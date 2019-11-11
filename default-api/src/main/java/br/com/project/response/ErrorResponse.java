package br.com.project.response;

import java.util.List;

@FunctionalInterface
public interface ErrorResponse<T extends ErrorResponseDetail> extends Response {

    List<T> getErrors();
}
