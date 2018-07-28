package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Meal save(Meal meal, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
/*        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Meal> cd = cb.createCriteriaDelete(Meal.class);
        Root<Meal> meal = cd.from(Meal.class);
        Predicate pId = cb.equal(meal.get("id"), id);
        Predicate pUserId = cb.equal(meal.get("userId"), userId);
        cd.where();
        return false;*/
        em.remove(get(id, userId));
        return true;
    }

    @Override
    public Meal get(int id, int userId) {
        return em.createQuery("select m from Meal m join fetch m.user u where m.id = :id and u.id = :userId", Meal.class)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createQuery("select m from Meal m join fetch m.user u where u.id = :userId order by m.dateTime desc", Meal.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createQuery("select m from Meal m join fetch m.user u where u.id = :userId and m.dateTime between :startDate and :endDate order by m.dateTime desc", Meal.class)
                .setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
}