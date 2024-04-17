import tech.tablesaw.api.DateColumn;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Metric {
    public Table data;

    Metric(Table data){
        data = data.sortOn("date");
        int[] i = IntStream.range(0,data.rowCount()).toArray();
        DoubleColumn index = DoubleColumn.create("index",i);
        data.addColumns(index);
        this.data = data;
    }

    //date format "yyyy/mm/dd"
    public LocalDate[] timeInterval() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/d");
        LocalDate startDate = this.data.dateColumn("date").get(0);
        LocalDate endDate = this.data.dateColumn("date").get(this.data.rowCount()-1);

        return new LocalDate[] {startDate,endDate};
    }

    public Double mean (String colName){
        DoubleColumn col = this.data.doubleColumn(colName);
        Double sum = (double)0;
        for (Double num : col){
            sum += num;
        }
        Double mean = sum/col.size();
        //System.out.println(col.mean());
        return mean;
    }

    public Double sd (String colName){
        DoubleColumn x = this.data.doubleColumn(colName);
        Double sd = (double)0;
        for (int i=0 ; i<x.size() ; i++){
            sd += Math.pow((x.get(i) - mean(colName)),2);
        }
        sd = Math.sqrt(sd/(x.size()-1));
        //System.out.println(x.standardDeviation());
        return sd;
    }

    public Double[] range (String colName){
        DoubleColumn col = this.data.doubleColumn(colName);
        Double[] range = new Double[2];
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (double d : col){
            if (d <= min){
                range[0] = d;
                min = d;
            } else if (d >= max){
                range[1] = d;
                max = d;
            }
        }
        //System.out.println("col.min() + "," + col.max());
        return range;
    }

    public Double corr (String colName){
        DoubleColumn y = this.data.doubleColumn(colName);
        DoubleColumn x = this.data.doubleColumn("index");

        Double corr = (double)0;
        for (int i=0 ; i<y.size() ; i++){
            corr += (x.get(i)-mean("index"))*(y.get(i)-mean(colName));
        }

        corr = corr/((y.size()-1)*sd(colName)*sd("index"));

        return corr;
    }

    public Double slope (String colName){
        return corr(colName)*sd(colName)/sd("index");
    }

    public Double intercept (String colName){
        return  mean(colName) - slope(colName)*mean("index");
    }

    //date format "yyyy/mm/dd"
    public Double predict(String colName, String date) throws ParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/d");
        LocalDate date1 = this.data.dateColumn("date").get(0);
        LocalDate date2 = LocalDate.parse(date,format);
        long diff = Math.abs(date1.getDayOfYear()-date2.getDayOfYear());

        Double predict = intercept(colName)+slope(colName)*diff;
        return predict;
    }

    public Double mse(String colName) throws ParseException {
        Double mse = (double)0;
        int[] index = IntStream.range(0,data.rowCount()).toArray();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/d");

        for (int i : index){
            Double p = predict(colName,this.data.dateColumn("date").get(i).format(format));
            mse += Math.pow(this.data.doubleColumn(colName).get(i)-p,2);
        }
        mse = mse/this.data.rowCount();

        return mse;
    }

    public static void main(String[] arg) throws IOException, ParseException {
        String path = "C:\\Users\\So Wai\\Desktop\\Metric\\example.csv";

        Table table = Table.read().csv(path);
        Metric m = new Metric(table);
        List<String> colNames = table.columnNames();
        String cases = colNames.get(4);
/*
        System.out.println("time interval : "+m.timeInterval()[0]+","+m.timeInterval()[1]);
        System.out.println("mean : "+m.mean(cases));
        System.out.println("range : "+m.range(cases)[0]+","+m.range(cases)[1]);
        System.out.println("correlation : "+m.corr(cases));
        System.out.println("Linear regression : \ny = "+m.slope(cases)+"x + "+m.intercept(cases));
        System.out.println("prediction of 7 days later : " + m.predict(cases,"2020/11/10"));
        System.out.println("mean square error : " + m.mse(cases));

 */

    }

}
