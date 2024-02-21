package com.swyxl.test;

public class MinioTest {
    public static void main(String[] args) {
        String str = "123.jpg";
        String substring = str.substring(str.lastIndexOf('.'));
        System.out.println(substring);
    }
}
