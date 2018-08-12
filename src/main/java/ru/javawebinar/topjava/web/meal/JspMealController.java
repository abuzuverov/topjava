package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping("/meals")
public class JspMealController {

    @Autowired
    private MealService service;

    @GetMapping("")
    public String meals(Model model) {
        int userId = SecurityUtil.authUserId();
        model.addAttribute("meals", MealsUtil.getWithExceeded(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay()));
        return "meals";
    }

    @GetMapping("create")
    public ModelAndView createView() {
        Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        return new ModelAndView("mealSpringForm", "meal", meal);
    }

    @GetMapping("update")
    public ModelAndView updateView(@RequestParam(value = "id") int id) {
        return new ModelAndView("mealSpringForm", "meal", service.get(id, SecurityUtil.authUserId()));
    }

    @PostMapping("submit")
    public String submit(@RequestParam String id,
                         @RequestParam String dateTime,
                         @RequestParam String description,
                         @RequestParam int calories) {
        Meal meal = new Meal(LocalDateTime.parse(dateTime), description, calories);
        if (id.isEmpty()) {
            service.create(meal, SecurityUtil.authUserId());
        } else {
            meal.setId(Integer.parseInt(id));
            service.update(meal, SecurityUtil.authUserId());
        }
        return "redirect:/meals";
    }

    @GetMapping("delete")
    public String delete(@RequestParam(value = "id") int id) {
        service.delete(id, SecurityUtil.authUserId());
        return "redirect:/meals";
    }

}
