package Tests;

import Model.Expression.ArithExp;
import Model.Expression.ConstExp;
import Model.Expression.ExpressionException;
import Model.Expression.VarExp;
import Model.Utils.Heap;
import Model.Utils.MyDictionary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.Expression;

import static org.junit.jupiter.api.Assertions.*;

class ArithExpTest{
    private MyDictionary<String,Integer> st;
    private Heap heap;
    private ArithExp ae;
    @BeforeEach
    void setUp() {
        st=new MyDictionary<>();
        st.put("a",2);
        heap=new Heap();
        ae=new ArithExp(new ConstExp(2),new VarExp("a"),'+');
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void evaluate() throws ExpressionException {
        Assertions.assertEquals(ae.evaluate(st,heap),4);
        Assertions.assertNotEquals(ae.evaluate(st,heap),3);
    }
}