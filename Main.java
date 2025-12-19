import java.util.Map;

public class Main {

    public static void main(String[] args) {

        String csvPath = "animal_experimentation_statistics.csv";

        try {
            Map<Integer, Map<String, Integer>> data =
                    CSV.load(csvPath);

            Daten analyzer =
                    new Daten(data);

            Output.printTotalPerYear(
                    analyzer.totalPerYear()
            );

            Output.printTopCantons(
                    analyzer.top5Cantons()
            );

            Output.printExtremes(
                    analyzer.minValue(),
                    analyzer.maxValue()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
