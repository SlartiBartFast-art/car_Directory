package com.customer.cardirectory.usercriteria;

import com.customer.cardirectory.model.Car;

import java.util.List;

public interface IUserDao {

    List<Car> searchUser(List<SearchCriteria> params);

    void save(Car entity);
}
