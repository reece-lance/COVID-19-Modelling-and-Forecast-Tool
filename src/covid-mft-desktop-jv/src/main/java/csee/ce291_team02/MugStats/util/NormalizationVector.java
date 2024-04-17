package csee.ce291_team02.MugStats.util;

import csee.ce291_team02.MugStats.Models.MugPoint;

import java.util.ArrayList;

/**
 * Helper for normalization of data as well as its reversed process.
 */
public class NormalizationVector {
    private double xFactor;
    private double yFactor;
    private double xMin;
    private double yMin;

    public NormalizationVector(double xMax, double yMax, double xMin, double yMin) {
        this.xFactor = xMax - xMin;
        this.yFactor = yMax - yMin;
        this.xMin = xMin;
        this.yMin = yMin;
    }
    public void normalizeData(ArrayList<MugPoint> data){
        for (MugPoint p: data){
            normalizePoint(p);
        }
    }

    public void normalizePoint(MugPoint p){
        p.a = normalizeX(p.a);
        p.b = normalizeY(p.b);
    }

    public double normalizeX(double x){
        return (x - xMin) / xFactor;
    }

    public double normalizeY(double y){
        return (y - yMin) / yFactor;
    }

    public void denormalizeData(ArrayList<MugPoint> data){
        for (MugPoint p: data){
            denormalize(p);
        }
    }

    public void denormalize(MugPoint p){
        p.a = denormalizeX(p.a);
        p.b = denormalizeY(p.b);
    }

    public double denormalizeX(double x){
        return (x * xFactor) + xMin;
    }

    public double denormalizeY(double y){
        return (y * yFactor) + yMin;
    }
}
