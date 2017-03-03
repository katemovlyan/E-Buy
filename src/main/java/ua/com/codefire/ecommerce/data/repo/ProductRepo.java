package ua.com.codefire.ecommerce.data.repo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.codefire.ecommerce.data.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by human on 1/31/17.
 */
@Repository
@Transactional(readOnly = true)
public class ProductRepo{

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Product> findAll() {
        return entityManager.createNamedQuery("Product.findAll", Product.class).getResultList();
    }

    @Transactional(readOnly = false)
    public void add(Product product) {
            entityManager.persist(product);
    }

    @Transactional
    public Product getById(int id) {
        return entityManager.find(Product.class, id);
    }

    @Transactional
    public void update(Product product){
        entityManager.merge(product);
    }

    @Transactional
    public void delete(Product product){
        entityManager.remove(entityManager.contains(product) ? product : entityManager.merge(product));
    }

    @Transactional
    public List<Product> getProductsByPage(int pageNumber, int amountByPage) {
        Query query = entityManager.createNamedQuery("Product.findAll", Product.class);
        query.setFirstResult((pageNumber - 1) * amountByPage);
        query.setMaxResults(amountByPage);
        return query.getResultList();
    }

    @Transactional
    public long getProductsAmount() {
        return entityManager.createNamedQuery("Product.getCount", Long.class).getSingleResult();
    }
}
