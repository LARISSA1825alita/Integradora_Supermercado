package com.integradora.supermercadointegradora.Custom;

import java.util.LinkedList;
import java.util.Queue;

public class CustomQueue<T> {
    private Queue<T> queue;

    public CustomQueue() {
    //contructor  para inicializar a la cola
        this.queue = new LinkedList<>();
    }
    // metodo que se agrege un  elemento al final de la cola
    public void enqueue(T item) {
        //con el add se va a agregar el elemtno al final de la cola
        queue.add(item);
    }
    // metodo para eliminar y regresar el primer elemento de la cola
    public T dequeue() {
        //el if va a verificar  si la cola esta vacia antes de eliminar
        if (queue.isEmpty()) {
            throw new IllegalStateException("Cola vacia");
        }
    //si la cola no esta vacia va a eliminar y aregresar al primero
        return queue.poll();
    }
  //el metodo va a mostrar el primer elem de la cola pero no se elimina
    public T peek() {
        //el if va a checar que la cola si este vacia
        if (queue.isEmpty()) {
            //si si esta vacia va a mandar una ecepcion
            throw new IllegalStateException("Cola vacia");
        }
        //y si no , va a retornar al primero pero no eliminaria
        return queue.peek();
    }
//y este metodo checaria si la cola esta vacia
    public boolean isEmpty() {
        //va a retornar qie si esta vacia y si no seria falso de que no
        return queue.isEmpty();
    }
//metodo para saber el tamaño de la cola
    public int size() {
  //y va a retornar el numero de elementos en la cola
        return queue.size();
    }

    // los getters y Setters
    public Queue<T> getQueue() {
        return queue;
    }
    public void setQueue(Queue<T> queue) {
        this.queue = queue;
    }
}

