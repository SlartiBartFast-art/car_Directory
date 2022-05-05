package com.embedica.cardirectory.service;

import com.embedica.cardirectory.model.CarResponse;

public interface CarService {

    CarResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}
