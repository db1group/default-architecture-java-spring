package br.com.project.response;

import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

public final class ResponseFactory {

    private ResponseFactory() {
        // for frameworks
    }

    public static <T> ResponseEntity<Response> ok(T body) {
        return ResponseEntity.status(OK).body((SuccessResponse<T>) () -> body);
    }

    public static <T> ResponseEntity<Response> created(T body) {
        return ResponseEntity.status(CREATED).body((SuccessResponse<T>) () -> body);
    }

    public static ResponseEntity<Response> noContent() {
        return ResponseEntity.status(NO_CONTENT).body((SuccessResponse<String>) () -> null);
    }

    public static ResponseEntity<Response> error(ErrorResponseDetail errorDetail) {
        return ResponseEntity.status(BAD_REQUEST)
                .body((ErrorResponse<ErrorResponseDetail>) () -> Collections.singletonList(errorDetail));
    }

    public static ResponseEntity<Response> error(List<ErrorResponseDetail> errorDetails) {
        return ResponseEntity.status(BAD_REQUEST).body((ErrorResponse<ErrorResponseDetail>) () -> errorDetails);
    }

}
