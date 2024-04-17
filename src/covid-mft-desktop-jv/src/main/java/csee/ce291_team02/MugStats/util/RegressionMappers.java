package csee.ce291_team02.MugStats.util;


import csee.ce291_team02.Models.CovidCaseByDate;
import csee.ce291_team02.MugStats.Models.MugPoint;

import java.awt.geom.Point2D;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegressionMappers {
    public static List<Point2D> covidCasesByDatesToPoints(List<CovidCaseByDate> covidCaseByDates) {
        ArrayList<Point2D> result = new ArrayList<Point2D>();


        for (CovidCaseByDate c : covidCaseByDates) {
            result.add(covidCaseByDateToPoint(c));
        }

        return result;
    }

    public static Point2D covidCaseByDateToPoint(CovidCaseByDate covidCaseByDate) {
        double numericDate = (Long.valueOf(covidCaseByDate.Date.toEpochDay())).doubleValue();
        return new Point2D.Double(numericDate, covidCaseByDate.NewCasesBySpecimenDate);
    }

    public static ArrayList<MugPoint> covidCasesByDatesToMyPointsByNewCases(List<CovidCaseByDate> covidCaseByDates) {
        ArrayList<MugPoint> result = new ArrayList<>();


        for (CovidCaseByDate c : covidCaseByDates) {
            result.add(covidCaseByDateToMyPointByNewCases(c));
        }

        return result;
    }

    public static ArrayList<MugPoint> covidCasesByDatesToMyPointsByCumCases(List<CovidCaseByDate> covidCaseByDates) {
        ArrayList<MugPoint> result = new ArrayList<>();


        for (CovidCaseByDate c : covidCaseByDates) {
            result.add(covidCaseByDateToMyPointbyCumCases(c));
        }

        return result;
    }

    public static MugPoint covidCaseByDateToMyPointByNewCases(CovidCaseByDate covidCaseByDate) {
        double numericDate = (Long.valueOf(covidCaseByDate.Date.toEpochDay())).doubleValue();
        return new MugPoint(numericDate, (double) covidCaseByDate.NewCasesBySpecimenDate);
    }

    public static MugPoint covidCaseByDateToMyPointbyCumCases(CovidCaseByDate covidCaseByDate) {
        double numericDate = (Long.valueOf(covidCaseByDate.Date.toEpochDay())).doubleValue();
        return new MugPoint(numericDate, (double) covidCaseByDate.CumCasesBySpecimenDate);
    }

    public static ArrayList<Double> toDoubleEpochDayArray(List<LocalDate> dates) {
        ArrayList<Double> result = new ArrayList<>();

        for (LocalDate d : dates) {
            result.add(Helpers.convertToEpochDay(d));
        }

        return result;
    }
}
