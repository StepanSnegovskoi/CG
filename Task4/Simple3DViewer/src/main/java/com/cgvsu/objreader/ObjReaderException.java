package com.cgvsu.objreader;

public class ObjReaderException extends RuntimeException {
    public ObjReaderException(String errorMessage, int lineInd) {
        super("Error parsing OBJ file on line: " + lineInd + ". " + errorMessage);
    }

    public ObjReaderException(String errorMessage, int lineInd, NumberFormatException e) {
        super(String.format("Error parsing OBJ file on line: %s. Failed to parse float value.", Integer.toString(lineInd)));
    }
}