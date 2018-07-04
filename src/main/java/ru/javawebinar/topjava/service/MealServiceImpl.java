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

    @Override
    public Meal getMealById(int id) {
        return mealDao.getMealById(id);
    }

    @Override
    public void deleteMeal(int id) {
        mealDao.deleteMeal(id);
    }

    @Override
    public void addMeal(Meal meal) {
        mealDao.addMeal(meal);
    }

    @Override
    public void updateMeal(int id, Meal meal) {
        mealDao.updateMeal(id, meal);
    }
}
