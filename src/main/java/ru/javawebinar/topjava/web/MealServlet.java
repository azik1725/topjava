package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealServlet extends HttpServlet {
    private static final LoggerWrapper log=LoggerWrapper.get(MealServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     log.info("getAllMealList");
        request.setAttribute("mealList", UserMealsUtil.getWithExceeded(UserMealsUtil.MEALS_LIST,2000));
        request.getRequestDispatcher("/mealList.jsp").forward(request,response);
    }
}
