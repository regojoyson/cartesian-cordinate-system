package com.cartesian.coordinateSystem.service;

import com.cartesian.coordinateSystem.model.Line;
import com.cartesian.coordinateSystem.model.Point;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test cases for the Cartesian Service
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartesianServiceTest {

    @Autowired
    private CartesianService cartesianService;


    @Test
    public void testIsLineUsingTwoPoints() {
        Assert.assertTrue(cartesianService.isLineUsingTwoPoints(new Point(3, 2), new Point(2, 6)));
        Assert.assertFalse(cartesianService.isLineUsingTwoPoints(new Point(3, 3), new Point(3, 3)));
    }


    @Test
    public void testFindGradientAndYIntercept() {
        Assert.assertEquals(cartesianService.findGradientAndYIntercept(new Point(5, 2), new Point(2, 7)), "10");
    }

    @Test
    public void testIsParallelLineTrue() {
        Line l1 = new Line();
        Line l2 = new Line();
        l1.setStart(new Point(2, 2));
        l1.setEnd(new Point(2, 8));
        l2.setStart(new Point(3, 3));
        l2.setEnd(new Point(3, 3));
        Assert.assertTrue(cartesianService.isParallel(l1, l2));
    }


    @Test
    public void testIsParallelLineFalse() {
        Line l1 = new Line();
        Line l2 = new Line();
        l1.setStart(new Point(-8, -8));
        l1.setEnd(new Point(-2, 8));
        l2.setStart(new Point(5, 7));
        l2.setEnd(new Point(3, 3));
        Assert.assertFalse(cartesianService.isParallel(l1, l2));
    }


    @Test
    public void testIsPerpendicular() {
        Line l1 = new Line();
        Line l2 = new Line();
        l1.setStart(new Point(1, 2));
        l1.setEnd(new Point(1, 8));
        l2.setStart(new Point(0, 1));
        l2.setEnd(new Point(8, 1));
        Assert.assertTrue(cartesianService.isPerpendicular(l1, l2));
    }


    @Test
    public void testLineIncidencePoint() {
        Line l1 = new Line();
        Line l2 = new Line();
        l1.setStart(new Point(1, 1));
        l1.setEnd(new Point(4, 4));
        l2.setStart(new Point(1, 8));
        l2.setEnd(new Point(5, 0));
        Assert.assertEquals(String.valueOf(cartesianService.lineIncidencePoint(l1, l2).getX()),String.valueOf("3.3333333333333335"));
    }







}
