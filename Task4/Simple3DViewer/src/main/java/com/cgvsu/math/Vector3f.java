package com.cgvsu.math;

public class Vector3f {
    public final static float EPS = 1e-7f;
    private float x, y, z;

    public Vector3f(final float x, final float y, final float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean equals(final Vector3f other) {
        return Math.abs(x - other.x) < EPS && Math.abs(y - other.y) < EPS && Math.abs(z - other.z) < EPS;
    }

    public Vector3f add(final Vector3f vector3f) {
        return new Vector3f(this.x + vector3f.x, this.y + vector3f.y, this.z + vector3f.z);
    }

    public Vector3f subtract(final Vector3f vector3f) {
        return new Vector3f(this.x + (-vector3f.x), this.y + (-vector3f.y), this.z + (-vector3f.z));
    }

    public Vector3f multiply(final float scalar) {
        return new Vector3f(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public Vector3f divide(final float scalar) {
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

    public float dot(final Vector3f vector3f) {
        return this.x * vector3f.x + this.y * vector3f.y + this.z * vector3f.z;
    }

    public Vector3f cross(final Vector3f vector3f) {
        return new Vector3f(
                this.y * vector3f.z - this.z * vector3f.y,
                - this.z * vector3f.x + this.x * vector3f.z,
                this.x * vector3f.y - this.y * vector3f.x
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

    public void setX(final float x) {
        this.x = x;
    }

    public void setY(final float y) {
        this.y = y;
    }

    public void setZ(final float z) {
        this.z = z;
    }
}
