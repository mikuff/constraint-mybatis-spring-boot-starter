package com.lwl.constraint;

import java.io.Serializable;
import java.util.function.Supplier;

public interface Loader<T> {

    UpdateHandler<T> loadById(Serializable id);

    UpdateHandler<T> load(Supplier<T> t);
}
