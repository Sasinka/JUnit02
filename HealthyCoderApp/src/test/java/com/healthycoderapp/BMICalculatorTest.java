package com.healthycoderapp;

import org.junit.jupiter.api.*;

//import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

class BMICalculatorTest {
    private String enviroment = "prod";
    @BeforeAll //proběhne právě jednou před všemi testy
    static void beforeAll(){
        System.out.println("before all unit test");
    }


    @ParameterizedTest (name = "weight={0}, height={1}")
    @CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)
    void should_returnTrue_When_DietRecomended(Double coreWeight, Double coreHeight){

        //given
        double weight = coreWeight;
        double height = coreHeight;
        //when
        boolean recomended = BMICalculator.isDietRecommended(weight, height);


        //then

        assertTrue(BMICalculator.isDietRecommended(weight, height));
    }



    @Test
    @DisplayName(">>>sample method display name")
    void should_returnFalse_When_DietRecomended(){

        //given
        double weight = 50;
        double height = 1.72;
        //when
        boolean recomended = BMICalculator.isDietRecommended(weight, height);


        //then

        assertFalse(BMICalculator.isDietRecommended(weight, height));

    }

    @Test
    void should_ThrowArithmeticException_when_HeightZero(){

        //given
        double weight = 50.0;
        double height = 0.0;
        //when
        Executable exetutable = () -> BMICalculator.isDietRecommended(weight, height);//()-> is lambda exception


        //then
        // //True(BMICalculator.isDietRecommended(weight, height));

        assertThrows(ArithmeticException.class, exetutable);
    }

    @Test
    void should_ReturnCoderWithTheWorstBMI_When_CoderListNotEmpty(){
        //given
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.89, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.89, 64.7));
        //when
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
        //then
        assertAll(
                ()-> assertEquals(1.82, coderWorstBMI.getHeight()),
                ()-> assertEquals(90.0, coderWorstBMI.getWeight())
        );
    }

    @Test
    void should_returnCoderWithWorstBMIIn1MS_(){
        //given
        assumeTrue(this.enviroment.equals("prod"));
        List<Coder> coders = new ArrayList<>();
        for(int i=0; i<1000; i++){
            coders.add(new Coder(1+i, 10.0+i));
        }
        //when
        Executable executable = ()-> BMICalculator.findCoderWithWorstBMI(coders);
        //then
        assertTimeout(Duration.ofMillis(500), executable);
    }
}