package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;

import java.time.LocalDateTime;

@Repository
@Profile(Profiles.POSTGRES_DB)
public class JdbcPostgresMealRepositoryImpl extends JdbcMealRepositoryImpl<LocalDateTime> {

    @Override
    protected LocalDateTime getTimestamp(LocalDateTime localDateTime) {
        return localDateTime;
    }
}
