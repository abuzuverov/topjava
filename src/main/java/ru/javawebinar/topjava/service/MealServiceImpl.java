package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoMock;
import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public class MealServiceImpl implements MealService {
    private MealDao mealDao;

    public MealServiceImpl() {
        mealDao = new MealDaoMock();
    }

    @Override
    public Collection<Meal> getAll() {
        return mealDao.getAll();
    }

    @Override
    public Meal get(int id) {
        return mealDao.get(id);
    }

    @Override
    public void delete(int id) {
        mealDao.delete(id);
    }

    @Override
    public void save(Meal meal) {
        mealDao.save(meal);
    }
}
