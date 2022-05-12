import org.junit.Test;

import static org.junit.Assert.*;

public class DuidokuLogicTest {

    @Test
    public void checkItemFalse() {
        DuidokuLogic LogicD = new DuidokuLogic();
        boolean x1=false;
        boolean x2=LogicD.checkItem(8);
        assertEquals(x1,x2);
    }

    @Test
    public void checkItemTrue() {
        DuidokuLogic LogicD = new DuidokuLogic();
        boolean x1=true;
        boolean x2=LogicD.checkItem(2);
        assertEquals(x1,x2);
    }


    @Test
    public void checkMoveTrue() {
        DuidokuLogic LogicD = new DuidokuLogic();
        boolean check=true;
        boolean result=LogicD.checkMove(5,2);
        assertEquals(result,check);
    }


    @Test
    public void checkEmptyBoxTrue() {
        DuidokuLogic LogicD = new DuidokuLogic();
        boolean check=true;
        boolean result=LogicD.checkEmptyBox(2);
        assertEquals(result,check);
    }

    @Test
    public void checkMoveColumnTrue() {
        DuidokuLogic LogicD = new DuidokuLogic();
        boolean check=true;
        boolean result=LogicD.checkMoveColumn(2,3);
        assertEquals(result,check);
    }
    @Test
    public void checkMoveLine() {
        DuidokuLogic LogicD = new DuidokuLogic();
        boolean check=true;
        boolean result=LogicD.checkMoveColumn(2,3);
        assertEquals(result,check);
    }
    @Test
    public void checkMoveBox() {
        DuidokuLogic LogicD = new DuidokuLogic();
        boolean check=true;
        boolean result=LogicD.checkMoveColumn(2,3);
        assertEquals(result,check);
    }
}