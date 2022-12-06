package com.lwl.constraint.validate;

public class ValidateResult {

    // 字段路径
    private String fieldPath;

    // 信息
    private String message;

    public ValidateResult() {
    }

    public ValidateResult(String fieldPath, String message) {
        this.fieldPath = fieldPath;
        this.message = message;
    }
}
