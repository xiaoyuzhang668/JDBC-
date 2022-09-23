package com.sg.jdbcexample.ui;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Email: xiaoyuzhang668@gmail.com Date: 2022
 *
 * @author catzh
 */
public interface UserIO {
//print message

    void print(String msg);

    //print message on one same line
    void printOneLine(String msg);

    //print one line message, seperated by delimiter
    public void printOneLine(String msg, String delimiter);

    //readString message
    String readString(String prompt);

    //readString message
    String readStringOneLine(String prompt);

    //string input mandatory field
    String readStringReq(String prompt, String fieldName);

    //retrict user to enter yes or no answer
    String readStringRestrict(String prompt, String first, String second, String fieldName);

    // readInt message and min and max check
    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    int readInt(String prompt, int min);

    //readDouble message and min and max check
    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    // readFloat message and min and max check
    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    BigDecimal readFloat(String prompt, float min); // define minimum input for float for BigDecimal

    // readLong message and min and max check
    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    //pring error message for this project
    void displayError(String errorMsg);

    //pring error message on one same line
    void printOne(String message);

    //pring message on one line with delimiter
    void printOne(String msg, String delimiter);

    //method to   prompt user enter to continue    // display break line
    void displayEnter();
    
    void displayBreak();
}
