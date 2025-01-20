package com.cgvsu.math;

public class Vector2f {
    public float x, y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f add(Vector2f vector2f) {
        return new Vector2f(this.x + vector2f.x, this.y + vector2f.y);
    }

    public Vector2f subtract(Vector2f vector2f) {
        return new Vector2f(this.x + (-vector2f.x), this.y + (-vector2f.y));
    }

    public Vector2f multiply(float scalar) {
        return new Vector2f(this.x * scalar, this.y * scalar);
    }

    public Vector2f divide(float scalar) {
        if(scalar == 0) throw new ArithmeticException();
        return new Vector2f(this.x / scalar, this.y / scalar);
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public Vector2f normalize() {
        float len = length();
        return new Vector2f(x / len, y / len);
    }

    public double scalarMultiply(Vector2f vector2f) {
        return this.x * vector2f.x + this.y * vector2f.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
