package com.xtremax.recruitment.livecoding.pathfinder.model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Frog extends Animal {

  @Override
  public int getMaxLeap() {
    return 3;
  }

  @Override
  public String makeSound() {
    return "croak";
  }
}
