import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSV {

    public static List<DatenEingang> load(String path) throws Exception {

        List<DatenEingang> result = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;

        br.readLine(); // Header überspringen

        while ((line = br.readLine()) != null) {

            String[] parts = line.split(";");

            int year = Integer.parseInt(parts[0]);
            String canton = parts[1];
            int value = Integer.parseInt(parts[2]);

            result.add(new DatenEingang(year, canton, value));
        }

        br.close();
        return result;
    }
}
