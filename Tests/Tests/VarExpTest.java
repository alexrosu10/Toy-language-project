package Tests;

import Model.Expression.ExpressionException;
import Model.Expression.VarExp;
import Model.Utils.Heap;
import Model.Utils.MyDictionary;
import groovy.util.GroovyTestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VarExpTest{
    private MyDictionary<String,Integer> st;
    private Heap heap;
    private VarExp ve;
    @BeforeEach
    void setUp() {
        st=new MyDictionary<>();
        st.put("a",20);
        heap=new Heap();
        ve=new VarExp("a");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void evaluate() throws ExpressionException {
        Assertions.assertEquals(ve.evaluate(st,heap),20);
        Assertions.assertNotEquals(ve.evaluate(st,heap),1);
    }
}