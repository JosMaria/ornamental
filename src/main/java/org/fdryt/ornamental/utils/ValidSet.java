package org.fdryt.ornamental.utils;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.experimental.Delegate;

import java.util.HashSet;
import java.util.Set;

@Data
public class ValidSet<E> implements Set<E> {

    @Valid
    @Delegate
    private Set<E> set = new HashSet<>();
}
