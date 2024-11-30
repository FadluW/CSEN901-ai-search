package de.giuberlin.exceptions;

import java.awt.*;
import java.security.InvalidParameterException;

public class InvalidNeighboursException extends InvalidParameterException {
    private static final String preamble = "Invalid coordinates for nodes at: ";

    public InvalidNeighboursException(Point point1, Point point2) {
        super(preamble + "(" + point1.x + ", " + point1.y + ") (" + point2.x + ", " + point2.y + ")");
    }
}
