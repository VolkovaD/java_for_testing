package kz.qa.jft.point;

public class mainRunClass {
    public static void main(String[] args){
        Point p1 = new Point();
        Point p2 = new Point();

        p1.x = 1;
        p1.y = 1;

        p2.x = 11;
        p2.y = 11;


        System.out.println("Расстояние между двумя точками p1( " + p1.x + "," + p1.y + ") и р2( "  + p2.x + "," +  p2.y + ") = " + distance(p1, p2));

    }

    public static double distance (Point p1, Point p2){
        return Math.sqrt(Math.pow((p2.x - p1.x),2) + (Math.pow((p2.y - p1.y), 2)));
    }
}
