import java.util.*;
import java.util.stream.Collectors;

public class DatenFunktional {

    private final List<DatenEingang> data;

    public DatenFunktional(List<DatenEingang> data) {
        this.data = data;
    }

    // --- Gesamtzahl pro Jahr ---
    public List<String> totalPerYear() {
        Map<Integer, Integer> sums = data.stream()
            .filter(entry -> entry.year != 0)
            .collect(Collectors.groupingBy(
                entry -> entry.year,
                TreeMap::new,   // automatisch nach Jahr sortiert
                Collectors.summingInt(entry -> entry.value)
            ));

        return sums.entrySet().stream()
            .map(e -> e.getKey() + ": " + e.getValue())
            .collect(Collectors.toList());
    }

    // --- Gesamtzahl pro Kanton ---
    public List<String> totalPerCanton() {
        Map<String, Integer> sums = data.stream()
            .collect(Collectors.groupingBy(
                entry -> entry.canton,
                Collectors.summingInt(entry -> entry.value)
            ));

        return sums.entrySet().stream()
            .map(e -> e.getKey() + ": " + e.getValue())
            .collect(Collectors.toList());
    }

    // --- Top 5 Kantone ---
    public List<String> top5Cantons() {
        return data.stream()
            .collect(Collectors.groupingBy(
                entry -> entry.canton,
                Collectors.summingInt(entry -> entry.value)
            ))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(5)
            .map(e -> e.getKey() + ": " + e.getValue())
            .collect(Collectors.toList());
    }

    // --- Extremwerte ---
    public String minValue() {
        return data.stream()
            .filter(entry -> entry.year != 0)
            .min(Comparator.comparingInt(entry -> entry.value))
            .map(entry -> entry.canton + " (" + entry.year + "): " + entry.value)
            .orElse("Keine Daten");
    }

    public String maxValue() {
        return data.stream()
            .max(Comparator.comparingInt(entry -> entry.value))
            .map(entry -> entry.canton + " (" + entry.year + "): " + entry.value)
            .orElse("Keine Daten");
    }

    // --- Trendanalyse (sinkende Kantone) ---
    public List<String> decreasingCantons() {
        return data.stream()
            .collect(Collectors.groupingBy(entry -> entry.canton))
            .entrySet().stream()
            .map(entry -> {
                List<DatenEingang> list = entry.getValue().stream()
                        .sorted(Comparator.comparingInt(e -> e.year))
                        .collect(Collectors.toList());
                boolean decreasing = true;
                for (int i = 1; i < list.size(); i++) {
                    if (list.get(i).value >= list.get(i - 1).value) {
                        decreasing = false;
                        break;
                    }
                }
                if (decreasing && !list.isEmpty() && list.get(0).value > 0) {
                    double percent = ((list.get(0).value - list.get(list.size() - 1).value) * 100.0) / list.get(0).value;
                    return entry.getKey() + ": -" + String.format("%.1f", percent) + "%";
                }
                return null;
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    // --- Durchschnitt pro Kanton ---
    public List<String> averagePerCanton() {
        Map<String, Double> averages = data.stream()
            .collect(Collectors.groupingBy(
                entry -> entry.canton,
                Collectors.averagingInt(entry -> entry.value)
            ));

        double totalAvg = averages.values().stream().mapToDouble(Double::doubleValue).average().orElse(0);

        return averages.entrySet().stream()
            .map(e -> e.getKey() + ": " + String.format("%.1f", e.getValue())
                    + " (" + (e.getValue() >= totalAvg ? "überdurchschnittlich" : "unterdurchschnittlich") + ")")
            .collect(Collectors.toList());
    }

    // --- Zeitreihenanalyse ---
    public List<String> yearlyPercentageChange() {
        TreeMap<Integer, Integer> sums = data.stream()
            .filter(entry -> entry.year != 0)
            .collect(Collectors.groupingBy(
                entry -> entry.year,
                TreeMap::new,
                Collectors.summingInt(entry -> entry.value)
            ));

        List<Integer> years = new ArrayList<>(sums.keySet());
        List<Integer> values = new ArrayList<>(sums.values());

        List<String> result = new ArrayList<>();
        for (int i = 1; i < years.size(); i++) {
            if (values.get(i - 1) == 0) {
                result.add(years.get(i) + ": N/A");
            } else {
                double change = ((values.get(i) - values.get(i - 1)) * 100.0) / values.get(i - 1);
                result.add(years.get(i) + ": " + String.format("%.1f", change) + "%");
            }
        }
        return result;
    }
}
