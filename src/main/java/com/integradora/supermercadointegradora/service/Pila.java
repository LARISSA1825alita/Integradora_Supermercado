package com.integradora.supermercadointegradora.service;

import java.util.Arrays;

public class Pila<T>{
    int Capacity;
    int size;

    T[] items;

    @SuppressWarnings("unchecked")
    Pila(int Capacity){
        this.size=-1;
        this.Capacity=Capacity;
        this.items=(T[]) new Object[Capacity];
    }

    @Override
    public String toString() {
        return "Pila:  " +Arrays.toString(items);
    }

    public void push(T item){
        if(isFull()){
            System.out.println("El stack ya esta lleno");
            return;
        }
        items[++size]=item;
    }

    public T pop(){
        if(isEmpty()){
            System.out.println("El Stack esta vacio");
            return null;
        }
        return items[size--];
    }

    public T peek(){
        if (isEmpty()){
            System.out.println("Stack is empty");
            return null;
        }
        return items[size];
    }

    public boolean isEmpty(){
        return (size == -1);
    }

    public boolean isFull(){
        return (size == Capacity-1);
    }
}


