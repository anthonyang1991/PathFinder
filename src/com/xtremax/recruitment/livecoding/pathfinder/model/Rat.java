package com.xtremax.recruitment.livecoding.pathfinder.model;

public class Rat extends Animal {

    @Override
    public int getMaxLeap() {
        return 1;
    }

    @Override
    public String makeSound() {
        return "squeak";
    }
}
