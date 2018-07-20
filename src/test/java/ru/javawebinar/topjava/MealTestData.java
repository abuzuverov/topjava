package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;

public class MealTestData {
    public static final int START_SEQ = 100000;

    public static final Meal MEAL_2 = new Meal(START_SEQ + 2, LocalDateTime.of(2018, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL_3 = new Meal(START_SEQ + 3, LocalDateTime.of(2018, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL_4 = new Meal(START_SEQ + 4, LocalDateTime.of(2018, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL_5 = new Meal(START_SEQ + 5, LocalDateTime.of(2018, Month.MAY, 31, 10, 0), "Завтрак", 500);
    public static final Meal MEAL_6 = new Meal(START_SEQ + 6, LocalDateTime.of(2018, Month.MAY, 31, 13, 0), "Обед", 1000);
    public static final Meal MEAL_7 = new Meal(START_SEQ + 7, LocalDateTime.of(2018, Month.MAY, 31, 20, 0), "Ужин", 510);
    public static final List<Meal> MEALS = Arrays.asList(MEAL_7, MEAL_6, MEAL_5, MEAL_4, MEAL_3, MEAL_2);

    public static Meal getCreated() {
        return new Meal(null, of(2018, Month.JUNE, 1, 18, 0), "Созданный ужин", 300);
    }
}
