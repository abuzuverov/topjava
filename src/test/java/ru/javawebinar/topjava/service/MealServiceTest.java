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
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
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
        assertThat(service.get(MEAL_2.getId(), USER_ID)).isEqualToComparingFieldByField(MEAL_2);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(MEAL_2.getId(), ADMIN_ID);
    }

    @Test
    public void delete() {
        service.delete(MEAL_2.getId(), USER_ID);
        assertThat(service.getAll(USER_ID)).doesNotContain(MEAL_2);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(MEAL_2.getId(), ADMIN_ID);
    }

    @Test
    public void getBetween() {
        List<Meal> between = service.getBetweenDates(
                LocalDate.of(2018, 5, 30),
                LocalDate.of(2018,5,30),
                USER_ID);
        List<Meal> expected = Arrays.asList(MEAL_4, MEAL_3, MEAL_2);
        assertThat(between).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

    @Test
    public void getAll() {
        assertThat(service.getAll(USER_ID)).usingFieldByFieldElementComparator().isEqualTo(MEALS);
    }

    @Test
    public void update() {
        Meal updated = new Meal(MEAL_2.getId(), LocalDateTime.now(), "Updated meal", 555);
        service.update(updated, USER_ID);
        assertThat(service.get(MEAL_2.getId(), USER_ID)).isEqualToComparingFieldByField(updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        service.update(MEAL_2, ADMIN_ID);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(LocalDateTime.now(), "New meal", 555);
        int newId = service.create(newMeal, USER_ID).getId();
        assertThat(service.get(newId, USER_ID)).isEqualToComparingFieldByField(newMeal);
    }
}