package com.advisor.health.mental.mentalhealthadvisor;

public class Question {
    private String Question;
    private String AnswerOne;
    private String AnswerTwo;
    private String AnswerThree;

    public Question(){

    }

    public Question(String sQuestion, String sAnswerOne, String sAnswerTwo, String sAnswerThree) {
        this.Question = sQuestion;
        this.AnswerOne = sAnswerOne;
        this.AnswerTwo = sAnswerTwo;
        this.AnswerThree = sAnswerThree;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String sQuestion) {
        this.Question = sQuestion;
    }

    public String getAnswerOne() {
        return AnswerOne;
    }

    public void setAnswerOne(String sAnswerOne) {
        this.AnswerOne = sAnswerOne;
    }

    public String getAnswerTwo() {
        return AnswerTwo;
    }

    public void setAnswerTwo(String sAnswerTwo) {
        this.AnswerTwo = sAnswerTwo;
    }

    public String getAnswerThree() {
        return AnswerThree;
    }

    public void setAnswerThree(String sAnswerThree) {
        this.AnswerThree = sAnswerThree;
    }
}
