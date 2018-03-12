package kz.qa.jft;

public class Equality {

    public static void main(String[] args) {
        String s1 = "firefox";
        String s2 = "firefox"; //new String(s1);

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
    }
}
