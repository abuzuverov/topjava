package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoMock;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public class MealServiceImpl implements MealService {
    private MealDao mealDao;

    public MealServiceImpl() {
        mealDao = new MealDaoMock();
    }

    @Override
    public List<Meal> getAllMeals() {
        return mealDao.getAllMeals();
    }
}
