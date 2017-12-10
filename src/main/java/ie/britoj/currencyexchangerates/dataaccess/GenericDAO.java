package ie.britoj.currencyexchangerates.dataaccess;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;

@Component
public abstract class GenericDAO<T> implements DAO<T>  {
    private Class<T> persistentClass;

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return  sessionFactory.getCurrentSession().getSession();
    }
    public T findById(long id) {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        return (T) getSession().get(this.persistentClass, id);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    public void create(T entity) {
        getSession().save(entity);
    }

    public void update(T entity) {
        getSession().update(entity);
    }
}
