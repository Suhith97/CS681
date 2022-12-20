package edu.umb.cs681.hw20;

import java.io.IOException;
import java.util.*;

public class NasaPower {
  public static void main(String[] args) throws IOException {
    API api = new API();

    String startDate = "20220101"; // January 1st 2022
    String endDate = "20220930"; // September 30th 2022

    System.out.println("RH2M = Relative Humidity at 2 Meters");
    System.out.println("GWETROOT = Root Zone Soil Wetness");

    Map<String, List<Double>> latitudeLongitude = new HashMap<>();

    latitudeLongitude.put("Boston", Arrays.asList(42.360081, -71.058884));
    latitudeLongitude.put("New York", Arrays.asList(40.712776, -74.005974));
    latitudeLongitude.put("Detroit", Arrays.asList(42.331429, -83.045753));
    latitudeLongitude.put("Seattle", Arrays.asList(47.606209, -122.332069));
    latitudeLongitude.put("Los Angeles", Arrays.asList(34.052235, -118.243683));
    latitudeLongitude.put("Austin", Arrays.asList(30.2711286, -97.7436995));
    latitudeLongitude.put("Arlington", Arrays.asList(32.7355816, -97.1071186));
    latitudeLongitude.put("Dallas", Arrays.asList(32.7762719, -96.7968559));
    latitudeLongitude.put("New Hampshire", Arrays.asList(43.4849133, -71.6553992));
    latitudeLongitude.put("Kansas", Arrays.asList(38.27312, -98.5821872));


    for (Map.Entry<String, List<Double>> location : latitudeLongitude.entrySet()) {

      System.out.println(location.getKey() + ":");
      List<String> params = List.of(new String[]{"RH2M", "GWETROOT"});

      String fileName = startDate + "-" + endDate + ".csv";
      api.getCSVFile(location.getValue().get(0), location.getValue().get(1), startDate, endDate,
              params, fileName);

      List<List<String>> data = api.parseCSVFile(fileName);

      // calculate average/mean min and max RH2M and GWETROOT for each location
      int dayCounter = 0;
      double minRH2M = 0.0;
      double maxRH2M = 99999.0;
      double averageRH2M = 0.0;

      double minGWETROOT = 0.0;
      double maxGWETROOT = 99999.0;
      double averageGWETROOT = 0.0;

      for (List<String> d : data) {
        try {
          double valueRH2M = Double.parseDouble(d.get(3));
          averageRH2M += valueRH2M;
          if (valueRH2M > maxRH2M) {
            maxRH2M = valueRH2M;
          }
          if (valueRH2M < minRH2M) {
            minRH2M = valueRH2M;
          }

          double valueGWETROOT = Double.parseDouble(d.get(2));
          averageGWETROOT += valueGWETROOT;
          if (valueGWETROOT > maxGWETROOT) {
            maxGWETROOT = valueGWETROOT;
          }
          if (valueGWETROOT < minGWETROOT) {
            minGWETROOT = valueGWETROOT;
          }
          dayCounter++;
        } catch (Exception e) {
            //e.printStackTrace();
        }
      }

      averageRH2M = averageRH2M / dayCounter;
      averageGWETROOT = averageGWETROOT / dayCounter;

      System.out.println("Mean RH2M: " + averageRH2M);
      System.out.println("Min RH2M: " + minRH2M);
      System.out.println("Max RH2M: " + maxRH2M);
      System.out.println("Mean GWETROOT: " + averageGWETROOT);
      System.out.println("Min GWETROOT: " + minGWETROOT);
      System.out.println("Max GWETROOT: " + maxGWETROOT + "\n\n");

    }
  }
}
