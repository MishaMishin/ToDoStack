package home.service;


public class CalcImpl {
    public int sum(int a, int b){
        int sum = a+b;
        return sum;
    }

    public int sub(int a, int b){
        int sub = a-b;
        return sub;
    }

    public double devide(double a, double b){
        if((a==0) || (b == 0)) return 0;
        double devide = a/b;
        return devide;
    }

    public int mult(int a, int b){
        int mult = a*b;
        return mult;
    }


}
