package csee.ce291_team02.MugStats.Models;

import org.jetbrains.annotations.NotNull;

public class MugPoint implements Comparable<MugPoint> {
    public Double a;
    public Double b;

    public MugPoint(Double a, Double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "MP{" +
                "a=" + Math.round(a) +
                ", b=" + Math.round(b) + "} ";
    }

    /*
    * This is for demonstration purposes only.*/
    String packagePrivateMethod(){
        return "call me if you can";
    }

    @Override
    public int compareTo(@NotNull MugPoint o) {
        return this.a.compareTo(o.a);
    }
}
