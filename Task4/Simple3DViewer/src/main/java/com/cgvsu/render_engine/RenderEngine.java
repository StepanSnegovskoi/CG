package com.cgvsu.render_engine;

import java.util.ArrayList;

import com.cgvsu.Utils.Rasterisator;
import com.cgvsu.Utils.Rendering_the_grid;
import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Polygon;
import javafx.scene.canvas.GraphicsContext;
import com.cgvsu.math.*;
import com.cgvsu.model.Model;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import static com.cgvsu.render_engine.GraphicConveyor.*;

public class RenderEngine {
    Rasterisator rasterisator;

    /**
     * Метод для отрисовки 3D-модели на 2D-канвасе.
     *
     * @param graphicsContext Контекст графики, используемый для рисования на канвасе.
     * @param camera          Камера, определяющая точку наблюдения, проекцию и видовые параметры.
     * @param mesh            3D-модель, содержащая вершины и полигоны.
     * @param width           Ширина канваса.
     * @param height          Высота канваса.
     */

    public void render(final GraphicsContext graphicsContext, final Camera camera, final Model mesh, final int width, final int height, Image texture, boolean flagTexture, boolean flagGrid, boolean flagLighting, Color color, double shadow) {
        if (texture != null && flagTexture) {
            rasterisator = new Rasterisator(texture, graphicsContext.getPixelWriter(), shadow, camera.getPosition(), flagLighting, true);
        } else {
            rasterisator = new Rasterisator(graphicsContext.getPixelWriter(), color, shadow, camera.getPosition(), flagLighting);
        }

        Matrix4d modelMatrix = rotateScaleTranslate();
        Matrix4d viewMatrix = camera.getViewMatrix();
        Matrix4d projectionMatrix = camera.getProjectionMatrix();

        Matrix4d modelViewProjectionMatrix = projectionMatrix.multiply(viewMatrix).multiply(modelMatrix);

        double[][] zBuffer = new double[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                zBuffer[x][y] = Double.NEGATIVE_INFINITY;
            }
        }

        // Количество полигонов в модели.
        final int nPolygons = mesh.polygons.size();

        // Перебор всех полигонов модели.
        for (int polygonInd = 0; polygonInd < nPolygons; ++polygonInd) {

            // Количество вершин в текущем полигоне.

            final Polygon polygon = mesh.polygons.get(polygonInd);
            final int nVerticesInPolygon = polygon.getVertexIndices().size();
            // Список для хранения преобразованных экранных координат вершин полигона.
            ArrayList<Vector3f> resultVectors = new ArrayList<>();
            ArrayList<Vector3f> polygonNormals = new ArrayList<>();
            ArrayList<Vector2f> polygonTexture = new ArrayList<>();
            ArrayList<Vector3f> polygonVertex = new ArrayList<>();
            // Перебор всех вершин текущего полигона.

            for (int vertexInPolygonInd = 0; vertexInPolygonInd < nVerticesInPolygon; vertexInPolygonInd++) {

                // Получение координат вершины из модели.
                Vector3f vertex = mesh.vertices.get(polygon.getVertexIndices().get(vertexInPolygonInd));
                if (nVerticesInPolygon == 3) {
                    if (flagLighting) {
                        Vector3f mirVertex = modelMatrix.multiply(vertex);
                        polygonVertex.add(mirVertex);
                        polygonNormals.add(mesh.normals.get(polygon.getNormalIndices().get(vertexInPolygonInd)));
                    }
                    if (flagTexture) {
                        polygonTexture.add(mesh.textureVertices.get(polygon.getTextureVertexIndices().get(vertexInPolygonInd)));
                    }
                }

                resultVectors.add(vertexToBord(multiplyMatrix4ByVector3(modelViewProjectionMatrix, vertex), width, height));
            }


            if (nVerticesInPolygon == 3) {
                if (texture != null || flagTexture || flagGrid || flagLighting) {
                    rasterisator.draw(resultVectors, polygonTexture, polygonVertex, polygonNormals, zBuffer);
                } else {
                    rasterisator.drawTriangle(graphicsContext, resultVectors, zBuffer);
                }
            }

//            Сетка полигональная
            if (nVerticesInPolygon > 1 && flagGrid) {
                Rendering_the_grid.DrawGrid(graphicsContext, resultVectors, zBuffer);
            }

        }

    }
}