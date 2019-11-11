package br.com.project.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 7318753939094407579L;
    public static final String ERROR_001 = "business.error.code.001";
    public static final String ERROR_002 = "business.error.code.002";
    private final String field;
    private final Object value;
    private final String message;

    public BusinessException(String field, Object value, String message) {
        this.field = field;
        this.value = value;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

}
