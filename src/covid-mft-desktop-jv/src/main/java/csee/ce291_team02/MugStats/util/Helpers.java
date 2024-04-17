package csee.ce291_team02.MugStats.util;

import csee.ce291_team02.MugStats.Models.MugPoint;

import java.time.LocalDate;
import java.util.List;

public class Helpers {
    public static class Calc {
        public static double sum(List<MugPoint> points, AccessTarget target) {
            double result = 0;

            for (MugPoint point : points) {
                result += target.access(point);
            }

            return result;
        }
    }

    public static double convertToEpochDay(LocalDate date){
        return (Long.valueOf(date.toEpochDay())).doubleValue();
    }
}
