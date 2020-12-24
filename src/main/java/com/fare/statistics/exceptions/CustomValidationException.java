package com.fare.statistics.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class CustomValidationException extends Exception {
    private final List<String> errorDescription;
    public CustomValidationException(List<String> errorDescription){
        super(errorDescription.toString());
        this.errorDescription = errorDescription;
    }
}
