package ua.com.codefire.ecommerce.data.repo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.codefire.ecommerce.data.entity.Currency;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Katya on 02.02.2017.
 */
@Repository
@Transactional(readOnly = true)
public class CurrencyRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Currency> findAll() {
        return entityManager.createNamedQuery("Currency.findAll", Currency.class).getResultList();
    }

    @Transactional(readOnly = false)
    public void add(Currency currency) {
        entityManager.persist(currency);
    }

    @Transactional
    public Currency getById(int id) {
        return entityManager.find(Currency.class, id);
    }

    @Transactional
    public String getNameById(int id) {
        return entityManager.createNamedQuery("Currency.findNameById").setParameter("currencyId", id).getSingleResult().toString();
    }

    @Transactional
    public void update(Currency currency){
        entityManager.merge(currency);
    }

    @Transactional
    public void delete(Currency currency){
        entityManager.remove(currency);
    }
}
