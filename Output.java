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
    }
}
