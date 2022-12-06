package com.lwl.constraint;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwl.constraint.validate.CreateGroup;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Slf4j
public class EntityCreator<T> extends BaseEntityOperation implements Create<T>, UpdateHandler<T>, Executor<T> {

    private final BaseMapper<T> baseMapper;
    private T t;
    private Consumer<T> success = t -> log.info("save success");
    private Consumer<? super Throwable> error = e -> e.printStackTrace();

    public EntityCreator(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    public Executor<T> create(Supplier<T> supplier) {
        this.t = supplier.get();
        return this;
    }

    @Override
    public Option<T> execute() {
        doValidate(this.t, CreateGroup.class);
        T save = Try.of(() -> {
            baseMapper.insert(t);
            return this.t;
        }).onSuccess(success).onFailure(error).getOrNull();
        return Option.of(save);
    }

    @Override
    public Executor<T> success(Consumer<T> success) {
        this.success = success;
        return this;
    }

    @Override
    public Executor<T> fail(Consumer<? super Throwable> error) {
        this.error = error;
        return this;
    }

    @Override
    public Executor<T> update(Consumer<T> consumer) {
        if (Objects.isNull(t)) {
            throw new IllegalArgumentException("entity must supply");
        }
        consumer.accept(this.t);
        return this;
    }
}
