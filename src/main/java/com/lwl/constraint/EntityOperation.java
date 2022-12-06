package com.lwl.constraint;

import com.lwl.constraint.validate.ValidateGroup;
import com.lwl.constraint.validate.ValidationException;

public interface EntityOperation {
     public <T> void doValidate(T t, Class<? extends ValidateGroup> group) throws ValidationException;
}
