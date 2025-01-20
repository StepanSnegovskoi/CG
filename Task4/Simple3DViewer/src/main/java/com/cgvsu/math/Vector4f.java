package com.cgvsu.math;

public class Vector4f {
    private float x, y, z, w;

    public Vector4f(final float x, final float y, final float z, final float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4f add(final Vector4f vector4f) {
        return new Vector4f(this.x + vector4f.x, this.y + vector4f.y, this.z + vector4f.z, this.w + vector4f.w);
    }

    public Vector4f subtract(final Vector4f vector4f) {
        return new Vector4f(this.x + (-vector4f.x), this.y + (-vector4f.y), this.z + (-vector4f.z), this.w + (-vector4f.w));
    }

    public Vector4f multiply(final float scalar) {
        return new Vector4f(this.x * scalar, this.y * scalar, this.z * scalar, this.w * scalar);
    }

    public Vector4f divide(final float scalar) {
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

    public float dot(final Vector4f vector4f) {
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

    public void setX(final float x) {
        this.x = x;
    }

    public void setY(final float y) {
        this.y = y;
    }

    public void setZ(final float z) {
        this.z = z;
    }

    public void setW(final float w) {
        this.w = w;
    }
}

