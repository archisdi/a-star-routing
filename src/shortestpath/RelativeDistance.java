/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shortestpath;

/**
 *
 * @author Archie
 */
public class RelativeDistance {
    private city nextCity;
    private double distance;

    public RelativeDistance(city city2, double distance) {
        this.nextCity = city2;
        this.distance = distance;
    }

    public city getNextCity() {
        return nextCity;
    }

    public void setNextCity(city city2) {
        this.nextCity = city2;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    
    
    
}
