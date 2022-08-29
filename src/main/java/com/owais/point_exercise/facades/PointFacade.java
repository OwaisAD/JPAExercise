package com.owais.point_exercise.facades;

/**
 * *singleton*
 * forklaring her
 * <a href="https://refactoring.guru/design-patterns/singleton">...</a>
 * **/

public class PointFacade {

    private static PointFacade instance; //vi laver klassens egen instans

    private PointFacade() {} // privat constructor

    //vores egen fake constructor
    public static PointFacade getPointFacade() {
        if(instance == null) {
            instance = new PointFacade();
        }
        return instance;
    }

}
