package com.integradora.supermercadointegradora.service;

import java.util.Arrays;

public class Queue <T>{
    int tail;
    int head;
    int itemsNumber;
    int arrLength;

    T[] items;

    @Override
    public String toString() {
        return "Queue "+ Arrays.toString(items);
    }

    Queue(int arrLength){
        this.arrLength = arrLength;
        this.head=0;
        this.tail=-1;
        this.itemsNumber=0;
        this.items= (T[]) new Object[arrLength];
    }

    public boolean isEmpty(){
        return (itemsNumber==0);
    }

    public boolean isFull(){
        return (itemsNumber==arrLength);
    }

    public void offer (T item){
        if(isFull()){
            System.out.println("the Queue is Full");
            return;
        }
        tail=(tail+1)%arrLength;
        items[tail]=item;
        itemsNumber++;
    }

    public T poll(){
        if(isEmpty()){
            System.out.println("the Queue is Empty");
            return null;
        }
        T item=items[head];
        head=(head+1)%arrLength;
        itemsNumber--;
        return item;
    }

    public T peek(){
        if(isEmpty()){
            System.out.println("the Queue is Empty");
            return null;
        }
        return items[head];
    }

}

