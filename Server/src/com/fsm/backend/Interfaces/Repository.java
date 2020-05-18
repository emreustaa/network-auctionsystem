package com.fsm.backend.Interfaces;

import java.util.Collection;
import java.util.UUID;
import java.util.function.Predicate;

public interface Repository<T extends MyObject>{
    T findById(String id);
    T findById(UUID id);
    Collection<T> getAll();
    T addAndGet(T item);
    Collection<T>filter(Predicate<T> condition);
    T update(T updated);
    void removeById(UUID id);

    default void removeById(String id) {
        removeById(UUID.fromString(id));
    }

}
