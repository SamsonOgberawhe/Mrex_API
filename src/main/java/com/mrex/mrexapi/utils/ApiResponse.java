package com.mrex.mrexapi.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private String status;
    private String message;
    private Object data;
}
