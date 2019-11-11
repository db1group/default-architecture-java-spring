package br.com.project.response;

public interface SuccessResponse<T> extends Response {

    T getData();
}
