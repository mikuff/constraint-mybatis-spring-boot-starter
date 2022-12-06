package com.lwl.constraint;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwl.constraint.validate.UpdateGroup;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Slf4j
public class EntityUpdater<T> extends BaseEntityOperation implements Loader<T>, UpdateHandler<T>, Executor<T> {

    private final BaseMapper<T> baseMapper;
    private T entity;
    private Consumer<T> success = t -> log.info("update success");
    private Consumer<? super Throwable> error = e -> e.printStackTrace();

    public EntityUpdater(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    public Option<T> execute() {
        doValidate(this.entity, UpdateGroup.class);
        T save = Try.of(() -> {
            baseMapper.updateById(entity);
            return this.entity;
        }).onSuccess(success).onFailure(error).getOrNull();
        return Option.of(save);
    }

    @Override
    public Executor<T> success(Consumer<T> consumer) {
        this.success = success;
        return this;
    }

    @Override
    public Executor<T> fail(Consumer<? super Throwable> consumer) {
        this.error = error;
        return this;
    }

    @Override
    public UpdateHandler<T> loadById(Serializable id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("id is null");
        }
        T t = baseMapper.selectById(id);
        if (Objects.isNull(t)) {
            throw new NullPointerException("can't query record by id: " + id);
        } else {
            this.entity = t;
        }
        return this;
    }

    @Override
    public UpdateHandler<T> load(Supplier<T> t) {
        this.entity = t.get();
        return this;
    }

    @Override
    public Executor<T> update(Consumer<T> consumer) {
        if (Objects.isNull(this.entity)) {
            throw new IllegalArgumentException("entity is null");
        }
        consumer.accept(this.entity);
        return this;
    }
}