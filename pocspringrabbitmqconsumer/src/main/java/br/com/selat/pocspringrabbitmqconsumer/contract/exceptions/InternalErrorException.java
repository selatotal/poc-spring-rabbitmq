package br.com.selat.pocspringrabbitmqconsumer.contract.exceptions;

public class InternalErrorException extends RuntimeException {

    public InternalErrorException(String message) {
        super(message);
    }
}
