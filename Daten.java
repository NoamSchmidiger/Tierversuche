import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Daten {

    private Map<Integer, Map<String, Integer>> data;

    public Daten(Map<Integer, Map<String, Integer>> data) {
        this.data = data;
    }

    // Gesamtzahl pro Jahr
    public Map<Integer, Integer> totalPerYear() {

        Map<Integer, Integer> result = new java.util.TreeMap<>();

        for (Integer year : data.keySet()) {

            int sum = 0;
            Map<String, Integer> cantons = data.get(year);

            for (String canton : cantons.keySet()) {
                sum += cantons.get(canton);
            }
            if (year != null && year != 0){
                result.put(year, sum);
            }
        }

        return result;
    }

    // Gesamtzahl pro Kanton
    public Map<String, Integer> totalPerCanton() {

        Map<String, Integer> result = new java.util.HashMap<>();

        for (Integer year : data.keySet()) {

            Map<String, Integer> cantons = data.get(year);

            for (String canton : cantons.keySet()) {

                int value = cantons.get(canton);

                if (!result.containsKey(canton)) {
                    result.put(canton, 0);
                }

                result.put(canton, result.get(canton) + value);
            }
        }

        return result;
    }

    // Top 5 Kantone
    public List<String> top5Cantons() {

        Map<String, Integer> totals = totalPerCanton();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            String maxCanton = null;
            int maxValue = -1;

            for (String canton : totals.keySet()) {
                int value = totals.get(canton);
                if (value > maxValue) {
                    maxValue = value;
                    maxCanton = canton;
                }
            }

            if (maxCanton != null) {
                result.add(maxCanton + ": " + maxValue);
                totals.put(maxCanton, -1); // markieren als benutzt
            }
        }

        return result;
    }

    // Extremwerte
    public String maxValue() {

        int max = Integer.MIN_VALUE;
        String result = "";

        for (Integer year : data.keySet()) {
            for (String canton : data.get(year).keySet()) {

                int value = data.get(year).get(canton);

                if (value > max) {
                    max = value;
                    result = canton + " (" + year + "): " + value;
                }
            }
        }

        return result;
    }

    public String minValue() {

        int min = Integer.MAX_VALUE;
        String result = "";

        for (Integer year : data.keySet()) {
            for (String canton : data.get(year).keySet()) {

                int value = data.get(year).get(canton);
                if (year == 0 || year == null) {
                    continue; // Skip Gesamtwert
                }
                if (value < min) {
                    min = value;
                    result = canton + " (" + year + "): " + value;
                }
            }
        }

        return result;
    }
}
