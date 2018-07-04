package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    public List<Meal> getAllMeals();
    public Meal getMealById(int id);
    public void deleteMeal(int id);
    public void addMeal(Meal meal);
    public void updateMeal(int id, Meal meal);
}
