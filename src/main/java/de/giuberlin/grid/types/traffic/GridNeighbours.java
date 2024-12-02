package de.giuberlin.grid.types.traffic;

import de.giuberlin.exceptions.InvalidNeighboursException;
import de.giuberlin.search.NodePath;
import de.giuberlin.grid.types.GridObject;

public class GridNeighbours {
    private VerticalTrafficSegment northSegment = new VerticalTrafficSegment();
    private VerticalTrafficSegment southSegment = new VerticalTrafficSegment();
    private HorizontalTrafficSegment eastSegment = new HorizontalTrafficSegment();
    private HorizontalTrafficSegment westSegment = new HorizontalTrafficSegment();

    public GridObject getNeighbour(NodePath.Direction direction) {
        switch(direction) {
            case UP -> {
                return northSegment.getNorthObject();
            }
            case DOWN -> {
                return southSegment.getSouthObject();
            }
            case LEFT -> {
                return westSegment.getWest();
            }
            case RIGHT -> {
                return eastSegment.getEast();
            }
            default -> throw new IllegalArgumentException("Unsupported Neighbour Direction");
        }
    }

    public int getCost(NodePath.Direction direction) {
        switch(direction) {
            case UP -> {
                return northSegment.getTraffic();
            }
            case DOWN -> {
                return southSegment.getTraffic();
            }
            case LEFT -> {
                return westSegment.getTraffic();
            }
            case RIGHT -> {
                return eastSegment.getTraffic();
            }
            default -> throw new IllegalArgumentException("Unsupported Neighbour Direction");
        }
    }

    public void setNorthSegment(VerticalTrafficSegment northSegment) {
        this.northSegment = northSegment;
    }

    public void setSouthSegment(VerticalTrafficSegment southSegment) {
        this.southSegment = southSegment;
    }

    public void setEastSegment(HorizontalTrafficSegment eastSegment) {
        this.eastSegment = eastSegment;
    }

    public void setWestSegment(HorizontalTrafficSegment westSegment) {
        this.westSegment = westSegment;
    }

    public VerticalTrafficSegment createNorthNeighbourFromSelf(GridObject self, GridObject neighbour, int traffic) {
        if (self.getCoords().x != neighbour.getCoords().x || neighbour.getCoords().y - self.getCoords().y != 1) {
            throw new InvalidNeighboursException(self.getCoords(), neighbour.getCoords());
        }

        northSegment.setNorthObject(neighbour);
        northSegment.setSouthObject(self);
        northSegment.setTraffic(traffic);

        return northSegment;
    }

    public HorizontalTrafficSegment createEastNeighbourFromSelf(GridObject self, GridObject neighbour, int traffic) {
        if (self.getCoords().y != neighbour.getCoords().y || neighbour.getCoords().x - self.getCoords().x != 1) {
            throw new InvalidNeighboursException(self.getCoords(), neighbour.getCoords());
        }

        eastSegment.setEastObject(neighbour);
        eastSegment.setWestObject(self);
        eastSegment.setTraffic(traffic);

        return eastSegment;
    }

    public VerticalTrafficSegment createSouthNeighbourFromSelf(GridObject self, GridObject neighbour, int traffic) {
        if (self.getCoords().x != neighbour.getCoords().x || self.getCoords().y - neighbour.getCoords().y != 1) {
            throw new InvalidNeighboursException(self.getCoords(), neighbour.getCoords());
        }

        southSegment.setSouthObject(neighbour);
        southSegment.setNorthObject(self);
        southSegment.setTraffic(traffic);

        return southSegment;
    }

    public HorizontalTrafficSegment createWestNeighbourFromSelf(GridObject self, GridObject neighbour, int traffic) {
        if (self.getCoords().y != neighbour.getCoords().y || self.getCoords().x - neighbour.getCoords().x != 1) {
            throw new InvalidNeighboursException(self.getCoords(), neighbour.getCoords());
        }

        westSegment.setWestObject(neighbour);
        westSegment.setEastObject(self);
        westSegment.setTraffic(traffic);

        return westSegment;
    }
}
