package com.cgvsu.math;

public class Vector4f {
    private float x, y, z, w;

    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4f add(Vector4f vector4f) {
        return new Vector4f(this.x + vector4f.x, this.y + vector4f.y, this.z + vector4f.z, this.w + vector4f.w);
    }

    public Vector4f subtract(Vector4f vector4f) {
        return new Vector4f(this.x + (-vector4f.x), this.y + (-vector4f.y), this.z + (-vector4f.z), this.w + (-vector4f.w));
    }

    public Vector4f multiply(float scalar) {
        return new Vector4f(this.x * scalar, this.y * scalar, this.z * scalar, this.w * scalar);
    }

    public Vector4f divide(float scalar) {
        if(scalar == 0) throw new ArithmeticException();
        return new Vector4f(this.x / scalar, this.y / scalar, this.z / scalar, this.w / scalar);
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public Vector4f normalize() {
        float len = length();
        return new Vector4f(x / len, y / len, z / len, w / len);
    }

    public float scalarMultiply(Vector4f vector4f) {
        return this.x * vector4f.x + this.y * vector4f.y + this.z * vector4f.z + this.w * vector4f.w;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }


    public float getZ() {
        return z;
    }

    public float getW() {
        return w;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setW(float w) {
        this.w = w;
    }
}

