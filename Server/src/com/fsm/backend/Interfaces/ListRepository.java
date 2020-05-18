package com.fsm.backend.Interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class ListRepository<T extends MyObject>
        extends ArrayList<T>
        implements Repository<T> {

    public ListRepository() { }

    public ListRepository(Collection<T> sample) {
        this.addAll(sample);
    }

    @Override
    public T findById(String id) {
        return findById(UUID.fromString(id));
    }

    @Override
    public T findById(UUID id) {
        return this.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(this);
    }

    public T addAndGet(T item) {
        this.add(item);
        return item;
    }

    @Override
    public Collection<T> filter(Predicate<T> condition) {
        return this.stream()
                .filter(condition)
                .collect(Collectors.toList());
    }

    @Override
    public T update(T updated) {
        removeById(updated.getId());
        return addAndGet(updated);
    }

    @Override
    public void removeById(UUID id) {
        System.out.println("id: " + id.toString());
        T toRemove = this.stream()
                .filter(item ->
                        item.getId().equals(id))
                .findFirst().orElseThrow();
        this.remove(toRemove);
    }
}
