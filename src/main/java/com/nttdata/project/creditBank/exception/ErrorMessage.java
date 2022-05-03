package com.nttdata.project.creditBank.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorMessage {
    private int statusCode;
    private String errorMessage;
}
