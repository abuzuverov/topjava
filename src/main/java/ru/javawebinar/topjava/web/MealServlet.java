package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);

    private static final String LIST_OF_MEALS_NAME = "meals";
    private static final String MEAL_OBJ_NAME = "meal";

    private static final String LIST_OF_MEALS_PAGE = "/WEB-INF/pages/meals.jsp";
    private static final String MEAL_EDIT_PAGE = "/WEB-INF/pages/mealEdit.jsp";
    private static final String MEAL_ADD_PAGE = "/WEB-INF/pages/mealAdd.jsp";

    private static final String ACTION_PARAM_NAME = "action";
    private static final String ID_PARAM_NAME = "id";
    private static final String DATE_TIME_PARAM_NAME = "datetime";
    private static final String DESCRIPTION_PARAM_NAME = "description";
    private static final String CALORIES_PARAM_NAME = "calories";

    private static final String ACTION_LIST = "list";
    private static final String ACTION_ADD = "add";
    private static final String ACTION_ADD_SUBMIT = "addSubmit";
    private static final String ACTION_EDIT = "edit";
    private static final String ACTION_EDIT_SUBMIT = "editSubmit";
    private static final String ACTION_DELETE = "delete";

    private MealService mealService = new MealServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION_PARAM_NAME);
        String forwardPage;

        switch (action == null ? ACTION_LIST : action) {
            case ACTION_ADD: {
                forwardPage = MEAL_ADD_PAGE;
                break;
            }
            case ACTION_EDIT: {
                int id = Integer.parseInt(req.getParameter(ID_PARAM_NAME));
                req.setAttribute(MEAL_OBJ_NAME, mealService.getMealById(id));
                forwardPage = MEAL_EDIT_PAGE;
                break;
            }
            case ACTION_DELETE: {
                int id = Integer.parseInt(req.getParameter(ID_PARAM_NAME));
                mealService.deleteMeal(id);
                req.setAttribute(LIST_OF_MEALS_NAME, MealsUtil.getFilteredWithExceededInOnePass(mealService.getAllMeals(),
                        LocalTime.of(0, 0), LocalTime.of(23, 0), 2000));
                forwardPage = LIST_OF_MEALS_PAGE;
                break;
            }
            case ACTION_LIST:
            default: {
                forwardPage = LIST_OF_MEALS_PAGE;
                req.setAttribute(LIST_OF_MEALS_NAME, MealsUtil.getFilteredWithExceededInOnePass(mealService.getAllMeals(),
                        LocalTime.of(0, 0), LocalTime.of(23, 0), 2000));
                break;
            }
        }

        req.getRequestDispatcher(forwardPage).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION_PARAM_NAME);
        String forwardPage;

        LocalDateTime mealDate = LocalDateTime.parse(req.getParameter(DATE_TIME_PARAM_NAME));
        String mealDescription = req.getParameter(DESCRIPTION_PARAM_NAME);
        int mealCalories = Integer.parseInt(req.getParameter(CALORIES_PARAM_NAME));

        switch (req.getParameter(ACTION_PARAM_NAME)) {
            case ACTION_ADD_SUBMIT: {
                mealService.addMeal(new Meal(mealDate, mealDescription, mealCalories));
                forwardPage = MEAL_ADD_PAGE;
                break;
            }
            case ACTION_EDIT_SUBMIT: {
                mealService.updateMeal(Integer.parseInt(req.getParameter(ID_PARAM_NAME)),
                        new Meal(mealDate, mealDescription, mealCalories));
                forwardPage = LIST_OF_MEALS_PAGE;
                break;
            }
            default: {
                forwardPage = LIST_OF_MEALS_PAGE;
                break;
            }
        }
        req.getRequestDispatcher(forwardPage).forward(req, resp);
    }
}
