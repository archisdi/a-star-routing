/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shortestpath;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Archie
 */
public class city {

    private String name;
    private String stat;
    private double heur;
    private ArrayList<RelativeDistance> connection;

    public city(String name, String stat, double heur) {
        this.name = name;
        this.stat = stat;
        this.heur = heur;
        this.connection = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public void setHeur(double heur) {
        this.heur = heur;
    }

    public void addConnection(RelativeDistance distance) {
        this.connection.add(distance);
    }

    public String getName() {
        return name;
    }

    public String getStat() {
        return stat;
    }

    public double getHeur() {
        return heur;
    }

    public RelativeDistance getConnection(int i) {
        return this.connection.get(i);
    }

    public ArrayList<RelativeDistance> getAllConection() {
        return this.connection;
    }
    
    public static Comparator<city> HeurCompare = new Comparator<city>() {

        @Override
        public int compare(city c1, city c2) {
            return Double.compare(c1.getHeur(), c2.getHeur());
        }

    };

}
