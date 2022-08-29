package com.owais.point_exercise.facades;

import com.owais.point_exercise.entities.Point;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * *singleton*
 * forklaring her
 * <a href="https://refactoring.guru/design-patterns/singleton">...</a>
 * **/

public class PointFacade {

    private static PointFacade instance; //vi laver klassens egen instans
    private static EntityManagerFactory emf;


    private PointFacade() {} // privat constructor

    //vores egen hjemmelavet constructor som er lavet
    //da vi ikke vil have at andre kan accesse og lave instanser
    //der må kun være en instans af klassen
    public static PointFacade getInstance(EntityManagerFactory _emf) {
        if(instance == null) {
            emf = _emf;
            instance = new PointFacade();
        }
        return instance;
    }

    public void persistPoints(int numberOfPoints) {
        // Store 1000 Point objects in the database:
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        for (int i = 0; i < numberOfPoints; i++) {
            Point p = new Point(i, i);
            em.persist(p);
        }
        em.getTransaction().commit();
        em.close();
    }

}
