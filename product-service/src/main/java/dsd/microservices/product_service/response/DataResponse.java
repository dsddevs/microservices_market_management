package dsd.microservices.product_service.response;

public interface DataResponse<T> {
    T getData();
    void setData(T data);
}
