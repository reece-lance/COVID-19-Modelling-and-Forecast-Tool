package csee.ce291_team02.MugStats.Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MugPointTests {
    @Test
    void justAnExample() {
//        if a method does not throw an exception or no assertions fail, the test passes
    }

//    Assertion methods can be looked up here: https://junit.org/junit5/docs/5.0.1/api/org/junit/jupiter/api/Assertions.html
    @Test
    void PointP1ShouldBeGreaterThanPointP2(){
        MugPoint p1 = new MugPoint(1d, 1d);
        MugPoint p2 = new MugPoint(0d,0d);

        assertTrue(p1.compareTo(p2) == 1);
    }

    /*
    * Package private-methods are methods without an access modifier.
    * This will work because as you can see on the top, the package name
    * matches with the one of the called class. Everything which should be
    * private and we want to unit test shall thus be package-private.
    * One can even call package-private methods anywhere inside of the app.
    * This would not work if the code were to be imported into an external
    * application/libraries.*/
    @Test
    void testPackagePrivateMethod(){
        MugPoint p1 = new MugPoint(0d,0d);
        String result = p1.packagePrivateMethod();

        assertNotNull(result, "Package private method's return value should not be empty.");
    }
}
