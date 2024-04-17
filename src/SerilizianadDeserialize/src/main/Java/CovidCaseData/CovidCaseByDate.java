package main.Java.CovidCaseData;


import java.time.LocalDate;

public class CovidCaseByDate {
    public String AreaType;
    public String AreaName;
    public String AreaCode;
    public LocalDate Date;
    public int NewCasesBySpecimenDate;
    public int CumCasesBySpecimenDate;

    public CovidCaseByDate(String areaType, String areaName, String areaCode, LocalDate date, int newCasesBySpecimenDate, int cumCasesBySpecimenDate) {
        AreaType = areaType;
        AreaName = areaName;
        AreaCode = areaCode;
        Date = date;
        NewCasesBySpecimenDate = newCasesBySpecimenDate;
        CumCasesBySpecimenDate = cumCasesBySpecimenDate;
    }

    @Override
    public String toString() {
        return "CovidCaseByDate{" +
                "AreaType='" + AreaType + '\'' +
                ", AreaName='" + AreaName + '\'' +
                ", AreaCode='" + AreaCode + '\'' +
                ", Date=" + Date +
                ", NewCasesBySpecimenDate=" + NewCasesBySpecimenDate +
                ", CumCasesBySpecimenDate=" + CumCasesBySpecimenDate +
                '}';
    }





    /*public static void main(String[] args) {*/
       /* var data = new ArrayList<CovidCaseByDate>();
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 4),
                1434,
                1123197
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 3),
                14512,
                1121763
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 2),
                29142,
                1107251
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 1),
                15419,
                1078109
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 4),
                1434,
                1123197
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 3),
                14512,
                1121763
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 2),
                29142,
                1107251
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 1),
                15419,
                1078109
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 4),
                1434,
                1123197
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 3),
                14512,
                1121763
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 2),
                29142,
                1107251
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 1),
                15419,
                1078109
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 4),
                1434,
                1123197
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 3),
                14512,
                1121763
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 2),
                29142,
                1107251
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 1),
                15419,
                1078109
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 4),
                1434,
                1123197
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 3),
                14512,
                1121763
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 2),
                29142,
                1107251
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 1),
                15419,
                1078109
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 4),
                1434,
                1123197
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 3),
                14512,
                1121763
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 2),
                29142,
                1107251
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 1),
                15419,
                1078109
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 4),
                1434,
                1123197
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 3),
                14512,
                1121763
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 2),
                29142,
                1107251
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 1),
                15419,
                1078109
        ));

        Gson gson = new Gson();

        String jsonString = gson.toJson(data);

        String b = jsonString;
        System.out.println(b);


    }
*/

}
