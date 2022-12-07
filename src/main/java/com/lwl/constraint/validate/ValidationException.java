package com.lwl.constraint.validate;

import java.util.List;

public class ValidationException extends RuntimeException {

    private List<ValidateResult> results;

    public ValidationException(List<ValidateResult> results) {
        super("执行校验失败");
        this.results = results;
    }

    public List<ValidateResult> getResults() {
        return results;
    }
}
