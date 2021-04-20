package com.wush.kafka.consumer.exception;

public class KafkaNoEventsException extends RuntimeException {

    private String errorMessage;

    private String errorCode;

    public KafkaNoEventsException(String errorMessage, String errorCode) {
        super();
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public KafkaNoEventsException(String message) {
        super(message);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public KafkaNoEventsException() {
        super();
    }

    public KafkaNoEventsException(String message, Throwable cause) {
        super(message, cause);
    }

    public KafkaNoEventsException(Throwable cause) {
        super(cause);
    }
}
