package org.example;


import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Locale.setDefault(Locale.forLanguageTag("sv-SE"));
        Scanner input = new Scanner(System.in);

        int[] elpriser = new int[24];
        boolean exit = false;
        while(!exit) {
            System.out.print("\nElpriser\n");
            System.out.print("========\n");
            System.out.print("1. Inmatning\n");
            System.out.print("2. Min, Max och Medel\n");
            System.out.print("3. Sortera\n");
            System.out.print("4. Bästa Laddningstid (4h)\n");
            System.out.print("e. Avsluta\n");
            var choice = input.nextLine().toLowerCase();

            switch (choice) {
                case "1" -> {
                    for (int i = 0; i < 24; i++) {
                        try {
                            elpriser[i] = Integer.parseInt(input.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Endast heltal tillåtet");
                            // matar in värden i elpris arrayen om det inte är ett int värde så fångar numberformatexception upp det
                        }
                    }
                }
                case "2" -> minMaxMedel(elpriser);
                case "3" -> sortera(elpriser);
                case "4" -> bastaLaddningstid(elpriser);
                case "e" -> exit = true;
                default -> System.out.print("\n Ogiltligt val. Försök igen.");
            }

        }
    }


    public static void minMaxMedel(int[] elpriser) {
        // metod för minmaxmedel i case 2 med int array elpriser

        int minPris = elpriser[0];
        int maxPris = elpriser[0];
        int minTimme = 0;
        int maxTimme = 0;
        int totalPris = 0;
        // deklarerar alla värdena till 0 innan loopen körs

        for (int i = 0; i < elpriser.length; i++) {
            if (elpriser[i] < minPris) {
                minPris = elpriser[i];
                minTimme = i;
                //loop som elpriser är mindre än minPris då blir minPris elpriser = minTimme
            }
            if (elpriser[i] > maxPris) {
                maxPris = elpriser[i];
                maxTimme = i;
                // liknande loop men för att sätta nytt maxpris ist till maxtimme
            }
            totalPris += elpriser[i];
            // + ihop hela arrayen
        }

        double medelPris = (double) totalPris / elpriser.length;
        String formatMedelPris = String.format("%.2f", medelPris);
        // för att få två decimaler
        int nastaMinTimme = (minTimme + 1) % 24;
        int nastaMaxTimme = (maxTimme + 1) % 24;
        // vi lägger in en variabel för minsta och dyraste timme
        System.out.print("\nLägsta pris: " + String.format("%02d-%02d, %d öre/kWh", minTimme, nastaMinTimme, minPris));
        System.out.print("\nHögsta pris: " + String.format("%02d-%02d, %d öre/kWh", maxTimme, nastaMaxTimme, maxPris));
        System.out.print("\nMedelpris: " + formatMedelPris + " öre/kWh");
        // string format för att utskriften ska bli rätt i koden
    }

    public static void sortera(int[] elpriser) {
        // skapar metod för att sortera


        Integer[] index = new Integer[elpriser.length];
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
            // för att skapa en array med alla elpriser
            // som börjar på 0 i index +1 varje gång
        }

        Arrays.sort(index, (a, b) -> Integer.compare(elpriser[b], elpriser[a]));
        // sorterar och jämnför priser

        for (int i : index) {
            System.out.print("\n"+String.format("%02d-%02d %d öre", i, (i + 1) % 25, elpriser[i]));
            // 25 för att få med 23-24
        }
    }

    public static void bastaLaddningstid(int[] elpriser) {
        // en bastaLaddningstid metod

        int antalTimmar = 4;
        int billigastePris = Integer.MAX_VALUE;
        // max value från början för att inte missa ett högre tal
        int startTimme = 0;
        // fyra timmars intervaller

        for (int i = 0; i <= 24 - antalTimmar; i++) {
            int prisSumma = 0;
            for (int j = i; j < i + antalTimmar; j++) {
                prisSumma += elpriser[j];
            }
            if (prisSumma < billigastePris) {
                billigastePris = prisSumma;
                startTimme = i;
                // söker igenom 24timmar med tvp loopar för att hitta billigaste timme i en 4 timmars intervall
            }
        }

        double medelPris = (double) billigastePris / antalTimmar;
        // räknar ut medelpris för 4 timmars intervallen

        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String formatMedelPris = decimalFormat.format(medelPris);

        int timme = startTimme; {
            System.out.print("Påbörja laddning klockan " + timme +"\n");
        }
        System.out.print("Medelpris 4h: " + formatMedelPris + " öre/kWh");
    }


}


