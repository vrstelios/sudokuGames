import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


/**
 *
 * Η Κλαση αυτη δημιουργει γραφικα για το παιχνιδι Sudoku
 *
 * @author Alexandros Vladovitis,Stelios Verros
 */


public class SudokuICLI extends JFrame {

    private SudokuLogic logic;
    private JButton[][] Buttons;
    private JButton[] choose;
    private International Inter;
    private JLabel returnGame;
    private boolean error;
    private JCheckBox HELPS;

    final int SIZE_ARRAY = 9;
    final int SELECTIONS = 10;

    /**
     * Ο κατασκευαστης δημιουργει τα buttons ,τις γλωσσες ,την επιλογη του
     * χρηστη ,την βοηθεια και το πανελ ωστε να εμφανιζεται σωστα
     *
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public SudokuICLI(boolean ChoicePlayer){


        Inter=new International();
        error=ChoicePlayer;
        Buttons = new JButton[SIZE_ARRAY][SIZE_ARRAY];
        choose = new JButton[SELECTIONS];
        logic = new SudokuLogic();
        String[] LETTERS = new String[SELECTIONS];

        HELPS = new JCheckBox(Inter.SMS[3],false);

        LETTERS[1] = "A";
        LETTERS[2] = "B";
        LETTERS[3] = "C";
        LETTERS[4] = "D";
        LETTERS[5] = "E";
        LETTERS[6] = "F";
        LETTERS[7] = "G";
        LETTERS[8] = "H";
        LETTERS[9] = "I";

        JPanel p1 = new JPanel(new GridLayout(6,1));
        JPanel p2 = new JPanel(new GridLayout(3,3));
        JPanel[][] p3 = new JPanel[3][3];
        JPanel p4 = new JPanel(new GridLayout(1,9));

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                p3[i][j]=new JPanel(new GridLayout(3,3));
                p2.add(p3[i][j]);
            }
        }

        setTitle("Classic Sudoku");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(p2);
        add(p1);

        for (int i = 0; i < SIZE_ARRAY; i++) {
            for (int j = 0; j < SIZE_ARRAY; j++) {
                Buttons[i][j] = new JButton(" ");
                Buttons[i][j].setBackground(Color.WHITE);
                p3[i / 3][j / 3].add(Buttons[i][j]);
                p3[i / 3][j / 3].setBackground(Color.black);
                Buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
            }
        }

        add(p2);

        if(error){
            for(int i=1;i<SELECTIONS;i++){
                choose[i] = new JButton(" "+i);
                choose[i].setBackground(Color.CYAN);
                choose[i].addActionListener(new ListenerOfChoose(i));
                p4.add(choose[i]);
            }
        }else {
            for(int i=1;i<SELECTIONS;i++){
                choose[i] = new JButton(" " + LETTERS[i]);
                choose[i].setBackground(Color.CYAN);
                choose[i].addActionListener(new ListenerOfChoose(i));
                p4.add(choose[i]);
            }
        }

        choose[0]=new JButton(Inter.SMS[4]);
        choose[0].setBackground(Color.green);
        choose[0].addActionListener(new ListenerOfChoose(0));

        p1.add(p4);
        p1.add(choose[0]);
        p1.add(HELPS);

        /*
         * Εδω φορτωνει το αρχειο απο την συναρτηση  logic.puzzle και το εμφανιζω στην οθονη
         *
         * @author Alexandros Vladovitis,Stelios Verros
         */

        logic.puzzle();
        for(int i=0;i<SIZE_ARRAY;i++){
            for(int j=0;j<SIZE_ARRAY;j++){
                int posic=i*9+j;
                if(logic.getArray()[posic] != -1){
                    int x=logic.getArray()[posic];
                    Buttons[i][j].setBackground(Color.PINK);
                    if(error){
                        Buttons[i][j].setText(x + "");
                    }else {
                        Buttons[i][j].setText(LETTERS[x] + "");
                    }
                }
            }
        }

