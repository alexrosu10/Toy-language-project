package Tests;

import Model.Utils.Heap;
import groovy.util.GroovyTestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest{
    private Heap heap;

    @BeforeEach
    void setUp() {
        this.heap=new Heap();
        this.heap.put(10);
        this.heap.put(20);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void contains() {
        Assertions.assertTrue(heap.contains(1));
        Assertions.assertTrue(heap.contains(2));
        Assertions.assertFalse(heap.contains(3));
    }

    @Test
    void get() {
        Assertions.assertEquals(10, (int) heap.get(1));
        Assertions.assertEquals(20, (int) heap.get(2));
    }

    @Test
    void isEmpty() {
        Assertions.assertFalse(heap.isEmpty());
    }
}