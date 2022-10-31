package edu.umb.cs681.hw06;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class API {

  void getCSVFile(double latitude, double longitude, String startTime, String endTime,
                  List<String> parameters, String fileName) throws IOException {
    String parameterString = parameters.stream().collect(Collectors.joining(","));

    URL url = new URL
            (String.format("https://power.larc.nasa.gov/api/temporal/daily/point?" +
                            "parameters=%s" + "&community=AG" +
                            "&longitude=%f&latitude=%f&start=%s&end=%s&" +
                            "format=CSV"
            ,parameterString, longitude, latitude, startTime, endTime));

    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
    String s = null;
    FileOutputStream outputStream = new FileOutputStream(fileName);

    boolean skipHeader = false;
    while ((s=in.readLine())!=null) {
      if (s.contains("-END HEADER-")) {
        skipHeader = true;
        continue;
      }
      if(!skipHeader) {
        continue;
      }
      byte[] strToBytes = (s+"\n").getBytes();
      outputStream.write(strToBytes);
    }

    outputStream.close();
  }

  List<List<String>> parseCSVFile(String fileName) {
    Path path = Paths.get(fileName);
    List<List<String>> data = null;
    try( Stream<String> lines = Files.lines(path) ){
      data = lines.map(line -> {
                return Stream.of( line.split(",") )
                        .collect( Collectors.toList() ); })
              .collect( Collectors.toList() );
    } catch (IOException ex) {
      // ex.printStackTrace();
    }

    return data;
  }
}
