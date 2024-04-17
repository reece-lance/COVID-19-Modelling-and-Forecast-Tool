package csee.ce291_team02.MugStats.util;

import csee.ce291_team02.MugStats.Models.MugPoint;

import java.util.ArrayList;

/**
 * Normalizes specific DTOs
 */
public class Normalization {

    /**
     * Normalizes the incoming data to to values and retrieves the normalization vector.
     * @param data
     * @return Vectors of factors used to normalize the data for reversing of the normalization.
     */
    public static NormalizationVector normalizeData(ArrayList<MugPoint> data){
        double xMax = Integer.MIN_VALUE;
        double xMin = Integer.MAX_VALUE;

        double yMax = Integer.MIN_VALUE;
        double yMin = Integer.MAX_VALUE;

        for (MugPoint dp: data){
            if(dp.a > xMax){
                xMax = dp.a;
            }
            if(dp.a < xMin){
                xMin = dp.a;
            }
            if(dp.b > yMax){
                yMax = dp.b;
            }
            if(dp.b < yMin){
                yMin = dp.b;
            }
        }

        NormalizationVector nv = new NormalizationVector(xMax,yMax, yMax, xMin);
        for (int i = 0; i < data.size(); i++) {
            MugPoint p = data.get(i);

            nv.normalizePoint(p);
        }

        return nv;
    }


}
