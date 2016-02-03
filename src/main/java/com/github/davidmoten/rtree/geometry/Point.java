package com.github.davidmoten.rtree.geometry;

import com.github.davidmoten.util.ObjectsHelper;
import com.google.common.base.Objects;
import com.google.common.base.Optional;

public final class Point implements Geometry {

    private final Box mbr;

    protected Point(float x, float y) {
        this.mbr = Box.create(x, y, x, y);
    }

    public static Point create(double x, double y) {
        return new Point((float) x, (float) y);
    }

    @Override
    public Box mbr() {
        return mbr;
    }

    @Override
    public double distance(Box r) {
        return mbr.distance(r);
    }

    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }

    public double distanceSquared(Point p) {
        float dx = mbr().x1() - p.mbr().x1();
        float dy = mbr().y1() - p.mbr().y1();
        return dx * dx + dy * dy;
    }

    @Override
    public boolean intersects(Box r) {
        return mbr.intersects(r);
    }

    public float x() {
        return mbr.x1();
    }

    public float y() {
        return mbr.y1();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mbr);
    }

    @Override
    public boolean equals(Object obj) {
        Optional<Point> other = ObjectsHelper.asClass(obj, Point.class);
        if (other.isPresent()) {
            return Objects.equal(mbr, other.get().mbr());
        } else
            return false;
    }

    @Override
    public String toString() {
        return "Point [x=" + x() + ", y=" + y() + "]";
    }

}