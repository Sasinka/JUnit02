package com.healthycoderapp;

import org.junit.jupiter.api.*;

//import static org.junit.jupiter.api.AssertAll.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DietPlannerTest {

    private DietPlanner dietPlanner;

    @BeforeEach
    void setUp(){
        this.dietPlanner = new DietPlanner(20, 30, 50);
    }

    @AfterEach
    void afterEach(){
        System.out.println("A unit test has been finished");
    }

    @RepeatedTest(value =10, name = RepeatedTest.LONG_DISPLAY_NAME)
    public void should_ReturnCorrectDietPlan_When_CorrectCoder(){
        //given
        Coder coder = new Coder(1.82, 72.0, 26, Gender.MALE);
        DietPlan expected = new DietPlan(2152, 108, 269, 275);

        //when
        DietPlan actual = dietPlanner.calculateDiet(coder);

        //then
        Assertions.assertAll (
                ()-> assertEquals(expected.getCalories(), actual.getCalories()),
                ()-> assertEquals(expected.getProtein(), actual.getProtein()),
                ()-> assertEquals(expected.getFat(), actual.getCarbohydrate())
        );

    }
}
