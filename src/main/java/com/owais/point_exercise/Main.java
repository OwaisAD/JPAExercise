package com.owais.point_exercise;

import com.owais.point_exercise.entities.Point;
import com.owais.point_exercise.facades.PointFacade;

import javax.persistence.*;
import java.util.*;

public class Main {
    private static PointFacade pointFacade;
    public static void main(String[] args) {
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); //svarer lidt til Connectionpool vi brugte på andet semester
        pointFacade = PointFacade.getInstance(emf);

        EntityManager em = emf.createEntityManager();

        pointFacade.persistPoints(1000);

        // Find the number of Point objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        System.out.println("Total Points: " + q1.getSingleResult());

        // Find the average X value:
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p"); //virker fordi vi har getX()
        System.out.println("Average X: " + q2.getSingleResult());

        // Retrieve all the Point objects from the database:
        TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        for (Point p : results) {
            System.out.println(p);
        }

        // Close the database connection:
        em.close();
        emf.close();
    }
}
