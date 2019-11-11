package br.com.project.response;

public class ErrorResponseDetail {

    private String field;
    private Object value;
    private String message;

    private ErrorResponseDetail(ErrorResponseDetailBuilder errorResponseDetailBuilder) {
        this.field = errorResponseDetailBuilder.field;
        this.value = errorResponseDetailBuilder.value;
        this.message = errorResponseDetailBuilder.message;
    }

    public ErrorResponseDetail() {
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

    public static ErrorResponseDetailBuilder builder() {
        return new ErrorResponseDetailBuilder();
    }

    public static final class ErrorResponseDetailBuilder {
        private String field;
        private Object value;
        private String message;

        private ErrorResponseDetailBuilder() {
        }

        public ErrorResponseDetailBuilder withField(String field) {
            this.field = field;
            return this;
        }

        public ErrorResponseDetailBuilder withValue(Object value) {
            this.value = value;
            return this;
        }

        public ErrorResponseDetailBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponseDetail build() {
            return new ErrorResponseDetail(this);
        }
    }

}
