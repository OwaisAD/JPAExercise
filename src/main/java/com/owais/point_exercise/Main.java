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

        // Store X amount of Point objects in the database:
        pointFacade.persistPoints(1000);

        // Find the number of Point objects in the database:
        long numberOfPoints = pointFacade.findNumberOfPoints();
        System.out.println("Number of points: " + numberOfPoints);

        // Average X value
        double avgX = pointFacade.averageX();
        System.out.println("Average X: " + avgX);

        // Average Y value
        double avgY = pointFacade.averageY();
        System.out.println("Average Y: " + avgY);

        // Retrieve all the Point objects from the database:
        List<Point> listOfAllPoints = pointFacade.retrieveAllPoints();
        System.out.println("------------------\nList of all points:");
        for (Point p : listOfAllPoints) {
            System.out.println(p);
        }

        // Close the database connection:
        emf.close();
    }
}
