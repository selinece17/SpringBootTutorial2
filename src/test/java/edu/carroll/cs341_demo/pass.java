package edu.carroll.cs341_demo;

public class pass {
    public static void main(String[] args) {
        final String hash = Integer.toString("supersecret".hashCode());
        System.out.println(hash);
    }
}