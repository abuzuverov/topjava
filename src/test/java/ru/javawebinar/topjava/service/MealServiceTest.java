package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.MealRestController;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    private static final Logger log = LoggerFactory.getLogger(MealServiceTest.class);

    @Autowired
    private MealService service;

    @Test
    public void get() {
        /*Meal newMeal = new Meal(LocalDateTime.now(), "New meal", 555);
        int newId = service.create(newMeal, USER_ID).getId();
        assertEquals(newMeal, service.get((newId), USER_ID));*/
        //assertThat
    }

    @Test
    public void delete() {
        List<Meal> before = service.getAll(USER_ID);
        Meal created = service.create(new Meal(LocalDateTime.now(), "New meal", 555), USER_ID);
        service.delete(created.getId(), USER_ID);
        assertEquals(before, service.getAll(USER_ID));
    }

    @Test
    public void getBetweenDates() {
    }

    @Test
    public void getBetweenDateTimes() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void update() {
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(LocalDateTime.now(), "New meal", 555);
        int newId = service.create(newMeal, USER_ID).getId();
        assertEquals(newMeal, service.get((newId), USER_ID));
    }
}