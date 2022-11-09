package estructuras;


public class Heaps<T extends Comparable<T>> {

    private DinamicArray<T> array;



    //CONSTRUCTORES

    public Heaps(){
        this.array = new DinamicArray();
    }

    //METODOS

    //SIFTS
    // n = node prioriry, i = index
    public void siftUp(int i){
        int j  = (i-1)/2;
        T parent = array.get(j);
        T n = array.get(i);
        while(parent.compareTo(n) < 0 && i != 0){
            array.set(i, parent);
            array.set(j, n);
            i = j;
            j  = (i-1)/2;
            parent = array.get(j);
            n = array.get(i);
        }

    }

    public int olderChildIndex(int i){
        int left = 2*i +1;
        int right = 2*i +2;
        if(left >= array.size() && right >= array.size()){ //Si no tiene hijos
            return i;
        } else if(right >= array.size()){ //Si solo tiene hijo izquierdo
            return left;
        }
        else if(array.get(left).compareTo( array.get(right)) > 0){ // si el izquiero es el mayor
            return left;
        }else{
            return right;
        }
    }

    public void siftDown(int i){
        int childIndx = olderChildIndex(i);
        T n = array.get(i);
        T child = array.get(childIndx);
        while( n.compareTo(child) < 0  && i<array.size()){
            array.set(childIndx, n);
            array.set(i, child);
            i = childIndx;
            childIndx = olderChildIndex(i);
            child = array.get(childIndx);
            n = array.get(i);

        }

    }


    //INSERT
    public void insert(T element){
        array.pushBack(element);
        siftUp(array.size() - 1);

    }

    //EXTRACT

    public T extractMax(){
        if(array.size() == 0){
            throw new IllegalArgumentException("Ya estpa vacío pa");
        }
        T max = array.get(0);
        array.set( 0, array.get(array.size() - 1) );
        array.removes(array.size() - 1);
        siftDown(0);
        return max;

    }

    //GET

    public T getMax(){
        if(array.size() == 0){
            throw new IllegalArgumentException("Esta vacio pa");
        }
        return array.get(0);

    }

    //REMOVE

    public void remove(int index){
        array.set(index, getMax());
        siftUp(index);
        extractMax();
    }


    //CHANGE PRIORITY

    //p = priority
    public void changePriority(int index, T p){
        T old = array.get(index);
        array.set(index, p);
        if(p.compareTo(old) > 0){
            siftUp(index);
        }else if(p.compareTo(old) < 0){
            siftDown(index);
        }

    }

    //GETTERS AND SETTERS

    public DinamicArray getArray() {
        return array;
    }

    public void setArray(DinamicArray array) {
        this.array = array;
    }

}
