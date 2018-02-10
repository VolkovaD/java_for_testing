package kz.qa.jft.point;

public class Point {
    double x;
    double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double distanceMethod(Point p){
        double diffX = (this.x - p.x);
        double diffY = (this.y - p.y);
        return Math.sqrt(Math.pow(diffX,2) + (Math.pow(diffY, 2)));
    }
}
