package Tests;

import Model.Expression.ExpressionException;
import Model.Expression.ReadHeap;
import Model.Utils.Heap;
import Model.Utils.MyDictionary;
import groovy.util.GroovyTestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadHeapTest {
    private Heap heap;
    private MyDictionary<String,Integer> st;
    private ReadHeap rh;

    @BeforeEach
    void setUp() {
        heap=new Heap();
        heap.put(20);
        st=new MyDictionary<>();
        st.put("a",1);
        rh=new ReadHeap("a");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void evaluate() throws ExpressionException {
        Assertions.assertEquals(rh.evaluate(st,heap),20);
        Assertions.assertNotEquals(rh.evaluate(st,heap),1);
    }

}