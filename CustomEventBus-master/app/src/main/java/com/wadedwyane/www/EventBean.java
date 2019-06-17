package com.wadedwyane.www;

/**
 * @author kui.liu
 * @time 2019/4/9 14:55
 */
public class EventBean {

    private String stringOne;

    private String stringTwo;

    public String getStringTwo() {
        return stringTwo;
    }

    public void setStringTwo(String stringTwo) {
        this.stringTwo = stringTwo;
    }

    public String getStringOne() {
        return stringOne;
    }

    public void setStringOne(String stringOne) {
        this.stringOne = stringOne;
    }

    public EventBean(String stringOne, String stringTwo) {
        this.stringOne = stringOne;
        this.stringTwo = stringTwo;
    }

    @Override
    public String toString() {
        return "EventBean{" +
                "stringOne='" + stringOne + '\'' +
                ", stringTwo='" + stringTwo + '\'' +
                '}';
    }
}
