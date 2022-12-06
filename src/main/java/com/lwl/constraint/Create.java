package com.lwl.constraint;

import java.util.function.Supplier;

public interface Create<T> {

    Executor<T> create(Supplier<T> supplier);

}
