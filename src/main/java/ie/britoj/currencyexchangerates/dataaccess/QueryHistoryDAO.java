package ie.britoj.currencyexchangerates.dataaccess;

import ie.britoj.currencyexchangerates.models.QueryHistory;
import ie.britoj.currencyexchangerates.models.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class QueryHistoryDAO extends GenericDAO<QueryHistory> {

    public List<QueryHistory> historyByUser(String userEmail) {
        CriteriaBuilder criteriaBuilder = this.getCriteriaBuilder();
        CriteriaQuery<QueryHistory> criteriaQuery = this.getCriteriaQuery();
        Root<QueryHistory> rootQueryHistory = criteriaQuery.from(QueryHistory.class);
        Join<QueryHistory, User> childJoin = rootQueryHistory.join( "user" );
        criteriaQuery.where(criteriaBuilder.equal(childJoin.get("email"), userEmail));
        Query<QueryHistory> query = this.getQuery(criteriaQuery);


        List<QueryHistory> result = query.getResultList();
        return result;
    }
}
