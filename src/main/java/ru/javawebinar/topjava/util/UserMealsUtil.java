package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList= Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.DECEMBER, 3, 7, 30), "завтрак", 200),
                new UserMeal(LocalDateTime.of(2015, Month.DECEMBER, 3, 13, 30), "обед", 300),
                new UserMeal(LocalDateTime.of(2015, Month.DECEMBER, 3, 18, 30), "ужин", 400),
                new UserMeal(LocalDateTime.of(2015, Month.DECEMBER, 3, 7, 30), "завтрак", 200),
                new UserMeal(LocalDateTime.of(2015, Month.DECEMBER, 3, 13, 30), "обед", 300),
                new UserMeal(LocalDateTime.of(2015, Month.DECEMBER, 3, 18, 30), "ужин", 400)
        );
    }

    public static List<UserMealWithExceed> getFilteredMealsWithExceeded(List<UserMeal> mealList,LocalDate localDate,LocalTime localTime,int normOfCaloriesPerDay)
    {
        //TODO return list with correctly exceeded field
        return null;
    }

}
