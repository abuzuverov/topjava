package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcMealRepositoryImpl implements MealRepository {

    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert insertMeal;

    @Autowired
    public JdbcMealRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        insertMeal = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("meals")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("dateTime", meal.getDateTime())
                    .addValue("description", meal.getDescription())
                    .addValue("calories", meal.getCalories())
                    .addValue("userId", userId);
            Number newKey = insertMeal.executeAndReturnKey(params);
            meal.setId(newKey.intValue());
        } else {
            String sql = "update meals set date_time = ?, description = ?, calories = ? where id = ? and user_id = ?";
            if (jdbcTemplate.update(sql,
                    meal.getDateTime(), meal.getDescription(), meal.getCalories(), meal.getId(), userId) == 0)
                return null;
        }
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("delete from meals where id = ? and user_id = ?", id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return DataAccessUtils.singleResult(jdbcTemplate.query("select * from meals where id = ? and user_id = ?", ROW_MAPPER, id, userId));
    }

    @Override
    public List<Meal> getAll(int userId) {
        return jdbcTemplate.query("select * from meals where user_id = ? order by date_time desc", ROW_MAPPER, userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        String sql = "select * from meals where date_time between ? and ? and user_id = ? order by date_time desc";
        return jdbcTemplate.query(sql, ROW_MAPPER, startDate, endDate, userId);
    }
}
