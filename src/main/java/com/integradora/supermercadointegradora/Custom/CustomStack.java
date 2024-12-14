package com.integradora.supermercadointegradora.Custom;
import java.util.ArrayList;
import java.util.List;

public class CustomStack<T> {
    private List<T> stack;

    public CustomStack() {
        // constructor para inicializar la pila como una lista vacia
        this.stack = new ArrayList<>();
    }

    public void push(T item) {
// se agregan  elementos al final de la lista
        stack.add(item);
    }

    public T pop() {
 //se vrificara que la lista este vacia
        if (stack.isEmpty()) {
            throw new IllegalStateException("Pila vacia");
        }
// se elimina y devuelve el último elemento de la lista
        return stack.remove(stack.size() - 1);
    }

    public T peek() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Pila vacia");
        }
        // último elemento de la lista sin eliminarlo
        return stack.get(stack.size() - 1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    // Getters and Setters
    public List<T> getStack() {
//lista que representa la pila
        return stack;
    }

    public void setStack(List<T> stack) {
        this.stack = stack;
    }
}

