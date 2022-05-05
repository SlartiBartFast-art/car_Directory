package com.embedica.cardirectory.usercriteria;

import com.embedica.cardirectory.model.Car;
import com.embedica.cardirectory.usercriteria.SearchCriteria;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDAO implements IUserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Car> searchUser(List<SearchCriteria> params) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = builder.createQuery(Car.class);
        Root<Car> r = query.from(Car.class);

        Predicate predicate = builder.conjunction();

        UserSearchQueryCriteriaConsumer searchConsumer =
                new UserSearchQueryCriteriaConsumer(predicate, builder, r);
        params.forEach(searchConsumer);
        query.where(searchConsumer.getPredicate());
    
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void save(Car entity) {
        entityManager.persist(entity);
    }
}
