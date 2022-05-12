import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 *
 * Η Κλαση αυτη δημιουργει γραφικα για το παιχνιδι Killer Sudoku
 *
 * @author Alexandros Vladovitis,Stelios Verros
 */


public class KillerSudokuICLI extends JFrame {

    private SudokuLogic logicK;
    private JButton[][] ButtonsK;
    private International Inter;
    private JButton[] chooseK;
    private JLabel returnGame;
    private JCheckBox HELPK;

    final int SIZE_ARRAY = 9;
    final int SELECTIONS = 10;

    /**
     *
     * Ο κατασκευαστης δημιουργει τα buttons ,τις γλωσσες ,την επιλογη του
     * χρηστη ,την βοηθεια και το πανελ ωστε να εμφανιζεται σωστα
     *
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public KillerSudokuICLI(boolean ChoicePlayer){

        ButtonsK = new JButton[SIZE_ARRAY][SIZE_ARRAY];
        chooseK = new JButton[SELECTIONS];
        Inter=new International();
        HELPK = new JCheckBox(Inter.SMS[3],false);
        logicK = new SudokuLogic();
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

        setTitle("Killer Sudoku");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(p2);
        add(p1);

        for (int i = 0; i < SIZE_ARRAY; i++) {
            for (int j = 0; j < SIZE_ARRAY; j++) {
                ButtonsK[i][j] = new JButton(" ");
                ButtonsK[i][j].setBackground(Color.WHITE);
                p3[i / 3][j / 3].add(ButtonsK[i][j]);
                p3[i / 3][j / 3].setBackground(Color.black);
                ButtonsK[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
            }
        }

        add(p2);

        for(int i=1;i<SELECTIONS;i++){
            chooseK[i] = new JButton(" "+i);
            chooseK[i].setBackground(Color.CYAN);
            chooseK[i].addActionListener(new ListenerOfChooseKiller(i));
            p4.add(chooseK[i]);
        }

        chooseK[0]=new JButton(Inter.SMS[4]);
        chooseK[0].setBackground(Color.green);
        chooseK[0].addActionListener(new ListenerOfChooseKiller(0));

        p1.add(p4);
        p1.add(chooseK[0]);
        p1.add(HELPK);

        /*
         * Εδω φορτωνει το αρχειο απο την συναρτηση  logic.puzzleKiller και το εμφανιζω στην οθονη
         *
         * @author Alexandros Vladovitis,Stelios Verros
         */

        logicK.puzzleKiller();
        for(int i=0;i<SIZE_ARRAY;i++){
            for(int j=0;j<SIZE_ARRAY;j++){
                int posic=i*9+j;
                int x=logicK.getArrayKiller()[posic];
                //int y=logicK.getArray()[posic];
                ButtonsK[i][j].setBackground(Color.PINK);
                ButtonsK[i][j].setHorizontalAlignment( ButtonsK[i][j].LEFT );
                ButtonsK[i][j].setText(x + "");
                ButtonsK[i][j].setText(x + "");
                ButtonsK[i][j].setFont(ButtonsK[i][j].getFont().deriveFont(11.0f));
            }
        }

        /*
         * Εδω φορτωνει το αρχειο λυσης απο την συναρτηση  logic.puzzleKillerSolver και το εμφανιζω στην οθονη
         * με random color ελεγχοντας αν εχει το ιδιο γραμμα και τον ιδιο αριθμο με τις δυο καταλληλες
         * συναρτησεις
         *
         * @author Alexandros Vladovitis,Stelios Verros
         */

        logicK.puzzleKillerSolver();
        for(int i=0;i<81;i++){
            Random rand = new Random();
            Color color = new Color(rand.nextInt(256*256*256));

            for(int j=0;j<81;j++){
                if ((logicK.getArrayKiller()[i]==logicK.getArrayKiller()[j] )){
                    if( logicK.getArrayKillerColours()[j].equals(logicK.getArrayKillerColours()[i]) ){
                        int line = j / 9;
                        int column = j % 9;
                        ButtonsK[line][column].setBackground(color);
                    }
                }
            }
        }

