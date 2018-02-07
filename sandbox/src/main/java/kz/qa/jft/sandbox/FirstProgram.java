package kz.qa.jft.sandbox;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.SQLOutput;

public class FirstProgram {

	public static void main(String[] args){
		hello("World");
        hello("Dasha");

        double l = 8;
        System.out.println("Площадь квадрата со стороной " + l + " = " + area(l));
	}

	public static void hello(String somebody){
	    System.out.println("Hello, " + somebody + "!");
    }

    public static double area(double l){
	    return l*l;
    }
}