        setLayout(new GridLayout(1,2));
        setVisible(true);
    }

    /**
     * Δημιουργουμε μια εσωτερικη κλαση της Sudoku οπου καθε φορα που ο χρηστης παταει ενα κουμπι
     * απο τις επιλογες 1-9 ενημερωνεται η κλαση και αποθηκευει το κουμπι
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    class ListenerOfChoose implements ActionListener{

        private int x;
        private ListenerOfChoose(int x){
            this.x = x;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            for(int i=0;i<=SIZE_ARRAY;i++) {
                if(i==0){
                    choose[i].setBackground(Color.green);
                }else {
                    choose[i].setBackground(Color.CYAN);
                }
            }
            for(int i=0;i<SIZE_ARRAY;i++){
                for(int j=0;j<SIZE_ARRAY;j++){
                    for (ActionListener px:Buttons[i][j].getActionListeners()){
                        Buttons[i][j].removeActionListener(px);
                    }
                }
            }
            for(int i=0;i<SIZE_ARRAY;i++){
                for(int j=0;j<SIZE_ARRAY;j++){
                    Buttons[i][j].addActionListener(new ListenerSudokuOfButtons(i,j,x));
                }
            }
            if (x!=0)
                choose[x].setBackground(Color.PINK);
            else
                choose[x].setBackground(Color.lightGray);
        }
    }

    /**
     * Δημιουργουμε αλλη εσωτερικη κλαση η οποια καλειται απο την προηγουμενη κλαση και ουσιαστικα
     * τοποθετει στον πινακα το κουμπι που επελεξε ο χρηστης
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    private class ListenerSudokuOfButtons extends JFrame implements  ActionListener  {

        private int x,y,item,posic;
        private String[] T;

        private ListenerSudokuOfButtons(int x,int y,int item){
            this.x = x;
            this.y = y;
            this.item = item;
            posic=x*9+y;
            T = new String[SELECTIONS];

            T[1] = "A";
            T[2] = "B";
            T[3] = "C";
            T[4] = "D";
            T[5] = "E";
            T[6] = "F";
            T[7] = "G";
            T[8] = "H";
            T[9] = "I";
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            boolean choiceHelp= HELPS.isSelected();
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
                    choose[i].setBackground(Color.cyan);
                }
                for(int i=1;i<SELECTIONS;i++){
                    if(logic.checkMove(posic,i)){
                        choose[i].setBackground(Color.pink);
                    }
                }
            }

            /*
             * Αφαιρουμε το κελι που επελεξε ο χρηστης και το συνδεουμε και με την λογικη
             *
             * @author Alexandros Vladovitis,Stelios Verros
             */

            if(item==0) {
                logic.removeMove(posic);
                if(logic.getArraycheck()[posic])
                    Buttons[x][y].setText(" ");
            }

            /*
             * Ελεχγουμε αν ο χρηστης επιτρεπεται να κανει αυτη την κινηση ή οχι και του
             * εμφανιζει καταλληλο μηνυμα και εφοσον επιτρεπεται προσθετει την κινηση
             * στον πινακα
             *
             * @author Alexandros Vladovitis,Stelios Verros
             */

            if (logic.checkMove(posic, item)  && item!=0 && logic.checkEmptyBox(posic)) {
                for (int i = 1; i <= SIZE_ARRAY; i++) {
                    choose[i].setBackground(Color.cyan);
                }
                logic.addMove(posic, item);
                if(error){
                    Buttons[x][y].setText(" " + item);
                }else {
                    Buttons[x][y].setText(" " + T[item]);
                }
            }
            else if( item !=0 && moveBox) {

                if(error){
                    JOptionPane.showMessageDialog(null, Inter.SMS[5],
                            Inter.SMS[6], JOptionPane.ERROR_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, Inter.SMS[7],
                            Inter.SMS[6], JOptionPane.ERROR_MESSAGE);
                }
            }

            /*
             * Με την βοηθεια την GameResult ελεγχουμε αν ο χρηστης εχει τελειωσει το παζλ με επιτυχια
             * και αν ναι εμφανιζουμε καταλληλα μηνυματα
             *
             * @author Alexandros Vladovitis,Stelios Verros
             */

            if(logic.GameResult()) {
                JOptionPane.showMessageDialog(null, Inter.SMS[8]);
                GridLayout Layout = new GridLayout(4,1);
                setTitle(Inter.SMS[13]);
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

                returnGame = new JLabel(Inter.SMS[10]);
                JButton yas = new JButton();
                yas.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        returnGame.setText(Inter.SMS[12]);
                        if(returnGame.getText().equals(Inter.SMS[12])){
                            SudokuICLI clasic=new SudokuICLI(error);
                        }
                    }
                });
                yas.setText(Inter.SMS[12]);
                JButton no = new JButton(Inter.SMS[13]);
                no.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        returnGame.setText(Inter.SMS[14]);
                        if(returnGame.getText().equals(Inter.SMS[14])){
                            System.exit(0); // stop program
                            dispose(); // close window
                            setVisible(false); // hide window
                        }
                    }
                });
                no.setText(Inter.SMS[13]);
                JButton menu = new JButton(Inter.SMS[16]);
                menu.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        returnGame.setText(Inter.SMS[17]);
                        if(returnGame.getText().equals(Inter.SMS[17])){
                            printMenu menu = new printMenu();
                        }
                    }
                });
                FlowLayout aLayout = new FlowLayout();
                setLayout(aLayout);

                returnGame.setOpaque(false);
                returnGame.setBackground(Color.WHITE);

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
