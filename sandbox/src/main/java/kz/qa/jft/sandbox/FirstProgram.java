package kz.qa.jft.sandbox;

public class FirstProgram {

	public static void main(String[] args){
		hello("World");
        hello("Dasha");

        Square s = new Square(8);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());
	}

	public static void hello(String somebody){
	    System.out.println("Hello, " + somebody + "!");
    }

    public static double area(double l){
	    return l*l;
    }
}