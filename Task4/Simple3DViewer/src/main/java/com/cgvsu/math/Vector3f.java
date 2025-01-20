package com.cgvsu.math;

public class Vector3f {
    public final static float eps = 1e-7f;
    public float x, y, z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean equals(Vector3f other) {
        return Math.abs(x - other.x) < eps && Math.abs(y - other.y) < eps && Math.abs(z - other.z) < eps;
    }

    public Vector3f add(Vector3f vector3f) {
        return new Vector3f(this.x + vector3f.x, this.y + vector3f.y, this.z + vector3f.z);
    }

    public Vector3f subtract(Vector3f vector3f) {
        return new Vector3f(this.x + (-vector3f.x), this.y + (-vector3f.y), this.z + (-vector3f.z));
    }

    public Vector3f multiply(float scalar) {
        return new Vector3f(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public Vector3f divide(float scalar) {
        if(scalar == 0) throw new ArithmeticException();
        return new Vector3f(this.x / scalar, this.y / scalar, this.z / scalar);
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3f normalize() {
        float len = length();
        return new Vector3f(x / len, y / len, z / len);
    }

    public float scalarMultiply(Vector3f vector3f) {
        return this.x * vector3f.x + this.y * vector3f.y + this.z * vector3f.z;
    }

    public Vector3f cross(Vector3f v) {
        return new Vector3f(
                this.y * v.z - this.z * v.y,
                - this.z * v.x + this.x * v.z,
                this.x * v.y - this.y * v.x
        );
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

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
