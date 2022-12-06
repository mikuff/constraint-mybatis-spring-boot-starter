package com.lwl.constraint;

import com.lwl.constraint.validate.ValidateGroup;
import com.lwl.constraint.validate.ValidateResult;
import com.lwl.constraint.validate.ValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

public class BaseEntityOperation implements EntityOperation {

    static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public <T> void doValidate(T t, Class<? extends ValidateGroup> group) throws ValidationException {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t, group, Default.class);
        if (!isEmpty(constraintViolations)) {
            List<ValidateResult> results = constraintViolations.stream()
                    .map(cv -> new ValidateResult(cv.getPropertyPath().toString(), cv.getMessage()))
                    .collect(Collectors.toList());
            throw new ValidationException(results);
        }
    }

}
