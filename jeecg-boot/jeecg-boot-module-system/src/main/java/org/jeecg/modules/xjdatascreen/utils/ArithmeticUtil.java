package org.jeecg.modules.xjdatascreen.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author Edge
 * @version 1.0
 * @date 2020/11/19 13:44
 */
public class ArithmeticUtil {

    public static int sum(int a, int b) {
        return a + b;
    }

    public static int sub(int a, int b) {
        return a - b;
    }

    public static double mul(double a, double b) {
        return a * b;
    }

    public static double division(double a, double b) {
        double resultDiv = 0;
        if(b != 0) {
            resultDiv = a/ b;
        } else {
            resultDiv = 0;
        }
        return resultDiv;
    }

    public static String format(double a, double b) {
        double resultDiv = 0;
        if(b != 0) {
            resultDiv = a/ b;
        } else {
            resultDiv = 0;
        }
        double mul = mul(resultDiv, 100);
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setRoundingMode(RoundingMode.DOWN);
        return numberInstance.format(mul) + "%";
    }

    public static void main(String[] args) {
        String format = format(3, 9);
        System.out.println(format);
    }
}
