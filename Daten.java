import java.util.ArrayList;
import java.util.List;

public class Daten {

    private List<DatenEingang> data;

    public Daten(List<DatenEingang> data) {
        this.data = data;
    }

    // Gesamtzahl pro Jahr
    public List<String> totalPerYear() {

        List<Integer> years = new ArrayList<>();
        List<Integer> sums = new ArrayList<>();

        for (DatenEingang entry : data) {

            if (entry.year == 0) continue;

            int index = years.indexOf(entry.year);

            if (index == -1) {
                years.add(entry.year);
                sums.add(entry.value);
            } else {
                sums.set(index, sums.get(index) + entry.value);
            }
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < years.size(); i++) {
            result.add(years.get(i) + ": " + sums.get(i));
        }

        return result;
    }

    // Gesamtzahl pro Kanton
    public List<String> totalPerCanton() {

        List<String> cantons = new ArrayList<>();
        List<Integer> sums = new ArrayList<>();

        for (DatenEingang entry : data) {

            int index = cantons.indexOf(entry.canton);

            if (index == -1) {
                cantons.add(entry.canton);
                sums.add(entry.value);
            } else {
                sums.set(index, sums.get(index) + entry.value);
            }
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < cantons.size(); i++) {
            result.add(cantons.get(i) + ": " + sums.get(i));
        }

        return result;
    }

    // Top 5 Kantone
    public List<String> top5Cantons() {

        List<String> cantons = new ArrayList<>();
        List<Integer> sums = new ArrayList<>();

        for (DatenEingang entry : data) {
            int index = cantons.indexOf(entry.canton);

            if (index == -1) {
                cantons.add(entry.canton);
                sums.add(entry.value);
            } else {
                sums.set(index, sums.get(index) + entry.value);
            }
        }

        List<String> result = new ArrayList<>();

        for (int i = 0; i < 5 && !cantons.isEmpty(); i++) {

            int maxIndex = 0;

            for (int j = 1; j < sums.size(); j++) {
                if (sums.get(j) > sums.get(maxIndex)) {
                    maxIndex = j;
                }
            }

            result.add(cantons.get(maxIndex) + ": " + sums.get(maxIndex));

            cantons.remove(maxIndex);
            sums.remove(maxIndex);
        }

        return result;
    }

    // Maximalwert
    public String maxValue() {

        int max = Integer.MIN_VALUE;
        String result = "";

        for (DatenEingang entry : data) {
            if (entry.value > max) {
                max = entry.value;
                result = entry.canton + " (" + entry.year + "): " + entry.value;
            }
        }

        return result;
    }

    // Minimalwert (ohne Jahr 0)
    public String minValue() {

        int min = Integer.MAX_VALUE;
        String result = "";

        for (DatenEingang entry : data) {

            if (entry.year == 0) continue;

            if (entry.value < min) {
                min = entry.value;
                result = entry.canton + " (" + entry.year + "): " + entry.value;
            }
        }

        return result;
    }
}
