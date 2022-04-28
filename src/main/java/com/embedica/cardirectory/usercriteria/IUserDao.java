package com.embedica.cardirectory.usercriteria;

import com.embedica.cardirectory.model.Car;
import com.embedica.cardirectory.usercriteria.SearchCriteria;

import java.util.List;

public interface IUserDao {

    List<Car> searchUser(List<SearchCriteria> params);

    void save(Car entity);
}
