import java.util.Locale;

/**
 *
 * Δημιουργουμε μια κλαση η οποια παιρνει απο το συστημα του υπολογιστη την γλωσσα που εχει επιλεξει ο χρηστης και την εφαρμοζει
 * στο προγραμμα(ειτε ελληνικα ειτε αγγλικα)
 *
 * @author Alexandros Vladovitis,Stelios Verros
 */

public  class International {

    Locale locale;
    String[] SMS;

    public International(){

        locale = Locale.getDefault();
        String language;
        language = locale.toString();

        if(language.equals("el_GR")){
            SMS = new String[]{("ΕΠΙΛΟΓΕΣ"),(" ΘΑ ΠΑΙΞΕΙΣ ΜΕ ΓΡΑΜΜΑΤΑ 'Η ΜΕ ΑΡΙΘΜΟΥΣ;"),("         ΑΡΙΘΜΟΣ"),("ΒΟΗΘΕΙΑ"),("ΑΦΑΙΡΕΣΗ ΑΡΙΘΜΟΥ ΑΠΟ ΠΙΝΑΚΑ"),("ΔΕΝ ΜΠΟΡΕΙΣ ΝΑ ΒΑΛΕΙΣ ΑΥΤΟΝ ΤΟΝ ΑΡΙΘΜΟ!")
                    ,("ΛΑΘΟΣ ΚΙΝΗΣΗ"),("ΔΕΝ ΜΠΟΡΕΙΣ ΝΑ ΒΑΛΕΙΣ ΑΥΤΟ ΤΟ ΓΡΑΜΜΑ!"),("ΚΕΡΔΙΣΕΣ!"),("ΤΟ ΠΑΙΧΝΙΔΙ ΤΕΛΕΙΩΣΕ"),("                                      ΤΙ ΕΠΙΘΥΜΕΙΣ")
                    ,("                                     ΠΑΙΞΕ ΞΑΝΑ"),("ΠΑΙΞΕ ΞΑΝΑ"),("ΤΕΛΟΣ ΠΑΙΧΝΙΔΙΟΥ"),("                                ΤΕΛΟΣ ΠΑΙΧΝΙΔΙΟΥ"),("ΕΧΑΣΕΣ!")
                    ,("ΕΠΙΣΤΡΟΦΗ ΣΤΟ ΜΕΝΟΥ"),("                            ΕΠΙΣΤΡΟΦΗ ΣΤΟ ΜΕΝΟΥ"),("ΑΡΙΘΜΟΣ"),("   ΓΡΑΜΜΑΤΑ  "),("             ΓΡΑΜΜΑΤΑ"),("ΠΑΙΧΝΙΔΙ Sudoku"),("        ΔΙΑΛΕΞΕ ΠΑΙΧΝΙΔΙ")
                    ,("ΤΙ ΕΠΙΘΥΜΕΙΣ"),("ΠΑΙΧΝΙΔΙ Sudoku"),("     ΔΙΑΛΕΞΕ ΠΑΙΧΝΙΔΙ"),("ΕΠΙΛΟΓΕΣ"),("ΘΑ ΠΑΙΞΕΙΣ ΜΕ ΓΡΑΜΜΑΤΑ 'Η ΜΕ ΑΡΙΘΜΟΥΣ;"),("        ΑΡΙΘΜΟΥΣ    "),("   ΓΡΑΜΜΑΤΑ  "),("             ΓΡΑΜΜΑΤΑ")};
        }else {
            SMS = new String[]{("CHOICES"),("WILL YOU PLAY WITH LETTERS OR NUMBERS?"),("        NUMBERS"),("HELP"),("REMOVE THE NUMBER FROM THE TABLE"),("YOU CAN'T PUT THAT NUMBER!"),
                    ("WRONG MOVE"),("YOU CAN'T PUT THIS LETTER!"),("WIN!!"),("THE GAME IS OVER"),("                              WHAT DO YOU WANT"),("                                     PLAY AGAIN"),
                    ("PLAY AGAIN"),("GAME OVER"),("                                GAME OVER"),("LOST"), ("RETURN TO MENU"), ("                            RETURN TO MENU"),("NUMBER"),("   LETTERS  "),
                    ("             LETTERS"), ("Sudoku GAME"),("         CHOOSE A GAME"), ("WHAT DO YOU WANT"),("Sudoku GAME"), ("       CHOOSE A GAME"),("CHOICES"),("WILL YOU PLAY WITH LETTERS OR NUMBERS?"),
                    ("       NUMBERS    "),("   LETTERS  "),("             LETTERS")};
        }
    }
}
