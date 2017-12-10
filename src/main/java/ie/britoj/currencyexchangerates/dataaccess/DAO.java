package ie.britoj.currencyexchangerates.dataaccess;

public interface DAO<T> {
    T findById(long id);
    void delete(T entity);
    void create(T entity);
    void update(T entity);
}
