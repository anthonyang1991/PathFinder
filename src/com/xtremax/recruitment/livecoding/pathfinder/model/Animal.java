package com.xtremax.recruitment.livecoding.pathfinder.model;

public abstract class Animal {

  public abstract int getMaxLeap();

  public abstract String makeSound();

  public String dyingMessage() {
    return "dead";
  }

}
