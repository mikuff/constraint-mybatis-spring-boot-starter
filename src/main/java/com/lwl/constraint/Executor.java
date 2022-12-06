package com.lwl.constraint;

import io.vavr.control.Option;

import java.util.function.Consumer;

public interface Executor<T> {

    // 执行成功
    Option<T> execute();

    // 执行成功
    Executor<T> success(Consumer<T> consumer);

    // 执行失败
    Executor<T> fail(Consumer<? super Throwable> consumer);

}
