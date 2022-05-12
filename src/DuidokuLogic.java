import java.util.Random;

/**
 *
 * Η Κλαση αυτη δημιουργει λογικη για το παιχνιδι Duidoku
 * @author Alexandros Vladovitis,Stelios Verros
 */

public class DuidokuLogic {

    final int SIZE_ARRAY=16;
    private int[] array;
    private int sum=0;
    private int getItem,getPosic;

    /**
     * Ο κατασκευαστης δημιουργει τον πινακα array(ο οποιος αποθηκευει τις επιλογες του χρηστη)
     *
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public DuidokuLogic(){
        array=new int[SIZE_ARRAY];
        for(int i=0;i<SIZE_ARRAY;i++){
            array[i]=-1;
        }
    }

    /**
     *
     * Η addMove προσθετει το στοιχεο στον πινακα εφοσον μπορει να μπει το item  στην
     * θεση x που δεχεται ελεγχοντας αν εδωσε σωστο αριθμο και ειναι αδειο το κελι
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public void addMove(int x,int item){
        if(checkItem(item) && checkEmptyBox(x) && array[x]!=0 ){
            array[x]=item;
            sum++;
        }
    }

    /**
     * Η checkEmptyBox ελεγχει αν το item ειναι απο 1-4
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public boolean checkItem(int item){
        return item > 0 && item <= 4;
    }

    /**
     * Η checkMoveLine ελεγχει αν μπορει να μπει το item  στην
     * θεση x που δεχεται ελεγχοντας μονο την γραμμη του πινακα
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public boolean checkMoveLine(int x,int item){

        int line = x/4;
        int startLine =line*4;
        int endLine = startLine + 3;

        for(int i=startLine;i<=endLine;i++){
            if(item == array[i]){
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

        int startColumn = x % 4;//ευρεση στηλης
        int endColumn = 12 + startColumn;

        for (int i = startColumn; i <= endColumn; i += 4) {
            if(item==array[i]){
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

        int column = x % 4;
        int line = x / 4;
        int box = (line / 2) + (column / 2) + (line / 2);// ευρεση τετραγωνακι
        int startBoxLine = ((box / 2)* 8) + (box % 2 ) * 2;
        int endBoxLine = startBoxLine + 1;

        for(int i=startBoxLine;i<=endBoxLine;i++){
            for(int j=0;j<=4;j+=4){
                if(item==array[i+j]){
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
     *
     * Η checkEmptyBox ελεγχει αν το κελι περιεχει καποιον αριθμο και δεν σ αφηνει να το αλλαξεις
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public boolean checkEmptyBox(int x){
        return array[x] == -1;
    }

    /**
     *
     * Η checkBlackBox ελεγχει αν πρεπει να μπει μαυρο κουτι και χρησιμοποιωντας καταλληλες συναρτησεις
     * επιστρεφει την θεση αν πρεπει τελικα να μαυρισουμε το κελι
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public int checkBlackBox() {
        for (int i = 0; i < SIZE_ARRAY; i++) {
            if (array[i] == -1) {
                int sum = 0;
                for (int j = 1; j <= 4; j++) {
                    if (!checkMoveBox(i, j) || !checkMoveColumn(i, j) || !checkMoveLine(i, j))
                        sum += 1;
                }
                if (sum == 4)
                    return i;
            }
        }
        return -1;
    }

    /**
     * Η addBlackBox βαζει "μαυρο κουτι" στην λογικη το οποιο ειναι το 0
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public void addBlackBox(){
        if(checkBlackBox()!= -1) {
            array[checkBlackBox()] = 0;
        }
    }

    /**
     * Η TheComputerPlaying ειναι η κινηση του υπολογογιστη η οποια γινεται random ωστοσο
     * χρησιμοποιει τις προηγουμενες συναρτησεις για να αποφευχθει η λαθος κινηση(πχ μαυρο κουτι,
     * εχει το κελι ηδη αριθμο κτλ)
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public void TheComputerPlaying() {

        boolean check = false;

        while (!check) {
            Random r1 = new Random();
            Random r2 = new Random();
            int randomInt = r1.nextInt(16);
            int randomInt2 = r2.nextInt(4);
            randomInt2++;
            if (checkEmptyBox(randomInt)) {
                if (checkMove(randomInt, randomInt2)) {
                    array[randomInt] = randomInt2;
                    sum++;
                    check = true;
                    getItem=randomInt2;
                    getPosic=randomInt;
                }
            }
        }
    }

    public int setGetItem(){ return getItem; }

    public String setGetStringItem(){

        String[] T = new String[5];

        T[0] = "X";
        T[1] = "A";
        T[2] = "B";
        T[3] = "C";
        T[4] = "D";

        return T[getItem];
    }

    public int setGetPosic(){
        return getPosic;
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
        return sum == 16;
    }

    public int[] getArray() {
        return array;
    }

    /**
     *
     * Η lastMove ελεγχει το ποιος εχει κανει την τελευταια κινηση
     * και αν εχει κανει τελευαταια κινηση ο χρηστης επιστρεφει true αλλιως
     * false
     *
     * @author Alexandros Vladovitis,Stelios Verros
     */

    public boolean lastMove() { return sum % 2 != 0; }
}
