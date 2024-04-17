package csee.ce291_team02.MugStats.Models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MugSplineTests {

    private MugLine a ;
    private MugPoint b;
    private MugPoint d;
    private MugPoint e;
    private MugPoint f;
    private MugSpline c;

    private ArrayList<MugLine> segments ;
    private ArrayList<MugLine> segments1 ;

    private ArrayList<MugPoint> knots;

    @Test
    void mugSplineShouldInstantiateWithCorrectNumberOfSegments(){

        b = new MugPoint(2.5,1.5);
        d = new MugPoint(3.0,1.0);
        e = new MugPoint(2.0,2.0);
        knots = new ArrayList<MugPoint>();
        knots.add(b);
        knots.add(d);
        knots.add(e);

         c = new MugSpline(knots);
         Assertions.assertEquals(c.segments.size(),2);
        //System.out.println(c.segments.size());

        //throw new NotImplementedException();
    }

    @Test
    void mugSplineShouldReturnValidYaxisValuesForSegmentBoundaries(){

        b = new MugPoint(2.5,1.5); //k0
        d = new MugPoint(3.5,0.5); //k1
        // a = 4.0, b= -1.0 is an answer from a constructor in MugLine(MugPoint k0, MugPoint k1) in MugLine class.

        //Calculate Y
        //c.getY(3)

        knots = new ArrayList<MugPoint>();
        knots.add(b);
        knots.add(d);
        //There is only one segment.


        c = new MugSpline(knots);
        Assertions.assertEquals(c.getY(3),1.0);

        //throw new NotImplementedException();
    }

    @Test
    void mugSplineShouldReturnValidYaxisValuesForSegmentInnerPoints(){

        b = new MugPoint(1.8,1.74);
        d = new MugPoint(3.41,3.6);
        e = new MugPoint(5.5,1.55);
        f = new MugPoint(6.55,3.85);

        knots = new ArrayList<MugPoint>();
        //3 segments created.
        knots.add(b);
        knots.add(d);
        knots.add(e);
        knots.add(f);

        c = new MugSpline(knots);

        //Expected Y = 3;
        c.getY(4.022);
        boolean ans = 3 - (c.getY(4.022)) < 0.01;
        Assertions.assertTrue(ans);




        //throw new NotImplementedException();
    }

    @Test
    void mugSplineShouldBeResilientToUnorderedKnotEntries(){

        b = new MugPoint(1.8,1.74);
        d = new MugPoint(3.41,3.6);
        e = new MugPoint(5.5,1.55);
        f = new MugPoint(6.55,3.85);

        knots = new ArrayList<MugPoint>();
        //3 segments created. Adding knots in unordered entries into the arraylist.
        knots.add(f);
        knots.add(b);
        knots.add(d);
        knots.add(e);

        c = new MugSpline(knots);

        //Expected Y = 3;
        c.getY(4.022);

        c.getY(4.022);
        boolean ans = 3 - (c.getY(4.022)) < 0.01;
        Assertions.assertTrue(ans);

        //throw new NotImplementedException();
    }
}
