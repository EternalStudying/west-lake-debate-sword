package com.swyxl.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class DrawTest {
    public static void main(String[] args) {
        double winningIndex = BigDecimal.valueOf(new Random().nextDouble() * 100).setScale(1, RoundingMode.HALF_UP).doubleValue();
        System.out.println(winningIndex);
    }
}
