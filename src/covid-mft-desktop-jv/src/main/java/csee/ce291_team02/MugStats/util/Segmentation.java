package csee.ce291_team02.MugStats.util;

import csee.ce291_team02.MugLogger;
import csee.ce291_team02.MugStats.Models.MugPoint;

import java.util.ArrayList;
import java.util.Comparator;

public class Segmentation {
    /**
     * Separates the incoming data into different segments by individual segment's x coordinates.
     * @param data Data to be segmented.
     * @param segmentBoundaries x-axis values of segment boundaries
     * @return boundariesCount - 1 segments
     */
    public static ArrayList<ArrayList<MugPoint>> segmentate(ArrayList<MugPoint> data, ArrayList<Double> segmentBoundaries) throws Exception {

        // Ensure values are sorted by x (a)
        data.sort(Comparator.comparingDouble(value -> (value.a)));

        Double minSegmentBoundary = segmentBoundaries.stream().min(Double::compare).get();
        Double maxSegmentBoundary = segmentBoundaries.stream().max(Double::compare).get();

        ArrayList<ArrayList<MugPoint>> segments = new ArrayList<>();

        int segmentCount = 0;
        // We start at 1 because minSegmentBoundary will already cover the most-left boundary
        double currentSegmentBoundary = segmentBoundaries.get(segmentCount + 1);

        segments.add(new ArrayList<>());

        // For all data items
        for (int i = 0; i < data.size(); i++) {
            MugPoint currentPoint = data.get(i);

            // If current point does not fit within the boundaries given, we will ignore it
            if(currentPoint.a < minSegmentBoundary || currentPoint.a > maxSegmentBoundary){
                continue;
            }

            // If the point fits (right inclusive0 within the range of the segment
            if(currentSegmentBoundary >= data.get(i).a){
                segments.get(segmentCount).add(data.get(i));
                continue;
            }

            segmentCount += 1;

            // If we filled all segments and there is still more data return
            if(segmentCount > segmentBoundaries.size()){
                break;
            }

            // If the current segment is empty, go on and reuse it in next iteration
            if(segments.get(segmentCount - 1).size() == 0){
                MugLogger.log(String.format("Segment empty {currentSegmentBoundary=%d, segmentCount=%d}", currentSegmentBoundary,segmentCount));
            } else { // Initiate new segment
                logSegmentClosed(segments, segmentCount - 1);
                segments.add(new ArrayList<>());
                currentSegmentBoundary = segmentBoundaries.get(segmentCount + 1);
            }

            // Re-evaluate this point again for the new segment
            i--;
        }

        logSegmentClosed(segments, segmentCount - 1);
        return segments;
    }

    private static void logSegmentClosed(ArrayList<ArrayList<MugPoint>> segments, int index){
        MugLogger.log(String.format("Segment closed {index=%d, start=%.2f, end=%.2f", index, segments.get(index).get(0).a, segments.get(index).get(segments.get(index).size() - 1).a));
    }
}
