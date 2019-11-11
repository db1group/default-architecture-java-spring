package br.com.project.config;

import br.com.project.exception.BusinessException;
import br.com.project.response.ErrorResponseDetail;
import br.com.project.response.Response;
import br.com.project.response.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.*;

@ControllerAdvice
public class RestExceptionHandlerConfiguration {

    private static final String GLOBAL_ERROR_CODE_001 = "global.error.code.001";
    private static final String GLOBAL_ERROR_CODE_002 = "global.error.code.002";
    @Autowired
    private ResourceBundleMessageSource messageSource;
    private static Map<String, String> defaultMessages = new HashMap<>();

    {
        defaultMessages.put("não pode ser nulo", "O campo é obrigatório");
        defaultMessages.put("não pode estar em branco", "O campo é obrigatório");
        defaultMessages.put("deve ser maior que 0", "O campo deve ser maior que 0 (zero)");
        defaultMessages.put("não é um endereço de e-mail", "O campo não contém um endereço de e-mail válido");
    }

    @ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
    protected ResponseEntity<Response> handleException(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String field = ex.getName();
        String type = ex.getRequiredType().getSimpleName();
        Locale currentLocale = LocaleContextHolder.getLocale();
        Object[] values = new Object[] { field, type };
        String message = messageSource.getMessage(GLOBAL_ERROR_CODE_001, values, currentLocale);
        ErrorResponseDetail errorDetail = ErrorResponseDetail.builder()
                .withField(field)
                .withValue(ex.getValue())
                .withMessage(message).build();
        return ResponseFactory.error(errorDetail);
    }

    @ExceptionHandler(value = { ObjectOptimisticLockingFailureException.class })
    protected ResponseEntity<Response> handleException(ObjectOptimisticLockingFailureException ex, WebRequest request) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage(GLOBAL_ERROR_CODE_002, null, currentLocale);
        ErrorResponseDetail errorDetail = ErrorResponseDetail.builder()
                .withValue(ex.getIdentifier())
                .withMessage(message)
                .build();
        return ResponseFactory.error(errorDetail);
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    protected ResponseEntity<Response> handleException(MethodArgumentNotValidException ex, WebRequest request) {
        List<ErrorResponseDetail> errorDetails = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            FieldError fieldError = (FieldError) error;
            String fieldName = fieldError.getField();
            Object fieldValue = fieldError.getRejectedValue();
            String errorMessage = error.getDefaultMessage();
            ErrorResponseDetail errorDetail = ErrorResponseDetail.builder()
                    .withField(fieldName)
                    .withMessage(handleMessage(errorMessage))
                    .withValue(fieldValue)
                    .build();
            errorDetails.add(errorDetail);
        });
        return ResponseFactory.error(errorDetails);
    }

    private String handleMessage(String errorMessage) {
        return defaultMessages.getOrDefault(errorMessage, errorMessage);
    }

    @ExceptionHandler(value = { BusinessException.class })
    protected ResponseEntity<Response> handleException(BusinessException ex, WebRequest request) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage(ex.getMessage(), null, currentLocale);
        ErrorResponseDetail errorDetail = ErrorResponseDetail.builder()
                .withField(ex.getField())
                .withValue(ex.getValue())
                .withMessage(message)
                .build();
        return ResponseFactory.error(errorDetail);
    }

}
