package Tests;

import Model.Utils.MyDictionary;
import groovy.util.GroovyTestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyDictionaryTest {
    MyDictionary<String,Integer> st;

    @BeforeEach
    void setUp() {
        st=new MyDictionary<>();
        st.put("v",2);
    }

    @Test
    void contains() {
        Assertions.assertTrue(st.contains("v"));
        Assertions.assertFalse(st.contains("k"));
    }

    @Test
    void get() {
        Assertions.assertEquals((int)st.get("v"),2);
    }
}