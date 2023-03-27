/**
* GangeOeving.java Trine Normann 06.05.2018
* Programmet genererer et antall gangestykker, og
* skriver dem til en .txt-fil. Fasiten kommer på en egen fil.
*
*/

import java.io.*;
import java.util.ArrayList;
import static java.lang.Math.*;

public class GangeOeving {
    public static void main(String[] args) throws IOException {
        ArrayList<String> gangeStykker = new ArrayList<String>();
        ArrayList<String> fasit = new ArrayList<String>();

        /* Oppretter 100 gangestykker */
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                gangeStykker.add(i + " x " + j + " = ");
                fasit.add(i + " x " + j + " = " + i * j);
            }
        }

        /* For å skrive et antall gangestykker i tilfeldig rekkefølge til fil */

        /* Oppretter skriveforbindelse for gangestykkene */
        String tilFilGange = "gangestykker.txt";
        FileWriter skriveforbTilFilGange = new FileWriter(tilFilGange, false); // false skriver over filen, true legger til i filen
        PrintWriter skriverGange = new PrintWriter(new BufferedWriter(skriveforbTilFilGange));

        /* Oppretter skriveforbindelse for fasiten */
        String tilFilFasit = "gangefasit.txt";
        FileWriter skriveforbTilFilFasit = new FileWriter(tilFilFasit, false); // false skriver over filen, true legger til i filen
        PrintWriter skriverFasit = new PrintWriter(new BufferedWriter(skriveforbTilFilFasit));

        /* Skriver hvert gangestykke til fila i tilfeldig rekkefølge */
        int antallStykker = 0;
        ArrayList<Integer> brukteTall = new ArrayList<Integer>();
        do {
            int indeks = trekkTall(0, 99);
            if (brukteTall.indexOf(indeks) < 0) {
                skriverGange.println(gangeStykker.get(indeks));
                skriverFasit.println(fasit.get(indeks));
                brukteTall.add(indeks);
                antallStykker++;
            }
        } while (antallStykker < gangeStykker.size()); // for alle stykkene: antallStykker < gangeStykker.size()
        skriverGange.close();
        skriverFasit.close();
        System.out.println(antallStykker);
    }

    /* Metode for å trekke et tilfeldig tall */
    private static int trekkTall(int fra, int til) {
        int tall = (int)(random() * (til - fra + 1)) + fra;
        return tall;
    }
}
