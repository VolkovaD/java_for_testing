package kz.qa.jft.point;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    /**
     * Проверяет, что вычисленное растояние совпадает с
     * ожидаемым, когда мало символов после запятой
     */
    @Test
    public void testDistancePositive1(){

        Point p1 = new Point(0,1);
        Point p2 = new Point(0,9);

        Assert.assertEquals(p2.distanceMethod(p1), 8.0 );

    }

    /**
     * Проверяет, что вычисленное растояние совпадает с
     * ожидаемым, когда много символов после запятой
     */
    @Test
    public void testDistancePositive2(){

        Point p1 = new Point(1,1);
        Point p2 = new Point(3,9);

        Assert.assertEquals(p2.distanceMethod(p1), 8.246211251235321 );

    }

    /**
     * Проверяет, что расстояние между точками не равно нулю
     */
    @Test
    public void testDistanceNotEquals(){

        Point p1 = new Point(1,1);
        Point p2 = new Point(0,10);

        Assert.assertNotEquals(p2.distanceMethod(p1), 0.0);
    }
}
