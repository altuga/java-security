package com.kodcu.question08.fixed;

public class Rectangle extends Figure {

    private int len, wid;

    public Rectangle(int len, int wid) {
        this.len = len;
        this.wid = wid;
    }

    @Override
    double area() {
        return len * wid;
    }

    @Override
    public String toString() {
        return "Rectangle{}";
    }
}
