/* 
*******************************************************************
* Tekstanalyse
* Programmet lager en GUI-løsning som håndterer korte tekster
* som brukeren skriver inn. Programmet kan søke opp ord
* som starter med en bestemt bokstav, teller opp antall slike
* ord, og viser det lengste ordet som starter med denne 
* bokstaven.
*
* Programmet er basert på læring fra  
* Istad og Kristoffersen (2013): "Forstå programmering - med Java"
******************************************************************
*/

import java.awt.*;          // FlowLayout, BorderLayout...
import java.awt.event.*;   // ActionEvent, ActionListener...
import javax.swing.*;     // JFrame, JButton, JTekstField, JTextArea...
 
public class Tekstanalyse extends JFrame implements ActionListener {

  // Oppretter variabler
  private JButton startTelling, nullstill;
  private JTextField telleBokstav;
  private JTextArea skriveområde;
  private JScrollPane rullefelt;
  JLabel svarTekst = new JLabel("");
 
  // Main-metoden, konstruktør for klassen
  public static void main(String[] args) {
    Tekstanalyse vindu = new Tekstanalyse();
    vindu.setTitle("Tekstanalyse");
    vindu.setDefaultCloseOperation(EXIT_ON_CLOSE);
    vindu.opprettGUI();
    vindu.setSize(450,260);
    vindu.setLocationRelativeTo(null);
    vindu.setVisible(true);
    vindu.setResizable(false);
  }
 
  //Gjør komponenter tilgjengelige i GUI
  public void opprettGUI() {
    // Oppretter et panel øverst hvor bokstavfelt og knapper skal ligge
    JPanel np = new JPanel();
    add(np, BorderLayout.NORTH);
    
    //Tekstforklaring til bokstavfeltet
    JLabel etikett = new JLabel("Oppgi en tellebokstav: ");
    np.add(etikett);

    // Oppretter felt hvor brukeren kan skrive inn bokstav
    telleBokstav = new JTextField(2);
    np.add(telleBokstav);

    np.add( new JLabel("    ") );  // Litt luft

    // Oppretter knapp for å starte telling av forekomster av bokstav
    startTelling = new JButton("Start tellingen");
    startTelling.addActionListener(this);
    np.add(startTelling);

    np.add( new JLabel("    ") );  // Litt luft

    // Oppretter knapp for nullstilling
    nullstill = new JButton("Nullstill");
    nullstill.addActionListener(this);
    np.add(nullstill);

    // Oppretter tekstfeltet i midten, hvor bruker kan skrive inn en kort tekst
    JPanel cp = new JPanel();
    add(cp, BorderLayout.CENTER);

    skriveområde = new JTextArea(10, 35);
    skriveområde.setLineWrap(true);
    skriveområde.setWrapStyleWord(true);
    rullefelt = new JScrollPane(skriveområde);
    rullefelt.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    cp.add(rullefelt);

    // Oppretter etikett i bunnen, for presentasjon av antall ord og lengste ord med bokstaven det spørres etter
    add(svarTekst, BorderLayout.SOUTH);
    svarTekst.setHorizontalAlignment(JLabel.CENTER);
  }

  //Håndterer hendelser fra GUI-komponenter
  public void actionPerformed(ActionEvent e) {
    String utTekst="";
    if (e.getSource() == startTelling) {
      try {
        String tekst = skriveområde.getText().toUpperCase();           //Gjør om teksten til bare store bokstaver for søking
        char bokstav = telleBokstav.getText().toUpperCase().charAt(0); // Hvis bruker oppgir mer enn en søkebokstav brukes kun den første
        utTekst=finnOrd(tekst, bokstav);
      }
      catch (Exception ex) {                    // Slår til f.eks. ved to mellomrom på rad, eller hvis man trykker start telling uten 
        utTekst="Ulovlig inndata!";             // å ha oppgitt en søkebokstav eller uten at man har skrevet inn en tekst
      }
    }
    else if (e.getSource() == nullstill) {
      telleBokstav.setText("");
      skriveområde.setText("");
      utTekst="";
    }
    svarTekst.setText(utTekst);
  }

  // Hjelpemetode for å finne ord som begynner med en bestemt bokstav
  private static String finnOrd(String tekst, char bokstav) {
    String svar="";
    int antOrd=0;
    String lengsteOrd="";
    int lengsteLengde=0;   
    String[] ordTab = tekst.split(" |\n");                // Deler opp teksten i en ordtabell
    for (int i=0; i<ordTab.length; i++) {                 // Går gjennom hvert ord i tabellen
        String ord = ordTab[i];
        if ( ord.charAt(0) == bokstav ) {                 // Hvis ordet inneholder bokstaven vi søker etter
          antOrd++;                                       // øker antallet med 1.
          if ( ord.length() > lengsteLengde )  {          // Hvis ordet i tillegg er det lengste hittil,
            lengsteOrd = ord;                             // tilordnes det til String-variabelen lengsteOrd
            lengsteLengde = ord.length();                 // og lengsteLengde settes lik ordets lengde             
          }
          else if ( ord.length() == lengsteLengde )       // Hvis ordet er like langt som det lengste hittil,
            lengsteOrd += ", "+ord;                       // legges det til i variabelen lengsteOrd
        }
    } 
    if (antOrd > 0) {
      // Sørger for at det (første) lengste ordet får stor forbokstav og resten små før utskrift
      lengsteOrd = lengsteOrd.substring(0,1).toUpperCase() + lengsteOrd.substring(1).toLowerCase(); 
      svar = "Antall ord på "+bokstav+" er "+antOrd+". Lengst av dem var: "+lengsteOrd;
    }
    else
      svar = "Fant ingen ord som starter med "+bokstav+"!";
    return svar;
  }
 
}