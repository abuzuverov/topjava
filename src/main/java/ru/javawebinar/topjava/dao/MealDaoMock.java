package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MealDaoMock implements MealDao {

    private List<Meal> meals = initMeals();
    private int primaryKey;

    private List<Meal> initMeals() {
        List<Meal> list = new ArrayList<>();
        list.add(new Meal(1, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500)));
        list.add(new Meal(2, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000)));
        list.add(new Meal(3, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500)));
        list.add(new Meal(4, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000)));
        list.add(new Meal(5, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500)));
        list.add(new Meal(6, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)));

        primaryKey = list.size();
        return list;
    }

    @Override
    public List<Meal> getAllMeals() {
        return meals;
    }

    @Override
    public Meal getMealById(int id) {
        for (Meal meal : meals) {
            if (meal.getId() == id) return meal;
        }
        return null;
    }

    @Override
    public void deleteMeal(int id) {
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getId() == id) meals.remove(i);
        }
    }

    @Override
    public void addMeal(Meal meal) {
        primaryKey++;
        meals.add(new Meal(primaryKey, meal));
    }

    @Override
    public void updateMeal(int id, Meal meal) {
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getId() == id) meals.set(i, new Meal(id, meal));
        }
    }
}
