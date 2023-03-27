/**
* GodJul.java Trine Normann
* Programmet tar inn et antall og et navn, 
* og skriver en julehilsen og et lite juletre i terminalen.
*
*/

public class GodJul {
    public static void main(String[] args) {

        int antall = 0;
        String navn = "";

        if (args.length == 0 || args[0].equals("-h")) {
            usage();
            return;
        } else if (args.length != 2) {
            usage();
            return;
        } else {
            try {
                antall = Integer.parseInt(args[0]);
                navn = args[1];
            } catch (Exception e) {
                usage();
                return;
            }
        }

        lagJuletre(antall, navn);
    }

    static void printChar(String tegn, int ant) {
        for (int i = 0; i < ant; i++) {
            System.out.print(tegn);
        }
    }

    static void lagJuletre(int stjerner, String navn) {
        System.out.println("\nGod jul " + navn + "!\n");

        int max_stjerner = stjerner;
        int j = max_stjerner/2;
        for (int i = 1; i < max_stjerner; i += 2) {
            printChar(" ", j);
            printChar("*", i);
            printChar(" ", j);
            System.out.println();
            j = j - 1;
        }

        int nederste_space = max_stjerner/2-1;
        printChar(" ", nederste_space);
        printChar("|", max_stjerner - (nederste_space*2));
        printChar(" ", nederste_space);
        System.out.println();
    }

    public static void usage() {
        System.out.println("Usage: java Juli [-h] antall_stjerner navn");
    }
}
