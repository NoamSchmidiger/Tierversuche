import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String csvPath = "animal_experimentation_statistics.csv";

        try {
            // CSV laden
            List<DatenEingang> data = CSV.load(csvPath);

            // Analyse-Objekt
            Daten analyzer = new Daten(data);

            Scanner sc = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\n--- Tierversuchsanalyse Schweiz ---");
                System.out.println("1. Gesamtzahl pro Jahr");
                System.out.println("2. Top 5 Kantone");
                System.out.println("3. Extremwerte");
                System.out.println("4. Trendanalyse (sinkende Kantone)");
                System.out.println("5. Durchschnitt pro Kanton");
                System.out.println("6. Zeitreihenanalyse (prozentuale Veränderung)");
                System.out.println("0. Beenden");
                System.out.print("Bitte wählen: ");

                String choice = sc.nextLine();

                switch (choice) {
                    case "1":
                        Output.printTotalPerYear(analyzer.totalPerYear());
                        break;
                    case "2":
                        Output.printTopCantons(analyzer.top5Cantons());
                        break;
                    case "3":
                        Output.printExtremes(analyzer.minValue(), analyzer.maxValue());
                        break;
                    case "4":
                        Output.printTrendAnalysis(analyzer.decreasingCantons());
                        break;
                    case "5":
                        Output.printAveragePerCanton(analyzer.averagePerCanton());
                        break;
                    case "6":
                        Output.printYearlyChanges(analyzer.yearlyPercentageChange());
                        break;
                    case "0":
                        running = false;
                        System.out.println("Programm beendet.");
                        break;
                    default:
                        System.out.println("Ungültige Eingabe!");
                        break;
                }
            }

            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
