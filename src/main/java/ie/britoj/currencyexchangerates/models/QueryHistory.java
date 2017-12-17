package ie.britoj.currencyexchangerates.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class QueryHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    private String baseCurrency;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="query_history_id")
    private Set<Rate> rates;

    public QueryHistory(){
        this.rates = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Set<Rate> getRates() {
        return rates;
    }

}
