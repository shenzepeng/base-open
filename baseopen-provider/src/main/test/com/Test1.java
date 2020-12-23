package com;

import java.util.UUID;

/**
 * 要写注释呀
 */
public class Test1 {
    public static void main(String[] args) {
        String replaceAll = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(replaceAll.length());
        System.out.println(replaceAll);
    }
}
