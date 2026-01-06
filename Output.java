import java.util.List;

public class Output {

    public static void printTotalPerYear(List<String> totals) {
        System.out.println("Gesamtzahl pro Jahr:");
        for (String line : totals) {
            System.out.println(line);
        }
        System.out.println();
    }

    public static void printTopCantons(List<String> cantons) {
        System.out.println("Top 5 Kantone:");
        for (String line : cantons) {
            System.out.println(line);
        }
        System.out.println();
    }

    public static void printExtremes(String min, String max) {
        System.out.println("Minimalwert:");
        System.out.println(min);
        System.out.println();
        System.out.println("Maximalwert:");
        System.out.println(max);
        System.out.println();
    }

    public static void printTrendAnalysis(List<String> trend) {
    System.out.println("Trendanalyse: Kantone mit sinkenden Versuchszahlen");
    if (trend.isEmpty()) {
        System.out.println("Keine Kantone mit sinkender Tendenz gefunden.");
    } else {
        for (String line : trend) {
            System.out.println(line);
        }
    }
    System.out.println();
}


    public static void printAveragePerCanton(List<String> averages) {
        System.out.println("Durchschnitt pro Kanton (über/unter Durchschnitt):");
        for (String line : averages) {
            System.out.println(line);
        }
        System.out.println();
    }

    public static void printYearlyChanges(List<String> changes) {
        System.out.println("Prozentuale Veränderung zum Vorjahr:");
        for (String line : changes) {
            System.out.println(line);
        }
        System.out.println();
    }
}
