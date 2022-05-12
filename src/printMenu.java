import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * Η Κλαση αυτη δημιουργει μενου για να επιλεξει ποιο παιχνδι θα παιξει και αν θα συνεχισει μετα
 *
 * @author Alexandros Vladovitis,Stelios Verros
 */
public class printMenu extends JFrame{

    private JLabel label1;
    private boolean stop=false;
    public int chooseM;


    public printMenu(){

        GridLayout Layout = new GridLayout(4,1);
        International inter = new International();

        setTitle(inter.SMS[24]);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        label1 = new JLabel(inter.SMS[25]);
        JButton button1 = new JButton();
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label1.setText("         Classic Sudoku");
                if(label1.getText().equals("         Classic Sudoku")){
                    chooseM=1;
                    NumbersOrLetters letters = new NumbersOrLetters(chooseM);
                    stop=true;
                }
            }
        });

        button1.setText("Classic Sudoku");
        JButton button3 = new JButton("   Duidoku  ");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label1.setText("             Duidoku");
                if(label1.getText().equals("             Duidoku")){
                    chooseM=2;
                    NumbersOrLetters Letters = new NumbersOrLetters(chooseM);
                    stop=true;
                }
            }
        });

        button3.setText("   Duidoku   ");
        JButton button2 = new JButton("Killer Sudoku");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label1.setText("            Killer Sudoku");
                if(label1.getText().equals("            Killer Sudoku")){
                    chooseM=3;
                    KillerSudokuICLI killer = new KillerSudokuICLI(true);
                    stop=true;
                }
            }
        });

        FlowLayout aLayout = new FlowLayout();
        setLayout(aLayout);

        label1.setFont(Font.decode("Verdana-bold-20"));
        label1.setOpaque(false);
        label1.setBackground(Color.white);

        button1.setBackground(Color.lightGray);
        button2.setBackground(Color.magenta);
        button3.setBackground(Color.yellow);
        setLayout(Layout);

        add(label1, BorderLayout.PAGE_START);
        add(button1, BorderLayout.CENTER);
        add(button2,BorderLayout.CENTER);
        add(button3, BorderLayout.CENTER);

        if(stop){
            System.exit(0); // stop program
            dispose(); // close window
            setVisible(false); // hide window
        }else {
            setVisible(true);
        }
    }
}
