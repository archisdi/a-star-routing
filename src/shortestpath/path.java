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
public class path {

    private ArrayList<city> city;
    private int level;
    private double fitness,pathDistance;
    private String stats;
    public path() {
        this.level = 0;
        this.fitness = 0;
        this.pathDistance = 0;
        this.stats = "open";
        this.city = new ArrayList<>();
    }

    public void addPath(city kota) {
        this.city.add(kota);
    } 

    public void setLevel(int level) {
        this.level = level;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }
    
    public void setValue(double value) {
        this.fitness = value;
    }
    
    public void setPathDistance(double distance){
        this.pathDistance = distance;
    }
    
    public void addPathDistance(double distance){
        this.pathDistance = this.pathDistance + distance;
    }

    public double getValue() {
        return fitness;
    }    

    public city getCity(int i) {
        return city.get(i);
    }

    public ArrayList<city> getPath() {
        return this.city;
    }

    public int getLevel() {
        return level;
    }

    public String getStats() {
        return stats;
    }
    
    public double getPathDistance(){
        return this.pathDistance;
    }

    @Override
    public String toString() {
        return "path{" + "city=" + city + ", level=" + level + ", value=" + fitness + ", stats=" + stats + '}';
    }
       
    
    public static Comparator<path> ValueCompare = new Comparator<path>() {

        @Override
        public int compare(path c1, path c2) {
            return Double.compare(c1.getValue(), c2.getValue());
        }

    };
}
