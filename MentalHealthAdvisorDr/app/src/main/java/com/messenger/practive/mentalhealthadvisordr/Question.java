package com.messenger.practive.mentalhealthadvisordr;
/**
 * Created by curti on 23/02/2018.
 */
public class Question {
    private String sQuestion;
    private String sAnswerOne;
    private String sAnswerTwo;
    private String sAnswerThree;

    public Question(){

    }

    public Question(String sQuestion, String sAnswerOne, String sAnswerTwo, String sAnswerThree) {
        this.sQuestion = sQuestion;
        this.sAnswerOne = sAnswerOne;
        this.sAnswerTwo = sAnswerTwo;
        this.sAnswerThree = sAnswerThree;
    }
    
    public String getQuestion() {
        return sQuestion;
    }

    public void setQuestion(String sQuestion) {
        this.sQuestion = sQuestion;
    }

    public String getAnswerOne() {
        return sAnswerOne;
    }

    public void setAnswerOne(String sAnswerOne) {
        this.sAnswerOne = sAnswerOne;
    }

    public String getAnswerTwo() {
        return sAnswerTwo;
    }

    public void setAnswerTwo(String sAnswerTwo) {
        this.sAnswerTwo = sAnswerTwo;
    }

    public String getAnswerThree() {
        return sAnswerThree;
    }

    public void setAnswerThree(String sAnswerThree) {
        this.sAnswerThree = sAnswerThree;
    }
}
