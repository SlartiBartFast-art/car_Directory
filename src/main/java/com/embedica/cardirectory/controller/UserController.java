package com.embedica.cardirectory.controller;

import com.embedica.cardirectory.model.Car;
import com.embedica.cardirectory.usercriteria.IUserDao;
import com.embedica.cardirectory.usercriteria.SearchCriteria;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final IUserDao api;

    // todo - User у нас нету, всё переименовать под Car - а то выглядит как JS Ninja!!!
    @GetMapping(value = "/")
    public List<Car> findAll(@RequestParam(value = "search", required = false) String search) {
        List<SearchCriteria> params = new ArrayList<>();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            }
        }
        return api.searchUser(params);
    }
}

