package com.cgvsu.utils;

import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DeleteVertices {

    public static Model deleteVerticesFromModel(Model model, List<Integer> vertexIndices) {
        List<Integer> vertexIndicesToDelete = new ArrayList<>(vertexIndices)
                .stream()
                .sorted(Comparator.reverseOrder())
                .toList();

        deleteVertices(model.vertices, vertexIndicesToDelete);

        deleteDanglingPolygons(model.polygons, vertexIndices);

        shiftIndicesInPolygons(model.polygons, vertexIndices);

        return model;
    }

    private static void deleteVertices(List<Vector3f> modelVertices, List<Integer> vertexIndicesToDelete) {
        for (Integer i : vertexIndicesToDelete) {
            modelVertices.remove(i.intValue());
        }
    }

    private static void deleteDanglingPolygons(List<Polygon> modelPolygons, List<Integer> vertexIndicesToDelete) {
        for (int i = modelPolygons.size() - 1; i >= 0; i--) {
            Polygon polygon = modelPolygons.get(i);

            boolean areVertexIndicesToDeletePresentInPolygon = polygon.getVertexIndices().stream()
                    .anyMatch(vertexIndicesToDelete::contains);

            if (areVertexIndicesToDeletePresentInPolygon) {
                modelPolygons.remove(i);
            }
        }
    }

    private static void shiftIndicesInPolygons(List<Polygon> modelPolygons, List<Integer> vertexIndicesToDelete) {
        List<Integer> sortedVertexIndicesToDelete = new ArrayList<>(vertexIndicesToDelete);
        sortedVertexIndicesToDelete.sort(Comparator.reverseOrder());

        for (Polygon polygon : modelPolygons) {
            ArrayList<Integer> newVertexIndices = new ArrayList<>();

            for (int polygonVertexIndex : polygon.getVertexIndices()) {
                int offset = countLessThan(polygonVertexIndex, sortedVertexIndicesToDelete);
                newVertexIndices.add(polygonVertexIndex - offset);
            }

            polygon.setVertexIndices(newVertexIndices);
        }
    }

    private static int countLessThan(int polygonVertexIndex, List<Integer> sortedVertexIndicesToDelete) {
        int result = 0;
        while (result < sortedVertexIndicesToDelete.size()) {
            if (polygonVertexIndex >= sortedVertexIndicesToDelete.get(result)) {
                result++;
            } else {
                break;
            }
        }
        return result;
    }
}
