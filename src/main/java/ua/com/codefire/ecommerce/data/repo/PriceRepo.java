package ua.com.codefire.ecommerce.data.repo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.codefire.ecommerce.data.entity.Price;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Katya on 02.02.2017.
 */
@Repository
@Transactional(readOnly = true)
public class PriceRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Price> findAll() {
        return entityManager.createNamedQuery("Price.findAll", Price.class).getResultList();
    }

    @Transactional
    public Price findTopicalPrice(Integer id){
        return entityManager.createNamedQuery("Price.findTopical", Price.class).setParameter("productId", id).getSingleResult();
    }
//    public List<Integer> getAllPricesIds() {
//        return entityManager.createQuery("SELECT p.id FROM Price p", Integer.class)
//                .getResultList();
//    }

    @Transactional
    public void add(Price price) {
        entityManager.persist(price);
    }

    @Transactional
    public Price getById(int id) {
        return entityManager.find(Price.class, id);
    }

    @Transactional
    public String getValueById(int id) {
        return entityManager.createNamedQuery("Price.findValueById").setParameter("priceId", id).getSingleResult().toString();
    }

    @Transactional
    public void update(Price price) {
        entityManager.merge(price);
    }

    @Transactional
    public void delete(Price price) {
        entityManager.remove(price);
    }
}
