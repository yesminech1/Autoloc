package tn.esprit.autoloc.service;

public interface IWriteGenericService<T, I> {
    T create(T entity);
    void deleteById(I id);
}
