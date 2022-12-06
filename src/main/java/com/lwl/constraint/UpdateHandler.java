package com.lwl.constraint;

import java.util.function.Consumer;

public interface UpdateHandler<T> {
    Executor<T> update(Consumer<T> consumer);
}
