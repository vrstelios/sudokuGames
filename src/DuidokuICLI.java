import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * Η Κλαση αυτη δημιουργει γραφικα για το παιχνιδι Duidoku
 *
 * @author Alexandros Vladovitis,Stelios Verros
 */

public class DuidokuICLI extends JFrame {

    private DuidokuLogic logicD;
    private International InterD;
    private JButton[][] TableDui;
    private JButton[] TableCH;
    private JLabel returnGame;
    private boolean error;
    private JCheckBox HELP;

    final int SIZE_ARRAY = 4;
    final int SELECTIONS = 5;

    public DuidokuICLI(boolean ChoicePlayer) {

        /*
         * Ο κατασκευαστης δημιουργει τα buttons ,τις γλωσσες ,την επιλογη του
         * χρηστη ,την βοηθεια και την κινηση του υπολογιστη και το πανελ ωστε να εμφανιζεται σωστα
         *
         * @author Alexandros Vladovitis,Stelios Verros
         */

        error=ChoicePlayer;
        TableDui = new JButton[SIZE_ARRAY][SIZE_ARRAY];
        TableCH = new JButton[SELECTIONS];
        logicD=new DuidokuLogic();
        InterD=new International();
        String[] LETTERS = new String[SELECTIONS];

        HELP = new JCheckBox(InterD.SMS[3],false);

        LETTERS[1] = "A";
        LETTERS[2] = "B";
        LETTERS[3] = "C";
        LETTERS[4] = "D";

        JPanel MainPanel = new JPanel(new GridLayout(6, 1));
        JPanel DuidokuPanel = new JPanel(new GridLayout(2, 2));
        JPanel[][] BoxPanel = new JPanel[2][2];
        JPanel choosePanel = new JPanel(new GridLayout(1, 6));

        setTitle(" Duidoku ");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                BoxPanel[i][j] = new JPanel(new GridLayout(2, 2));
                DuidokuPanel.add(BoxPanel[i][j]);
            }
        }

        add(MainPanel);
        add(DuidokuPanel);

        for (int i = 0; i < SIZE_ARRAY; i++) {
            for (int j = 0; j < SIZE_ARRAY; j++) {
                TableDui[i][j] = new JButton(" ");
                TableDui[i][j].setBackground(Color.WHITE);
                BoxPanel[i / 2][j / 2].add(TableDui[i][j]);
                BoxPanel[i / 2][j / 2].setBackground(Color.black);
                TableDui[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
            }
        }

        add(DuidokuPanel);

        if(error){
            for (int i = 1; i < SELECTIONS; i++) {
                TableCH[i] = new JButton(" " + i);
                TableCH[i].setBackground(Color.yellow);
                TableCH[i].addActionListener(new ListenerOfTampleCH(i));
                choosePanel.add(TableCH[i]);
            }
        }else {
            for (int i = 1; i < SELECTIONS; i++) {
                TableCH[i] = new JButton(" " + LETTERS[i]);
                TableCH[i].setBackground(Color.yellow);
                TableCH[i].addActionListener(new ListenerOfTampleCH(i));
                choosePanel.add(TableCH[i]);
            }
        }

        MainPanel.add(choosePanel);
        MainPanel.add(HELP);
        setLayout(new GridLayout(1, 2));
        setVisible(true);
    }

    /**
     *
     * Δημιουργουμε μια εσωτερικη κλαση της Sudoku οπου καθε φορα που ο χρηστης παταει ενα κουμπι
     * απο τις επιλογες 1-9 ενημερωνεται η κλαση και αποθηκευει το κουμπι
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    private class ListenerOfTampleCH implements ActionListener {

        private int x;
        private ListenerOfTampleCH(int x) {
            this.x = x;
        }
        @Override
        public void actionPerformed(ActionEvent e) {

            for (int i = 1; i <= SIZE_ARRAY; i++) {
                TableCH[i].setBackground(Color.yellow);
            }
            for (int i = 0; i < SIZE_ARRAY; i++) {
                for (int j = 0; j < SIZE_ARRAY; j++) {
                    for (ActionListener px : TableDui[i][j].getActionListeners()) {
                        TableDui[i][j].removeActionListener(px);
                    }
                }
            }
            for (int i = 0; i < SIZE_ARRAY; i++) {
                for (int j = 0; j < SIZE_ARRAY; j++) {
                    TableDui[i][j].addActionListener(new ListenerSudokuOfButtons(i, j, x));
                }
            }
            TableCH[x].setBackground(Color.lightGray);
        }
    }

    /**
     * Δημιουργουμε αλλη εσωτερικη κλαση η οποια καλειται απο την προηγουμενη κλαση και ουσιαστικα
     * τοποθετει στον πινακα το κουμπι που επελεξε ο χρηστης και επισης την κινηση του υπολογιστη
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    private class ListenerSudokuOfButtons extends JFrame implements ActionListener {

        private int x, y, item, posic;
        private String[] t;
        private ListenerSudokuOfButtons(int x, int y, int item) {

            this.x = x;
            this.y = y;
            this.item = item;

            posic = x * 4 + y;
            t = new String[5];
            t[1] = "A";
            t[2] = "B";
            t[3] = "C";
            t[4] = "D";
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean choiceHelp= HELP.isSelected();
            boolean moveBox=false;

            /*
             * Ελεγχουμε αν ο χρηστης ζητησε βοηθεια και εμφανιζουμε τα πληκτρα τα οποια επιτρεπεται
             * να πατησει με αλλο χρωμα
             *
             * @author Alexandros Vladovitis,Stelios Verros
             */

            if (choiceHelp) {
                moveBox=true;
                for (int i = 1; i <= SIZE_ARRAY; i++) {
                    TableCH[i].setBackground(Color.yellow);
                }
                for(int i=1;i<SELECTIONS;i++){
                    if(logicD.checkMove(posic,i)){
                        TableCH[i].setBackground(Color.magenta);
                    }
                }
            }

            /*
             * Ελεχγουμε αν ο χρηστης επιτρεπεται να κανει αυτη την κινηση ή οχι και του
             * εμφανιζει καταλληλο μηνυμα και εφοσον επιτρεπεται προσθετει την κινηση
             * στον πινακα
             *
             * @author Alexandros Vladovitis,Stelios Verros
             */

            if (logicD.checkMove(posic,item) && logicD.checkEmptyBox(posic) ) {
                for (int i = 1; i <= SIZE_ARRAY; i++) {
                    TableCH[i].setBackground(Color.yellow);
                }
                logicD.addMove(posic, item);
                if(error){
                    TableDui[x][y].setText(" " + item);
                    if(!logicD.TheEndOfTheGame()) {
                        logicD.TheComputerPlaying();
                        TableDui[logicD.setGetPosic()/4][logicD.setGetPosic()%4].setText(" "+logicD.setGetItem());
                    }
                }else {
                    TableDui[x][y].setText(" " + t[item]);
                    if(!logicD.TheEndOfTheGame()) {
                        logicD.TheComputerPlaying();
                        TableDui[logicD.setGetPosic()/4][logicD.setGetPosic()%4].setText(" "+logicD.setGetStringItem());
                    }
                }
            }else if(!moveBox){
                if(error){
                    JOptionPane.showMessageDialog(null, InterD.SMS[5],
                            InterD.SMS[6], JOptionPane.ERROR_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, InterD.SMS[7],
                            InterD.SMS[6], JOptionPane.ERROR_MESSAGE);
                }
            }
            for(int j=0;j<4;j++) {
                logicD.addBlackBox();
                for (int i = 0; i < 16; i++) {
                    if (logicD.getArray()[i] == 0)
                        TableDui[i / 4][i % 4].setBackground(Color.black);
                }
            }

            /*
             *
             * Με την βοηθεια της TheEndOfTheGame ελεγχουμε αν ο χρηστης εχει τελειωσει το παζλ με επιτυχια
             * και αν ναι χρησιμοπουμε την lastMove ωστε να του εμφανιζουμε αν κερδισε ή εχασε
             *
             * @author Alexandros Vladovitis,Stelios Verros
             */

            if(logicD.TheEndOfTheGame()){
                if(logicD.lastMove()){
                    JOptionPane.showMessageDialog(null, InterD.SMS[8]);
                }
                else {
                    JOptionPane.showMessageDialog(null, InterD.SMS[15]);
                }

                GridLayout Layout = new GridLayout(4,1);
                setTitle(InterD.SMS[13]);
                setSize(300, 200);
                setLocationRelativeTo(null);
                setResizable(false);

                /*
                 * Μολις τελειωσει το παιχνιδι εμφανιζουμε ενα μενου με επιλογες
                 * αν ο χρηστης επιθυμει να ξαναπαιξει,να τερματισει την εφαμρμογη ή
                 * να επιστρεψει στο μενου
                 *
                 * @author Alexandros Vladovitis,Stelios Verros
                 */

                returnGame = new JLabel(InterD.SMS[10]);
                JButton yas = new JButton();
                yas.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        returnGame.setText(InterD.SMS[12]);
                        if(returnGame.getText().equals(InterD.SMS[12])){
                            DuidokuICLI c = new DuidokuICLI(error);
                        }
                    }
                });
                yas.setText(InterD.SMS[12]);
                JButton no = new JButton(InterD.SMS[13]);
                no.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        returnGame.setText(InterD.SMS[14]);
                        if(returnGame.getText().equals(InterD.SMS[14])){
                            System.exit(0); // stop program
                            dispose(); // close window
                            setVisible(false); // hide window
                        }
                    }
                });
                no.setText(InterD.SMS[13]);
                JButton menu = new JButton(InterD.SMS[16]);
                menu.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        returnGame.setText(InterD.SMS[17]);
                        if(returnGame.getText().equals(InterD.SMS[17])){
                            printMenu menu = new printMenu();
                        }
                    }
                });
                FlowLayout aLayout = new FlowLayout();
                setLayout(aLayout);

                returnGame.setOpaque(false);
                returnGame.setBackground(Color.white);

                yas.setBackground(Color.lightGray);
                no.setBackground(Color.lightGray);
                menu.setBackground(Color.lightGray);
                setLayout(Layout);

                add(returnGame, BorderLayout.PAGE_START);
                add(yas, BorderLayout.CENTER);
                add(no,BorderLayout.CENTER);
                add(menu,BorderLayout.CENTER);
                setVisible(true);
            }
        }
    }
}