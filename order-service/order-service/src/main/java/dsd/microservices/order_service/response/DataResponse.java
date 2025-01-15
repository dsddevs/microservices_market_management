package dsd.microservices.order_service.response;

public interface DataResponse<T> {
    T getData();
    void setData(T data);
}
