package vttp2023.batch3.ssf.frontcontroller.model;

import java.io.Serializable;
import java.util.Random;

public class Captcha implements Serializable {

    private static final int NUM_MIN = 1;
    
    private static final int NUM_MAX = 50;

    private static final String[] OPERATORS = {
        "+",
        "-",
        "*",
        "/"
    };
    
    private int num1;

    private int num2;

    private String operator;

    private int result;

    private String captchaString;

    private int answer;

    public Captcha() {
        this.num1 = generateRandomNumber();
        this.num2 = generateRandomNumber();
        this.operator = generateRandomOperator();

        switch (this.operator) {
            case "+" -> this.result = this.num1 + this.num2;
            case "-" -> this.result = this.num1 - this.num2;
            case "*" -> this.result = this.num1 * this.num2;
            case "/" -> this.result = this.num1 / this.num2;
        }

        this.captchaString = String.format("What is %d %s %d?", this.num1, this.operator, this.num2);
    }

    public int generateRandomNumber() {
        return new Random().nextInt(NUM_MIN, NUM_MAX + 1);
    }

    public String generateRandomOperator() {
        return OPERATORS[(new Random().nextInt(OPERATORS.length))];
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getCaptchaString() {
        return captchaString;
    }

    public void setCaptchaString(String captchaString) {
        this.captchaString = captchaString;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
