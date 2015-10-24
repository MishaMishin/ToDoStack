package home.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class CalcImplTest {
    private static CalcImpl calcImpl;
    @BeforeClass
    public static void initTest(){
        calcImpl = new CalcImpl();
    }

    @Test
    public void AnyName(){

        System.out.println("anyText");
    }

    @Test
    public void add_test1(){
    /*
    * Если а = 3 и б = 5, то результатом сложения будет число 8
    */
        int sum = calcImpl.sum(3,5);
        Assert.assertEquals(sum,8);
    }
    @Test
    public void add_test2(){
    /*
    * Если а = -5 и б = -6, то результатом сложения будет число 11
    */
        int sum = calcImpl.sum(-5, -6);
        Assert.assertEquals(sum,-11);
    }

    @Test
    public void add_test3(){
    /*
    * Если а = 3 и б = -5, то результатом сложения будет число -2
    */
        int sum = calcImpl.sum(3,-5);
        Assert.assertEquals(sum,-2);
    }
    @Test
    public void add_test4(){
    /*
    * Если а = -5 и б = 5, то результатом сложения будет число 0
    */
        int sum = calcImpl.sum(-5,5);
        Assert.assertEquals(sum,0);
    }



    @Test
    public void sub_test1(){
    /*
    * Если а = 5 и б = 3, то результатом вычитания будет число 2
    */
        int sub = calcImpl.sub(5, 3);
        Assert.assertEquals(sub,2);
    }
    @Test
    public void sub_test2(){
    /*
    * Если а = 6 и б = 5, то результатом вычитания будет число 1
    */
        int sub = calcImpl.sub(6, 5);
        Assert.assertEquals(sub,1);
    }

    @Test
    public void sub_test3(){
    /*
    * Если а = -5 и б = -10, то результатом вычитания будет число -15
    */
        int sub = calcImpl.sub(-5,-10);
        Assert.assertEquals(sub,5);
    }
    @Test
    public void sub_test4(){
    /*
    * Если а = 0 и б = 5, то результатом вычитания будет число -5
    */
        int sub = calcImpl.sub(0,5);
        Assert.assertEquals(sub,-5);
    }

    @Test
    public void devide_test1(){
    /*
    * Если а = 6 и б = 3, то результатом деления будет число 2
    */
        double div = calcImpl.devide(6, 3);
        Assert.assertEquals(div, 2, 1);
    }
    @Test
    public void devide_test2(){
    /*
    * Если а = 12 и б = 3, то результатом деления будет число 4
    */
        double div = calcImpl.devide(12, 3);
        Assert.assertEquals(div,4,1);
    }

    @Test(expected = ArithmeticException.class)
    public void devide_test3(){
    /*
    * Если а = 6 и б = 0, ошибка на 0 делить нельзя
    */
        double div = calcImpl.devide(6.0, 0.0);
        //Assert.assertEquals(div,2.0,1);
    }
    @Test
    public void devide_test4(){
    /*
    * Если а = -12 и б = 3, то результатом деления будет число -4
    */
        double div = calcImpl.devide(-12, 3);
        Assert.assertEquals(div,-4,1);
    }

    @Test
    public void devide_test5(){
    /*
    * Если а = 0 и б = 3, то результатом деления будет число 0
    */
        double div = calcImpl.devide(0, 3);
        Assert.assertEquals(div,0,1);
    }

    @Test
    public void mult_test1(){
    /*
    * Если а = 6 и б = 3, то результатом умножения будет число 18
    */
        double mult = calcImpl.mult(6, 3);
        Assert.assertEquals(mult,18,1);
    }
    @Test
    public void mult_test2(){
    /*
    * Если а = 12 и б = 3, то результатом умножения будет число 36
    */
        double mult = calcImpl.mult(0, 3);
        Assert.assertEquals(mult,0,1);
    }

    @Test
    public void mult_test3(){
    /*
    * Если а = -6 и б = 3, то результатом умножения будет число -18
    */
        double mult = calcImpl.mult(-6, 3);
        Assert.assertEquals(mult,-18,1);
    }
    @Test
    public void mult_test4(){
    /*
    * Если а = 12 и б = 0, то результатом умножения будет число 0
    */
        double mult = calcImpl.mult(12, 0);
        Assert.assertEquals(mult,0,1);
    }




}