package org.example;

import java.sql.SQLOutput;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char choice;

        do {
            System.out.print("\n Elpriser");
            System.out.print("\n ========");
            System.out.print("\n 1. Inmatning");
            System.out.print("\n 2. Min, Max och Medel");
            System.out.print("\n 3. Sortera");
            System.out.print("\n 4. Bästa Laddningstid (4h)");
            System.out.print("\n e. Avsluta");

            System.out.print("\n Välj ett alternativ: ");
            choice = input.next().charAt(0);

            switch (choice) {
                case '1':
                    System.out.print("\n Du valde Inmatning.");
                    break;
                case '2':
                    System.out.print("\n Du valde Min, Max och Medel.");
                    break;
                case '3':
                    System.out.print("\n Du valde Sortera.");
                    break;
                case '4':
                    System.out.print("\n Du valde Bästa Laddningstid (4h)");
                    break;
                case 'e':
                case 'E':
                    System.out.print("\n Programmet avslutas.");
                    break;
                default:
                    System.out.print("\n Ogiltligt val. Försök igen.");
                    break;
            }
        } while (choice != 'e' && choice !='E');

        input.close();
        }
    }

