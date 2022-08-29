package com.owais.point_exercise.facades;

import com.owais.point_exercise.entities.Point;

import javax.persistence.*;
import java.util.List;

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

    public long findNumberOfPoints() {
        // Find the number of Point objects in the database:
        EntityManager em = emf.createEntityManager();
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        long result = (long) q1.getSingleResult();
        em.close();
        return result;
    }

    public double averageX() {
        // Find the average X value:
        EntityManager em = emf.createEntityManager();
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p"); //virker fordi vi har getX()
        double result = (double) q2.getSingleResult();
        em.close();
        return result;
    }

    public double averageY() {
        // Find the average Y value:
        EntityManager em = emf.createEntityManager();
        Query q2 = em.createQuery("SELECT AVG(p.y) FROM Point p"); //virker fordi vi har getY()
        double result = (double) q2.getSingleResult();
        em.close();
        return result;
    }

    public List<Point> retrieveAllPoints() {
        // Retrieve all the Point objects from the database:
        EntityManager em = emf.createEntityManager();
        TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        em.close();
        return results;
    }
}