        setLayout(new GridLayout(1,2));
        setVisible(true);
    }


    /**
     *
     * Δημιουργουμε μια εσωτερικη κλαση της Sudoku οπου καθε φορα που ο χρηστης παταει ενα κουμπι
     * απο τις επιλογες 1-9 ενημερωνεται η κλαση και αποθηκευει το κουμπι
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */


    private class ListenerOfChooseKiller implements ActionListener {

        private int x;
        private ListenerOfChooseKiller(int x){
            this.x = x;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i=0;i<=SIZE_ARRAY;i++) {
                if(i==0){
                    chooseK[i].setBackground(Color.green);
                }else {
                    chooseK[i].setBackground(Color.CYAN);
                }
            }
            for(int i=0;i<SIZE_ARRAY;i++){
                for(int j=0;j<SIZE_ARRAY;j++){
                    for (ActionListener px:ButtonsK[i][j].getActionListeners()){
                        ButtonsK[i][j].removeActionListener(px);
                    }
                }
            }
            for(int i=0;i<SIZE_ARRAY;i++){
                for(int j=0;j<SIZE_ARRAY;j++){
                    ButtonsK[i][j].addActionListener(new ListenerSudokuOfButtonsKiller(i,j,x));
                }
            }
            if (x!=0)
                chooseK[x].setBackground(Color.PINK);
            else
                chooseK[x].setBackground(Color.lightGray);
        }
    }

    /**
     *
     * Δημιουργουμε αλλη εσωτερικη κλαση η οποια καλειται απο την προηγουμενη κλαση και ουσιαστικα
     * τοποθετει στον πινακα το κουμπι που επελεξε ο χρηστης
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    private class ListenerSudokuOfButtonsKiller extends JFrame implements  ActionListener  {

        private int x,y,item,posic;

        private ListenerSudokuOfButtonsKiller(int x,int y,int item){
            this.x = x;
            this.y = y;
            this.item = item;
            posic=x*9+y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            boolean choiceHelp= HELPK.isSelected();
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
                    chooseK[i].setBackground(Color.cyan);
                }
                for(int i=1;i<SELECTIONS;i++){
                    if(logicK.checkMove(posic,i)){
                        chooseK[i].setBackground(Color.pink);
                    }
                }
            }

            /*
             * Αφαιρουμε το κελι που επελεξε ο χρηστης και το συνδεουμε και με την λογικη
             *
             * @author Alexandros Vladovitis,Stelios Verros
             */

            if(item==0) {
                logicK.removeMove(posic);
                ButtonsK[x][y].setText( logicK.getArrayKiller()[posic] +" ");
            }

            /*
             * Ελεχγουμε αν ο χρηστης επιτρεπεται να κανει αυτη την κινηση ή οχι και του
             * εμφανιζει καταλληλο μηνυμα και εφοσον επιτρεπεται προσθετει την κινηση
             * στον πινακα
             *
             * @author Alexandros Vladovitis,Stelios Verros
             */

            if (logicK.checkMove(posic, item)  && item!=0) {
                for (int i = 1; i <= SIZE_ARRAY; i++) {
                    chooseK[i].setBackground(Color.cyan);
                }
                logicK.addMove(posic, item);
                ButtonsK[x][y].setText( logicK.getArrayKiller()[posic] +"      " + item);
            }
            else if( item !=0 && !moveBox) {
                JOptionPane.showMessageDialog(null, Inter.SMS[5], Inter.SMS[6], JOptionPane.ERROR_MESSAGE);
            }

            /*
             * Με την βοηθεια την GameResult και FinishKiller ελεγχουμε αν ο χρηστης εχει τελειωσει το παζλ με επιτυχια
             * και αν ναι εμφανιζουμε καταλληλα μηνυματα
             *
             * @author Alexandros Vladovitis,Stelios Verros
             */

            if(logicK.GameResult() && logicK.FinishKiller()) {
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
                            KillerSudokuICLI clasic=new KillerSudokuICLI(true);
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
                returnGame.setBackground(Color.white);

                yas.setBackground(Color.WHITE);
                no.setBackground(Color.WHITE);
                menu.setBackground(Color.WHITE);
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
