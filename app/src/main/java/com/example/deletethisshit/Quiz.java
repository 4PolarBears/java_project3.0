package com.example.deletethisshit;

import java.util.ArrayList;

public class Quiz {

    /*Possible questions:
    * 1. What year was the most babies born in XXX ?
    * 2. What was the total population in XXX in 2022 ?
    * 3. */

    private String q1;
    private String q2;
    private String q3;
    private String q4;
    private String q5;


    public Quiz(String name) {
        this.q1 = "1. What year has the most babies born in " + name + " ?";
        this.q2 = "2. What was the total population in " + name + "in 2022 ?";
        this.q3 = "3. What year has the most people died in " + name + " ?";
        this.q4 = "4. What was the total population change in " + name + "in 2022 ?";
        this.q5 = "5. What was the employment rate in " + name + "in 2022 ?";
    }

    public static ArrayList<Quiz> createQuiz(String name) {
        Quiz quiz = new Quiz(name);
        ArrayList<Quiz> arrayWithQuiz = new ArrayList<>();
        arrayWithQuiz.add(quiz);
        return arrayWithQuiz;

    }

    public String getQ1() {
        return q1;
    }
    public String getQ2() {
        return q2;
    }
    public String getQ3() {
        return q3;
    }
    public String getQ4() {
        return q4;
    }
    public String getQ5() {
        return q5;
    }



}
