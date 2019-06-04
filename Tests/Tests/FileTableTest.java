package Tests;

import Model.Utils.FileTable;
import Model.Utils.Pair;
import groovy.util.GroovyTestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileTableTest{
    private FileTable ft;
    private Pair<String,BufferedReader> p;
    private BufferedReader br;
    @BeforeEach
    void setUp() {
        ft=new FileTable();
        try {
            this.br = new BufferedReader(new FileReader("C:\\Users\\Printu Marnii\\Desktop\\hw2\\src\\test.in"));
            this.p=new Pair<>("C:\\Users\\Printu Marnii\\Desktop\\hw2\\src\\test.in",this.br);
            ft.put(1,"C:\\Users\\Printu Marnii\\Desktop\\hw2\\src\\test.in",br);
        }catch (IOException ioe){System.out.println(ioe.toString());}
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void contains() {
        Assertions.assertTrue(ft.contains(1));
        Assertions.assertFalse(ft.contains(2));
    }

    @Test
    void get(){
        Assertions.assertEquals(ft.get(1).x,this.p.x);
        Assertions.assertNull(ft.get(2));
    }

    @Test
    void isEmpty() {
        Assertions.assertTrue(!ft.isEmpty());
    }
}