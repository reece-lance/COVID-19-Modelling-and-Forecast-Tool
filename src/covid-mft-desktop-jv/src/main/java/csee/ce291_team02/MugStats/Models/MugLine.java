package csee.ce291_team02.MugStats.Models;

public class MugLine {
    public Double a;
    public Double b;

    public MugLine(Double a, Double b) {
        this.a = a;
        this.b = b;
    }

    public MugLine(MugPoint k0, MugPoint k1) {
        a = (k1.b - k0.b) / (k1.a - k0.a) * (-k0.a) + k0.b;
        b = (k1.b - k0.b) / (k1.a - k0.a);
    }

    public double getY(double x) {
        return b * x + a;
    }

    @Override
    public String toString() {
        return "ML{" +
                "a=" + a +
                ", b=" + b +
                "} ";
    }
}
