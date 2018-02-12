package kz.qa.jft.point;

public class MainRunClass {

    public static void main(String[] args){
        Point p1 = new Point(6, 6);
        Point p2 = new Point(9, 9);

        System.out.println("Через функцию distanceFunction():\nРасстояние между двумя точками p1(" + p1.x + "," + p1.y +
                ") и р2(" + p2.x + "," +  p2.y + ") = " + distanceFunction(p1, p2));
        System.out.println("Через метод distanceMethod():\nРасстояние между двумя точками p1(" + p1.x + "," + p1.y +
                ") и р2(" + p2.x + "," +  p2.y + ") = " + p2.distanceMethod(p1));
    }

    public static double distanceFunction(Point p1, Point p2){
        double diffX = (p2.x - p1.x);
        double diffY = (p2.y - p1.y);
        return Math.sqrt(Math.pow(diffX,2) + (Math.pow(diffY, 2)));
    }
}
