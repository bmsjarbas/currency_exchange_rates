package ie.britoj.currencyexchangerates.dataaccess;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.ParameterizedType;
import org.hibernate.query.Query;

@Component
public abstract class GenericDAO<T> implements DAO<T>  {
    private Class<T> persistentClass;

    @Autowired
    private SessionFactory sessionFactory;

    public GenericDAO(){
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getSession(){
        return  sessionFactory.getCurrentSession().getSession();
    }

    protected CriteriaQuery<T> getCriteriaQuery(){
        return getSession().getCriteriaBuilder().createQuery(this.persistentClass);

    }

    protected CriteriaBuilder getCriteriaBuilder(){
        return getSession().getCriteriaBuilder();
    }

    protected Query<T> getQuery(CriteriaQuery<T> criteriaQuery){
        return this.getSession().createQuery(criteriaQuery);
    }

    public T findById(long id) {

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
