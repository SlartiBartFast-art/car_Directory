package com.customer.cardirectory.service;

import com.customer.cardirectory.model.CarResponse;

public interface CarService {

    CarResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}
