package com.lwl.constraint;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@SuppressWarnings("unchecked")
public abstract class EntityOperations {

    // 更新
    public static <T> EntityUpdater<T> doUpdate(BaseMapper<T> baseMapper) {
        return new EntityUpdater<>(baseMapper);
    }

    // 创建
    public static <T> EntityCreator<T> doCreate(BaseMapper<T> baseMapper) {
        return new EntityCreator(baseMapper);
    }

}
