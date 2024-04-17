package csee.ce291_team02.MugStats.Models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


//import sun.reflect.generics.reflectiveObjects.NotImplementedException;




public class MugLineTests {

    private MugLine a;
    private MugPoint b;
    private MugPoint c;

    @Test
    void mugLineShouldInstantiateUsingDoubleConstructor(){

         a = new MugLine(2.5,1.5);
         Assertions.assertEquals(a.getY(2.5),6.25);
        //throw new NotImplementedException();
    }

    @Test
    void mugLineShouldInstantiateUsingPointConstructor(){

        b = new MugPoint(2.5,1.5);
        c = new MugPoint(3.5,0.5);
        a = new MugLine(b,c);

        Assertions.assertEquals(a.a,4.0,"It didnt work.");
        Assertions.assertEquals(a.b,-1.0,"It didnt work.");
        //Assertions.assertEquals();
        // throw new NotImplementedException();
    }

    @Test
    void mugLineShouldReturnValidLowerBoundaryYaxisValue(){
        a = new MugLine(10.5,3.5);
        boolean ans = 11.75 - (a.getY(0.3571)) < 0.001 ;
        Assertions.assertTrue(ans);
        //11.74985
        //Desmos
        //Y = 11.75
        // y = 3.5x+10.5




        //throw new NotImplementedException();
    }

    @Test
    void mugLineShouldReturnValidUpperBoundaryYaxisValue(){
        a = new MugLine(10.5,3.5);
        boolean ans = 23.5 - (a.getY(3.714)) < 0.001 ;
        Assertions.assertTrue(ans);
        //Desmos
        //Y = 23.5
        // y = 3.5x+10.5
        //23.499000000000002

    }

    @Test
    void mugLineShouldReturnValidYaxisValues(){
        a = new MugLine(2.5,1.5);

        Assertions.assertTrue(a.getY(2.5) == 6.25);

    }
}
