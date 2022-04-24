package com.embedica.cardirectory.repository;

import com.embedica.cardirectory.model.Car;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CustomCarRepositoryImpl implements CustomCarRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Car customFindMethod(Long id) {
        return null;
    }

    @Override
    public List<Car> findByRequestParamCustom(String id, String color, String year, String sort) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> carRoot = criteriaQuery.from(Car.class);

        // where car.id = {id} AND car.color = {color} // загрузить из табл Колор все текущие цвета
        Predicate predicateForBlueColor
                = criteriaBuilder.equal(carRoot.get("color"), "blue");
        Predicate predicateForRedColor
                = criteriaBuilder.equal(carRoot.get("color"), "red");
        Predicate predicateForColor
                = criteriaBuilder.or(predicateForBlueColor, predicateForRedColor);
//        if (id != null) criteriaBuilder.equal(carRoot.get("id"), id);
//        if (color != null) criteriaBuilder.equal(carRoot.get("color"), color);

        return entityManager.createQuery(criteriaQuery)
                .getResultList();
    }
}
