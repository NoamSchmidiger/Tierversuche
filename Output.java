import java.util.List;
import java.util.Map;

public class Output {

    public static void printTotalPerYear(Map<Integer, Integer> totals) {

        System.out.println("=== Gesamtzahl Tierversuche pro Jahr ===");

        for (Integer year : totals.keySet()) {
            System.out.println(year + ": " + totals.get(year));
        }
    }

    public static void printTopCantons(List<String> topCantons) {

        System.out.println("\n=== Top 5 Kantone ===");

        for (int i = 0; i < topCantons.size(); i++) {
            System.out.println(topCantons.get(i));
        }
    }

    public static void printExtremes(String min, String max) {

        System.out.println("\n=== Extremwerte ===");
        System.out.println("Minimum: " + min);
        System.out.println("Maximum: " + max);
    }
}
