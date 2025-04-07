package com.app.pickcourse.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DuplicateException extends RuntimeException {
    public DuplicateException(String message) {
        super(message);
    }
}
