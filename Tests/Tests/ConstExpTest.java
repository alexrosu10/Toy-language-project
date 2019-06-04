package Tests;

import Model.Expression.ConstExp;
import Model.Utils.Heap;
import Model.Utils.MyDictionary;
import groovy.util.GroovyTestCase;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstExpTest {
    private MyDictionary<String,Integer> st;
    private Heap heap;
    private ConstExp ce;

    @BeforeEach
    void setUp() {
        st=new MyDictionary<>();
        heap=new Heap();
        ce=new ConstExp(2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void evaluate() {
        Assertions.assertEquals(ce.evaluate(st,heap),2);
        Assertions.assertNotEquals(ce.evaluate(st,heap),3);
    }

}