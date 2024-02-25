package com.swyxl.test;

public class MinioTest {
    public static void main(String[] args) {
        String str = "123.jpg";
        String substring = str.substring(str.lastIndexOf('.'));
        System.out.println(substring);
        String contentType = substring.substring(1);
        System.out.println(contentType);
        String s = "http://127.0.0.1:9000/xhlj/service/20240224/avatar/2a2e6cd88f1b4f76b2867f000e9e1ea8.jpg";
        String sub = s.substring(27);
        System.out.println(sub);
    }
}
