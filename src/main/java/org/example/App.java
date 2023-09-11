package org.example;


import java.util.Arrays;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int[] elpriser = new int[24];

        while(true) {
            System.out.print("\n Elpriser");
            System.out.print("\n ========");
            System.out.print("\n 1. Inmatning");
            System.out.print("\n 2. Min, Max och Medel");
            System.out.print("\n 3. Sortera");
            System.out.print("\n 4. Bästa Laddningstid (4h)");
            System.out.print("\n e. Avsluta");

            System.out.print("\n Välj ett alternativ: ");
            char choice = input.next().toLowerCase().charAt(0);

            switch (choice) {
                case '1':
                    for (int i = 0; i < 24; i++) {
                        System.out.print("\n Ange elpris per timme " + i + " (i öre per kW/h: ");
                        elpriser[i] = input.nextInt();
                    }
                    break;
                case '2':
                    System.out.print("\n Du valde Min, Max och Medel.");
                    minMaxMedel(elpriser);
                    break;
                case '3':
                    System.out.print("\n Du valde Sortera.");
                    sortera(elpriser);
                    break;
                case '4':
                    System.out.print("\n Du valde Bästa Laddningstid (4h)");
                    bastaLaddningstid(elpriser);
                    break;
                case 'e':
                    System.out.print("\n Programmet avslutas.");
                    System.exit(0);

                default:
                    System.out.print("\n Ogiltligt val. Försök igen.");
                    break;
            }
        }
    }

    public static void minMaxMedel(int[] elpriser) {
        if (elpriser[0] == 0) {
            System.out.print("\n Ingen inmatning finns. Gör inmatning först.");
            return;
            // ny metod för minmaxmedel
        }

        int minPris = elpriser[0];
        int maxPris = elpriser[0];
        int minTimme = 0;
        int maxTimme = 0;
        int totalPris = 0;

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
        }

        double medelPris = (double) totalPris / elpriser.length;

        System.out.print("\n Lägsta pris " + minPris + "öre, timme: " +minTimme);
        System.out.println("\n Högsta pris: " + maxPris + " äre, timme " + maxTimme);
        System.out.print("\n Medel pris per dygn: " + medelPris + " öre per kWh");
    }

    public static void sortera(int[] elpriser) {
        if(elpriser[0] == 0) {
            System.out.print("\n Ingen inmatning finns. Gör en inmatning först.");
            return;
        }

        Integer[] index = new Integer[elpriser.length];
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
            // för att skapa en array med alla elpriser
        }

        Arrays.sort(index, (a, b) -> Integer.compare(elpriser[b], elpriser[a]));

        System.out.print("\n Sorterade elpriser:");
        for (int i : index) {
            System.out.print("\n " + i + ": " + elpriser[i] + " öre per kWh");
        }
    }

    public static void bastaLaddningstid(int[] elpriser) {
        if (elpriser[0] == 0) {
            System.out.print("\n Ingen inmatning finns. Gör inmaning först.");
            return;
        }

        int antalTimmar = 4;
        int billigastePris = Integer.MAX_VALUE;
        int startTimme = 0;

        for (int i = 0; i <= 24 - antalTimmar; i++) {
            int prisSumma = 0;
            for (int j = i; j < i + antalTimmar; j++) {
                prisSumma += elpriser[j];
            }
            if (prisSumma < billigastePris) {
                billigastePris = prisSumma;
                startTimme = i;
            }
        }

        double medelPris = (double) billigastePris / antalTimmar;

        System.out.print("\n Bästa laddningstid (4 timmar):");
        for (int timme = startTimme; timme < startTimme + antalTimmar; timme++) {
            System.out.print("\n " + timme + "-" + (timme + 1));
        }
        System.out.print("\n Medelpris: " + medelPris + " öre per kWh");
    }


    }


