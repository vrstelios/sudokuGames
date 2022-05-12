import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *Δημιουργουμε μια κλαση που δεχεται ως ορισμα ποιο παιχνιδι διαλεξε ο χρηστης και στην συνεχεια δημιουργει την
 * καταλληλη κλαση για το καταλληλο παιχνιδι και στελνει ενα ορισμα αν ο χρηστης εχει επιλεξει να παιζει με
 * γραμματα ή αριθμους
 *
 * @author Alexandros Vladovitis,Stelios Verros
 */

public class NumbersOrLetters extends JFrame {

    private JLabel choose;
    private boolean ChoicePlayer;
    private International Inter;

    public NumbersOrLetters(int chooseM){

        GridLayout Layout = new GridLayout(3,1);
        Inter=new International();

        setTitle(Inter.SMS[26]);
        setSize(550, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        choose = new JLabel(Inter.SMS[27]);
        JButton letters = new JButton();
        letters.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choose.setText(Inter.SMS[28]);
                if(choose.getText().equals(Inter.SMS[28])){
                    ChoicePlayer=true;
                    if(chooseM==1){
                        SudokuICLI clasic=new SudokuICLI(ChoicePlayer);
                    }else if(chooseM==2){
                        DuidokuICLI duidoku=new DuidokuICLI(ChoicePlayer);
                    }
                }
            }
        });

        letters.setText(Inter.SMS[28]);
        JButton numbers = new JButton(Inter.SMS[29]);
        numbers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choose.setText(Inter.SMS[29]);
                if(choose.getText().equals(Inter.SMS[29])){
                    ChoicePlayer=false;
                    if(chooseM==1){
                        SudokuICLI clasic=new SudokuICLI(ChoicePlayer);
                    }else if(chooseM==2){
                        DuidokuICLI duidoku=new DuidokuICLI(ChoicePlayer);
                    }
                }
            }
        });

        FlowLayout aLayout = new FlowLayout();
        setLayout(aLayout);

        choose.setFont(Font.decode("Verdana-bold-20"));
        choose.setBackground(Color.white);

        letters.setBackground(Color.lightGray);
        numbers.setBackground(Color.LIGHT_GRAY);
        setLayout(Layout);

        add(choose, BorderLayout.PAGE_START);
        add(letters, BorderLayout.CENTER);
        add(numbers,BorderLayout.CENTER);

        setVisible(true);
    }
}
