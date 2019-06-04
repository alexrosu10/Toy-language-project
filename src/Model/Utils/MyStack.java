package Model.Utils;

import java.util.Stack;

public class MyStack<T> implements IStack<T>{
    Stack<T> stack;

    public MyStack(){
        stack = new Stack<T>();
    }

    @Override
    public void push(T elem) {
        //push element in stack
        stack.push(elem);
    }


    @Override
    public T pop() {
        //pop element from stack
        //return the popped elem
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
        //stack.pick()
    }

    public Stack<T> getStack(){
        return this.stack;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(T i : stack)
            s.append(i.toString()).append("\n");
        return s.toString();
    }

    @Override
    public T top() {
        T el=stack.pop();
        stack.push(el);
        return el;
    }
}
