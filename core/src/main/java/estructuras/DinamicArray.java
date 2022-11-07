package estructuras;


import java.util.ArrayList;
public class DinamicArray<T> {

    private ArrayList<T> arr;
    private int capacity;
    private int size;
    private int resize;

    //CONSTRUCTORES
    public DinamicArray(int resize, int capacity){
        this.arr = new ArrayList<T>(capacity);
        this.capacity = capacity;
        this.size = 0;
        this.resize = resize;

    }

    public DinamicArray(int capacity){
        this(2,capacity);
    }

    public DinamicArray(){
        this(2,2);
    }

    //METODOS

    public void set(int i, T data){
        if(i<0 || i>=this.capacity){
            throw new IllegalArgumentException("Out of index");
        }
        this.arr.set(i, data);
    }

    public T get(int i){
        if(i<0 || i>=this.capacity){
            throw new IllegalArgumentException("Out of index");
        }
        return this.arr.get(i);
    }

    private void resize(){
        this.capacity *= this.resize;
        ArrayList<T> aux = new ArrayList<T>(this.capacity);
        for(int i=0; i<this.size; i++){
            aux.add(this.arr.get(i));
        }
        this.arr = aux;

    }

    public void pushBack(T data){
        if(this.size == this.capacity){
            resize();
        }
        this.arr.add(data);
        this.size ++;
    }


    public void removes(int n){
        if(n<0 || n>=this.size){
            throw new IllegalArgumentException("Out of index");
        }

        for(int i=n; i<(this.size-1); i++){

            this.arr.set(i, this.arr.get(i+1));

        }
        this.size --;
    }

    public int size() {
        return size;
    }

    public void stringTo(){
        String cad = "[ ";
        for(int i=0; i<this.size; i++){
            cad += this.arr.get(i) + " ";
        }

        System.out.println(cad + "]");
    }

    //GETTERS AND SETTERS

    public ArrayList<T> getArr() {
        return arr;
    }

    public void setArr(ArrayList<T> arr) {
        this.arr = arr;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getResize() {
        return resize;
    }

    public void setResize(int resize) {
        this.resize = resize;
    }


}


