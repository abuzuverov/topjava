package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoMock implements MealDao {

    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger primaryKey = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values();
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }

    @Override
    public void save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(primaryKey.incrementAndGet());
            repository.put(meal.getId(), meal);
        } else {
            repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }
    }
}
