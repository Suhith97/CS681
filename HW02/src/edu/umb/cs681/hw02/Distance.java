
package edu.umb.cs681.hw02;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Distance {
	public static void main(String[] args) {

    //System.out.println(d.get(Arrays.asList(10.0, 5.0), Arrays.asList(7.0, 3.0)));

    DistanceMetric manhattanDistance =
            (List<Double> p1, List<Double> p2) -> {
      return Math.abs(p1.get(0) - p2.get(0)) + Math.abs(p1.get(1) - p2.get(1));
    };

    double result = get(Arrays.asList(10.0, 5.0), Arrays.asList(7.0, 3.0), manhattanDistance);
    System.out.println(result);
    List<List<Double>> matrix = matrix(List.of(Arrays.asList(7.0,8.0), Arrays.asList(6.0,5.0),
            Arrays.asList(1.0,2.0), Arrays.asList(3.0,5.0), Arrays.asList(8.0,4.0)), manhattanDistance);
    System.out.println(matrix);

  }


	
	public static double get(List<Double> p1, List<Double> p2, DistanceMetric metric) {
		return metric.distance(p1, p2);
	}
	
	
	public static List<List<Double>> matrix(List<List<Double>> points, DistanceMetric metric) {
		// TODO Make this method more efficient by taking advantage of 
		// the symmetric nature of a distance matrix. For example, reduce
		// the number of distances to compute, from (numOfPoints)*(numOfPoints)
		// to ((numOfPoints-1)*(numOfPoints-1))/2.  
		int numOfPoints = points.size();
		List<List<Double>> distanceMatrix = Distance.initDistanceMatrix(numOfPoints);
		List<Double> current, peer;

		for(int i=0; i < numOfPoints; i++) {
			current = points.get(i);
			for(int j=0; j < numOfPoints; j++) {
				peer = points.get(j);
				double distance = Distance.get(current, peer, metric);
				distanceMatrix.get(i).set(j, distance);
			}
		}
		return distanceMatrix;
	}
	
	private static List<List<Double>> initDistanceMatrix(int numOfPoints){
		List<List<Double>> distanceMatrix = new ArrayList<>(numOfPoints);
		for(int i=0; i < numOfPoints; i++) {
			Double[] vector = new Double[numOfPoints];
			Arrays.fill(vector, 0.0);
			distanceMatrix.add( Arrays.asList(vector) );
		}
		return distanceMatrix;
	}

}

