package ie.britoj.currencyexchangerates.dataaccess;

import ie.britoj.currencyexchangerates.models.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserDAO extends GenericDAO <User>{
    public User findByEmail(String email){
        CriteriaBuilder criteriaBuilder = this.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery =  this.getCriteriaQuery();
        Root<User> rootUser = criteriaQuery.from(User.class);
        criteriaQuery.where(criteriaBuilder.equal(rootUser.get("email"), email));
        Query<User> queryUser = this.getQuery(criteriaQuery);
        User user = queryUser.getSingleResult();
        return user;
    }
}
