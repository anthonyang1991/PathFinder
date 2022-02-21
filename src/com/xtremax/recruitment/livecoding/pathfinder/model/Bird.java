package com.xtremax.recruitment.livecoding.pathfinder.model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Bird extends Animal {

  @Override
  public int getMaxLeap() {
    return 8;
  }

  @Override
  public String makeSound() {
    return "chirp";
  }
}
