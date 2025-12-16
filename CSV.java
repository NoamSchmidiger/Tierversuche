import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CSV {

    // Year -> (Canton -> TotalAnimals)
    public static Map<Integer, Map<String, Integer>> load(String path) throws IOException {

        Map<Integer, Map<String, Integer>> data = new TreeMap<>();

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        boolean firstLine = true;

        while ((line = br.readLine()) != null) {

            // Header überspringen
            if (firstLine) {
                firstLine = false;
                continue;
            }

            String[] p = line.split(",");

            int year = Integer.parseInt(p[0].trim());
            String canton = p[1].trim();

            int s0 = Integer.parseInt(p[4].trim());
            int s1 = Integer.parseInt(p[5].trim());
            int s2 = Integer.parseInt(p[6].trim());
            int s3 = Integer.parseInt(p[7].trim());

            int total = s0 + s1 + s2 + s3;

            if (!data.containsKey(year)) {
                data.put(year, new HashMap<>());
            }

            Map<String, Integer> yearMap = data.get(year);

            if (!yearMap.containsKey(canton)) {
                yearMap.put(canton, 0);
            }

            yearMap.put(canton, yearMap.get(canton) + total);
        }

        br.close();
        return data;
    }
}
