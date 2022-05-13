package com.nttdata.project.creditBank.util;

import java.util.List;
import java.util.Random;

public class OperatorUtils {
    public static int getIntegerPart(double firstFactor, double secondFactor) {
        return (int)(firstFactor/secondFactor);
    }

    public static String randomGenerator(List<String> idList) {
        Random randomNum = new Random();
        return idList.get(randomNum.nextInt(idList.size()));
    }
}
