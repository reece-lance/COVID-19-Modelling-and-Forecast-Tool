package csee.ce291_team02.MugStats.Models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class PclrSplineTests {

    private MugPoint b;
    private MugPoint d;
    private PclrSpline pclr;
    private ArrayList<MugPoint> knots;

    @Test
    void pclrSplineShouldInstantiateCorrectlyGivenSegmentPoints(){
        b = new MugPoint(2.5,1.5); //k0
        d = new MugPoint(3.5,0.5); //k1

        // a = 4.0, b= -1.0 is an answer from a constructor in MugLine(MugPoint k0, MugPoint k1) in MugLine class.
        knots = new ArrayList<MugPoint>();
        knots.add(b);
        knots.add(d);

        pclr = new PclrSpline(knots);

        Assertions.assertTrue((pclr.getPrediction(5.0).a).equals(8.5));  //8.5
        Assertions.assertTrue((pclr.getPrediction(5.0).b).equals(-4.5)); //(8.5*-1.0) + 4.0 = -4.5

        //throw new NotImplementedException();
    }

    //TODO: implement the rest of PcrlSplineTests
}
