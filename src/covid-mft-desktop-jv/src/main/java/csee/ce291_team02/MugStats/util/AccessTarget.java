package csee.ce291_team02.MugStats.util;

import csee.ce291_team02.MugStats.Models.MugPoint;

@FunctionalInterface
public interface AccessTarget {
    double access(MugPoint p);
}
