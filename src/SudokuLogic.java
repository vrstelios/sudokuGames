import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 *
 * Η Κλαση αυτη δημιουργει λογικη για το παιχνιδι Sudoku
 *
 * @author Alexandros Vladovitis,Stelios Verros
 */

public class SudokuLogic {

    final int SIZE_ARRAY = 81;
    private int[] array;
    private int[] arraySolver;
    private int[] arrayKiller;
    private String[] arrayKillerColours;
    private int rand;
    private boolean[] arraycheck;

    /**
     * Ο κατασκευαστης δημιουργει τους πινακες array(ο οποιος αποθηκευει τις επιλογες του χρηστη)
     * arraycheck(ο οποιος ελεγχει αν τα στοιχεια ειναι απο το αρχειο ή οχι)arraySolver(ο οποιος δεχεται
     * τις λυσεις του killer sudoku)arrayKiller(ο οποιος δεχεται τους αριθμους απο το αρχειο killer)
     * arrayKillerColours(ο οποιος δεχεται τα γραμματα του αρχειου killer ωστε να ξεχωριζει απο τους
     * υπολοιπους ιδιους αριθμους)
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public SudokuLogic() {

        array = new int[SIZE_ARRAY];
        arraycheck = new boolean[SIZE_ARRAY];
        arraySolver=new int[SIZE_ARRAY];
        arrayKiller=new int[SIZE_ARRAY];
        arrayKillerColours=new String[SIZE_ARRAY];

        for (int i = 0; i < SIZE_ARRAY; i++) {
            array[i] = -1;
            arraySolver[i]=-1;
            arrayKiller[i]=-1;
        }

        /*
         * Η checkMoveLine ελεγχει αν μπορει να μπει το item  στην
         * θεση x που δεχεται ελεγχοντας μονο την γραμμη του πινακα
         *
         * @author Alexandros Vladovitis,Stelios Verros
         */

    }

    public boolean checkMoveLine(int x, int item) {

        int line = x / 9; //ευρεση γραμμης
        int startLine = line * 9;
        int endLine = line * 9 + 8;
        for (int i = startLine; i <= endLine; i++) {
            if (item == array[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Η checkMoveColumn ελεγχει αν μπορει να μπει το item  στην
     * θεση x που δεχεται ελεγχοντας μονο την στηλη του πινακα
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public boolean checkMoveColumn(int x, int item) {

        int startColumn = x % 9;//ευρεση στηλης
        int endColumn = 72 + startColumn;
        for (int i = startColumn; i <= endColumn; i += 9) {
            if (item == array[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Η checkMoveBox ελεγχει αν μπορει να μπει το item  στην
     * θεση x που δεχεται ελεγχοντας μονο την κουτακι του πινακα
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public boolean checkMoveBox(int x, int item) {

        int column = x % 9;
        int line = x / 9;
        int box = (line / 3) + (column / 3) + (2 * (line / 3));// ευρεση τετραγωνακι
        int startBoxLine = ((box / 3) * 27) + (box % 3) * 3;
        int endBoxLine = startBoxLine + 2;
        for (int i = startBoxLine; i <= endBoxLine; i++) {
            for (int j = 0; j <= 18; j += 9) {
                if (item == array[i + j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Η checkMove ελεγχει αν μπορει να μπει το item  στην
     * θεση x που δεχεται ελεγχοντας ολο τον πινακα χρησιμοποιωντας και
     * τις προηγουμενες συναρτησεις
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public boolean checkMove(int x, int item) {
        return checkMoveBox(x, item) && checkMoveColumn(x, item) && checkMoveLine(x, item);
    }

    /**
     * Η addMove προσθετει το στοιχεο στον πινακα εφοσον μπορει να μπει το item  στην
     * θεση x που δεχεται ελεγχοντας αν εδωσε σωστο αριθμο και ειναι αδειο το κελι
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public void addMove(int x, int item) {
        if (checkItem(item) && checkEmptyBox(x)) {
            array[x] = item;
        }
    }

    /**
     * Η checkEmptyBox ελεγχει αν το κελι στην θεση χ ειναι αδειο και εφοσον ειναι = με -1
     * (δηλαδη ειναι αδειο λογω αρχικοποιησης)τοτε γυρναει true
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public boolean checkEmptyBox(int x) {
        //ελεχει αν εχει μπει στο κουτι αριθμος αν εχει μπει δεν σε αφηνει να το αλλαξεις
        return array[x] == -1;
    }

    /**
     * Η checkEmptyBox ελεγχει αν το item ειναι απο 1-9
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public boolean checkItem(int item) {
        return item > 0 && item < 10;
    }

    /**
     * Η checkPuzzle ελεγχει αν το κελι του πινακα ειναι απο το αρχειο ή οχι
     * και εφοσον ειναι δεν επιδεχεται αλλαγη αρα επιστρεφει false
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public void checkPuzzle(){
        for(int i=0;i<SIZE_ARRAY;i++){
            arraycheck[i]= array[i] == -1;
        }
    }

    /**
     * Η removeMove ελεγχει αν το κελι του πινακα ειναι απο το αρχειο ή οχι
     * και εφοσον δεν ειναι το αφαιρει
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public void removeMove(int x) {
        if(arraycheck[x])
            array[x] = -1;
    }

    /**
     * Η TheEndOfTheGame ελεγχει αν εχουν συμπληρωθει ολα τα κελια του πινακα
     * και αν ναι επιστεφει true
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public boolean TheEndOfTheGame() {

        int sum = 0;
        for (int i = 0; i < SIZE_ARRAY; i++) {
            if (array[i] != -1) {
                sum += 1;
            }
        }
        return sum == 81;
    }

    /**
     * Η GameResult ελεγχει χρησιμοποιωντας την προηγουμενη συναρτηση αν το αθροισμα
     * στηλων και γραμμων ειναι 405 αντοιστοιχα ωστε να επιστεψει true για να τελειωσει το παιχνιδι
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public boolean GameResult() {
        if (TheEndOfTheGame() ) {
            int sumLine = 0;//βρισκω το sum για καθε γραμμη
            for (int i = 0; i < SIZE_ARRAY; i += 9) {
                for (int j = i; j <= i + 8; j++) {
                    sumLine += array[j];
                }
            }
            int sumColumn = 0;//βρισκω το sum για καθε στηλη
            for (int i = 0; i < 9; i++) {
                for (int j = i; j < SIZE_ARRAY; j += 9) {
                    sumColumn += array[j];
                }
            }
            return sumColumn == 405 && sumLine == 405;
        }
        return false;
    }

    public int[] getArray() {
        return array;
    }

    public boolean[] getArraycheck(){return arraycheck;}

    /**
     * Η addMoveKiller προσθετει τον αριθμο του αρχειου στον πινακα arrayKiller
     * και το γραμμα c στο arrayKillerColours ωστε να ξεχωριζει απο τα αλλους παρομοιους αριθμους
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public void addMoveKiller(int x,int item,String c) {
        arrayKiller[x]=item;
        arrayKillerColours[x]=c;
    }

    public int[] getArrayKiller() {
        return arrayKiller;
    }

    public String[] getArrayKillerColours() {
        return arrayKillerColours;
    }

    /**
     * Η addMoveSolver προσθετει τον αριθμο του αρχειου Solver στον πινακα arrayKillerSolver
     * ωστε να ελεχθει η λυση
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public void addMoveSolver(int x,int item){
        arraySolver[x]=item;
    }

    /**
     * Η puzzle αρχικα δημιουργει ενα random στοιχειο ωστε να διαβασει απο τα 10 αρχεια ενα αρχειο
     * στην τυχη καθε φορα και μετα  διαβαζει εναν εναν τον χαρακτηρα του text και καθε φορα
     * που ο χαρακτηρας ειναι τελεια(δηλαδη στο unicode =46)τοτε ανεβαζει το sum(που ειναι η θεση στον πινακα)
     * και αν δεν ειναι τελεια τοτε βαζει την κινηση στον πινακα χρησιμοποιωντας την addMove
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public void puzzle() {
        try {
            Random rand = new Random();
            int randomSudoku = rand.nextInt(10);
            randomSudoku++;
            FileReader in = new FileReader("sudoku_" + randomSudoku + ".txt");
            int c;
            int sum = 0;
            while ((c = in.read()) != -1) {
                char c1 = (char) c;
                if (c1 != 46) {
                    int item = c1 - 48;
                    addMove(sum, item);
                }
                sum++;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        checkPuzzle();
    }

    /**
     * Η puzzleKillerSolver χρησιμοποιει το ιδιο rand με την συναρτηση puzzleKiller ωστε να ταιριαξει τα txt files
     * και περναει την κινηση στην addMoveSolver
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public void puzzleKillerSolver() {

        try
        {
            BufferedReader in = new BufferedReader(new FileReader("killer_sudoku_solve_" + rand +".txt"));
            String l;
            int sum = 0;
            while ((l = in.readLine()) != null) {
                int result = Integer.parseInt(l);
                addMoveSolver(sum,result);
                sum++;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Η puzzleKillerSolver χρησιμοποιει ενα rand ωστε να παρει τυχαι ενα killer sudoku txt file
     * και χρησιμοποιωντας τα string και τις συναρτησεις valueof και charAt ξεχωριζουμε τους αριθμους με
     * τα γραμματα και αντιστοιχα τα περναμε στις αντοιστιχες συναρτησεις
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public void puzzleKiller() {

        try
        {
            Random rand1 = new Random();
            int randomSudoku1 = rand1.nextInt(10);
            randomSudoku1++;
            rand=randomSudoku1;
            BufferedReader in = new BufferedReader(new FileReader("killer_sudoku_"+ rand + ".txt"));
            String l;
            int sum=0;
            while ((l = in.readLine()) != null) {
                String c="";
                StringBuilder number= new StringBuilder();
                int len=l.length();
                for(int i=0;i<len;i++)
                    if (i== (len-1)){
                        c= String.valueOf((l.charAt(len-1)));
                    }else
                        number.append(String.valueOf((l.charAt(i))));
                int result = Integer.parseInt(number.toString());
                addMoveKiller(sum,result,c);
                sum++;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Η FinishKiller ελεχγει τον πινακα arraySolver των λυσεων και τον πινακα array των επιλογων του
     * χρηστη ωστε να επιστρεψει true για να τερματισει το παιχνιδι
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public boolean FinishKiller(){

        int sum=0;
        for(int i=0;i<SIZE_ARRAY;i++){
            if(arraySolver[i]==array[i])
                sum++;
        }
        return sum == 81;
    }
}
