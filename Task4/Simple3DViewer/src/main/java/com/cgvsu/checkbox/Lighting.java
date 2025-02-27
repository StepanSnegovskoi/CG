package com.cgvsu.checkbox;

import com.cgvsu.math.Vector3f;
import javafx.scene.paint.Color;

public class Lighting {

    private static final int MAX_COLOR_VALUE = 255;
    final static double k = 0.5;

    public static void calculateLight(int[] rgb, double[] light, Vector3f normal){
        double l = -(light[0] * normal.x + light[1] * normal.y + light[2] * normal.z);

        if (l < 0) {
            l = 0;
        }

        rgb[0] = Math.min(MAX_COLOR_VALUE, (int) (rgb[0] * (1 - k) + rgb[0] * k * l));
        rgb[1] = Math.min(MAX_COLOR_VALUE, (int) (rgb[1] * (1 - k) + rgb[1] * k * l));
        rgb[2] = Math.min(MAX_COLOR_VALUE, (int) (rgb[2] * (1 - k) + rgb[2] * k * l));
    }

    public static int[] getGradientCoordinatesRGB(final double[] baristicCoords, final Color[] color) {
        int r = Math.min(MAX_COLOR_VALUE, (int) Math.abs(
                color[0].getRed()   * 255 * baristicCoords[0]
                        + color[1].getRed()   * 255 * baristicCoords[1]
                        + color[2].getRed()   * 255 * baristicCoords[2])
        );
        int g = Math.min(MAX_COLOR_VALUE, (int) Math.abs(
                color[0].getGreen() * 255 * baristicCoords[0]
                        + color[1].getGreen() * 255 * baristicCoords[1]
                        + color[2].getGreen() * 255 * baristicCoords[2])
        );
        int b = Math.min(MAX_COLOR_VALUE, (int) Math.abs(
                color[0].getBlue()  * 255 * baristicCoords[0]
                        + color[1].getBlue()  * 255 * baristicCoords[1]
                        + color[2].getBlue()  * 255 * baristicCoords[2])
        );

        return new int[] { r, g, b };
    }

    public static Vector3f smoothingNormal(final double[] baristicCoords, final Vector3f[] normals) {
        float nx = (float) (baristicCoords[0] * normals[0].x
                + baristicCoords[1] * normals[1].x
                + baristicCoords[2] * normals[2].x);
        float ny = (float) (baristicCoords[0] * normals[0].y
                + baristicCoords[1] * normals[1].y
                + baristicCoords[2] * normals[2].y);
        float nz = (float) (baristicCoords[0] * normals[0].z
                + baristicCoords[1] * normals[1].z
                + baristicCoords[2] * normals[2].z);

        return new Vector3f(nx, ny, nz);
    }

    public static void light(final double[] barizentric, final Vector3f[] normals, double[] light, int[] rgb){
        Vector3f smooth = smoothingNormal(barizentric, normals);
        calculateLight(rgb, light, smooth);
    }
}
