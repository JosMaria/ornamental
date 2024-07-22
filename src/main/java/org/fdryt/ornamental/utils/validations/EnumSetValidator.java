package org.fdryt.ornamental.utils.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Set;

public class EnumSetValidator implements ConstraintValidator<ValidEnum, Set<Enum<?>>> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(Set<Enum<?>> values, ConstraintValidatorContext context) {
        for (Enum<?> value : values) {
            if (Arrays.stream(enumClass.getEnumConstants())
                    .map(Enum::name)
                    .noneMatch(e -> e.equals(value.name()))) {
                return false;
            }
        }
        return true;
    }
}

