package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MealDaoMock implements MealDao {

    private List<Meal> meals = initMeals();

    private List<Meal> initMeals() {
        List<Meal> list = new ArrayList<>();
        list.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        list.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        list.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        list.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        list.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        list.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));

        return list;
    }

    public List<Meal> getAllMeals () {
        return meals;
    }
}
