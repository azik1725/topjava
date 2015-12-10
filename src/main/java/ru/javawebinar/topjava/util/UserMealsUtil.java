package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> filteredMealsWithExceeded = getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        filteredMealsWithExceeded.forEach(System.out::println);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate,Integer> caloriesSumByDay=new HashMap<>();
        for (UserMeal userMeal :mealList) {
            LocalDateTime caloriesByDay=userMeal.getDateTime();
            caloriesSumByDay.put(caloriesByDay.toLocalDate(),caloriesSumByDay.getOrDefault(caloriesByDay,0)+userMeal.getCalories());
        }

        List<UserMealWithExceed> mealExceed=new ArrayList<>();
        for (UserMeal userMeal :mealList) {
            LocalTime dateTime=userMeal.getDateTime().toLocalTime();
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(),startTime,endTime))
            {
                mealExceed.add(new UserMealWithExceed(userMeal.getDateTime(),userMeal.getDescription(),userMeal.getCalories(),
                        caloriesSumByDay.get(userMeal.getDateTime().toLocalDate())>caloriesPerDay));
            }
        }
        //Version with Java 8
        /*Map<LocalDate, Integer> caloriesSumByDay = mealList.stream().collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(),
                Collectors.summingInt(UserMeal::getCalories)));
        mealList.stream().filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                .map(um -> new UserMealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(),
                        caloriesSumByDay.get(um.getDateTime().toLocalDate()) > caloriesPerDay)).collect(Collectors.toList());
        */return null;
    }
}
