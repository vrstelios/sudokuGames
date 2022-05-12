import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuLogicTest {
    @Test
    public void checkMoveLine() {
        SudokuLogic Logic = new SudokuLogic();
        boolean check=true;
        boolean result=Logic.checkMoveLine(5,2);
        assertEquals(result,check);
    }


    @Test
    public void checkMoveColumn() {
        SudokuLogic Logic = new SudokuLogic();
        boolean check=true;
        boolean result=Logic.checkMoveColumn(5,2);
        assertEquals(result,check);
    }

    @Test
    public void checkMoveBox() {
        SudokuLogic Logic = new SudokuLogic();
        boolean check=true;
        boolean result=Logic.checkMoveBox(5,2);
        assertEquals(result,check);
    }

    @Test
    public void checkMove() {
        SudokuLogic Logic = new SudokuLogic();
        boolean check=true;
        boolean result=Logic.checkMove(5,2);
        assertEquals(result,check);
    }

    @Test
    public void checkEmptyBox() {
        SudokuLogic Logic = new SudokuLogic();
        boolean check=true;
        boolean result=Logic.checkEmptyBox(5);
        assertEquals(result,check);
    }

    @Test
    public void checkItemTrue() {
        SudokuLogic Logic = new SudokuLogic();
        boolean check=true;
        boolean result=Logic.checkItem(5);
        assertEquals(result,check);
    }

    @Test
    public void checkItemFalse() {
        SudokuLogic Logic = new SudokuLogic();
        boolean check=false;
        boolean result=Logic.checkItem(25);
        assertEquals(result,check);
    }

}