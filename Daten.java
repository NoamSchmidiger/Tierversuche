import java.util.ArrayList;
import java.util.List;

public class Daten {

    private final List<DatenEingang> data;

    public Daten(List<DatenEingang> data) {
        this.data = data;
    }

    // --- Gesamtzahl pro Jahr ---
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

        // Jahre + Summen chronologisch sortieren
        sortParallel(years, sums);

        List<String> result = new ArrayList<>();
        for (int i = 0; i < years.size(); i++) {
            result.add(years.get(i) + ": " + sums.get(i));
        }
        return result;
    }

    // --- Gesamtzahl pro Kanton ---
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

    // --- Top 5 Kantone ---
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
                if (sums.get(j) > sums.get(maxIndex)) maxIndex = j;
            }
            result.add(cantons.get(maxIndex) + ": " + sums.get(maxIndex));
            cantons.remove(maxIndex);
            sums.remove(maxIndex);
        }
        return result;
    }

    // --- Extremwerte ---
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

    public List<String> decreasingCantons() {
    List<String> result = new ArrayList<>();
    List<String> cantons = new ArrayList<>();

    // Alle Kantone sammeln
    for (DatenEingang entry : data) {
        if (!cantons.contains(entry.canton)) cantons.add(entry.canton);
    }

    for (String canton : cantons) {
        List<Integer> yearsPerCanton = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        for (DatenEingang entry : data) {
            if (entry.canton.equals(canton)) {
                yearsPerCanton.add(entry.year);
                values.add(entry.value);
            }
        }

        // chronologisch sortieren
        sortParallel(yearsPerCanton, values);

        // prüfen, ob Werte sinken
        boolean decreasing = true;
        for (int i = 1; i < values.size(); i++) {
            if (values.get(i) >= values.get(i - 1)) {
                decreasing = false;
                break;
            }
        }

        // Nur Kantone mit gültigem Startwert
        if (decreasing && !values.isEmpty() && values.get(0) > 0) {
            int reduction = values.get(0) - values.get(values.size() - 1);
            double percent = (reduction * 100.0) / values.get(0);
            result.add(canton + ": -" + String.format("%.1f", percent) + "%");
        }
    }

    return result;
}

    // --- Durchschnitt pro Kanton ---
    public List<String> averagePerCanton() {
        List<String> cantons = new ArrayList<>();
        List<Integer> sums = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();

        for (DatenEingang entry : data) {
            int index = cantons.indexOf(entry.canton);
            if (index == -1) {
                cantons.add(entry.canton);
                sums.add(entry.value);
                counts.add(1);
            } else {
                sums.set(index, sums.get(index) + entry.value);
                counts.set(index, counts.get(index) + 1);
            }
        }

        List<Double> averages = new ArrayList<>();
        double totalAvg = 0;
        for (int i = 0; i < sums.size(); i++) {
            double avg = sums.get(i) * 1.0 / counts.get(i);
            averages.add(avg);
            totalAvg += avg;
        }
        totalAvg /= averages.size();

        List<String> result = new ArrayList<>();
        for (int i = 0; i < cantons.size(); i++) {
            if (cantons.get(i).isEmpty()) continue;
            String label = averages.get(i) >= totalAvg ? "überdurchschnittlich" : "unterdurchschnittlich";
            result.add(cantons.get(i) + ": " + String.format("%.1f", averages.get(i)) + " (" + label + ")");
        }
        return result;
    }

    // --- Zeitreihenanalyse ---
    public List<String> yearlyPercentageChange() {
        List<Integer> years = new ArrayList<>();
        List<Integer> sums = new ArrayList<>();

        for (DatenEingang entry : data) {
            int index = years.indexOf(entry.year);
            if (index == -1) {
                years.add(entry.year);
                sums.add(entry.value);
            } else {
                sums.set(index, sums.get(index) + entry.value);
            }
        }

        // Jahre + Summen chronologisch sortieren
        sortParallel(years, sums);

        List<String> result = new ArrayList<>();
        for (int i = 1; i < years.size(); i++) {
            if (sums.get(i - 1) == 0 || years.get(i) == 0) {
                result.add(years.get(i) + ": N/A");
                continue;
            }
            double change = ((sums.get(i) - sums.get(i - 1)) * 100.0) / sums.get(i - 1);
            result.add(years.get(i) + ": " + String.format("%.1f", change) + "%");
        }
        return result;
    }


    // --- Hilfsmethode: zwei Listen parallel nach erster sortieren ---
    private void sortParallel(List<Integer> list1, List<Integer> list2) {
        for (int i = 0; i < list1.size() - 1; i++) {
            for (int j = i + 1; j < list1.size(); j++) {
                if (list1.get(j) < list1.get(i)) {
                    int temp1 = list1.get(i);
                    list1.set(i, list1.get(j));
                    list1.set(j, temp1);

                    int temp2 = list2.get(i);
                    list2.set(i, list2.get(j));
                    list2.set(j, temp2);
                }
            }
        }
    }
}
