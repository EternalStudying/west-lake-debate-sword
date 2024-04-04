package com.exhibit;

import java.util.Random;

public class test {
    public static void main(String[] args) {
        Random random = new Random();
        int streamName = random.nextInt(9999) + 10000;
        System.out.println(streamName);
    }
}
