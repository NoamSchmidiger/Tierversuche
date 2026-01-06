import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String csvPath = "animal_experimentation_statistics.csv";

        try {
            // CSV laden
            List<DatenEingang> data = CSV.load(csvPath);

            // Scanner für Benutzerinput
            try (Scanner sc = new Scanner(System.in)) {

                // Auswahl zwischen imperativ und funktional
                System.out.println("--- Tierversuchsanalyse Schweiz ---");
                System.out.println("Wählen Sie die Version:");
                System.out.println("1. Imperativ");
                System.out.println("2. Funktional");
                System.out.print("Ihre Wahl: ");
                String versionChoice = sc.nextLine();

                // Analyse-Objekte
                Daten analyzerImperativ = new Daten(data); // imperativ implementiert
                DatenFunktional analyzerFunktional = new DatenFunktional(data); // funktional implementiert

                boolean running = true;
                while (running) {
                    System.out.println("\n--- Menü ---");
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
                            System.out.println("--- Gesamtzahl pro Jahr ---");
                            if (versionChoice.equals("1")) {
                                Output.printTotalPerYear(analyzerImperativ.totalPerYear());
                            } else {
                                Output.printTotalPerYear(analyzerFunktional.totalPerYear());
                            }
                            break;

                        case "2":
                            System.out.println("--- Top 5 Kantone ---");
                            if (versionChoice.equals("1")) {
                                Output.printTopCantons(analyzerImperativ.top5Cantons());
                            } else {
                                Output.printTopCantons(analyzerFunktional.top5Cantons());
                            }
                            break;

                        case "3":
                            System.out.println("--- Extremwerte ---");
                            if (versionChoice.equals("1")) {
                                Output.printExtremes(analyzerImperativ.minValue(), analyzerImperativ.maxValue());
                            } else {
                                Output.printExtremes(analyzerFunktional.minValue(), analyzerFunktional.maxValue());
                            }
                            break;

                        case "4":
                            System.out.println("--- Trendanalyse (sinkende Kantone) ---");
                            if (versionChoice.equals("1")) {
                                Output.printTrendAnalysis(analyzerImperativ.decreasingCantons());
                            } else {
                                Output.printTrendAnalysis(analyzerFunktional.decreasingCantons());
                            }
                            break;

                        case "5":
                            System.out.println("--- Durchschnitt pro Kanton ---");
                            if (versionChoice.equals("1")) {
                                Output.printAveragePerCanton(analyzerImperativ.averagePerCanton());
                            } else {
                                Output.printAveragePerCanton(analyzerFunktional.averagePerCanton());
                            }
                            break;

                        case "6":
                            System.out.println("--- Zeitreihenanalyse ---");
                            if (versionChoice.equals("1")) {
                                Output.printYearlyChanges(analyzerImperativ.yearlyPercentageChange());
                            } else {
                                Output.printYearlyChanges(analyzerFunktional.yearlyPercentageChange());
                            }
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
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
