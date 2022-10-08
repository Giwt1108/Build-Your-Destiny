package estructuras;

public class PilaEnlazada <T>{
    private Node top;

    public boolean empty(){
        return top.getNext() == null;
    }

    public boolean full(){ //Nunca se llena, a menos que nos quedemos sin memoria
        return false;
    }

    public void push(int value){
        Node aux = new Node(value);
        aux.setNext(this.top);
        this.top = aux;
        //Destruir aux? No es necesario, es inocuo y solo come como 8 bytes
    }

    public T pop(){

        if(!empty()){
            T value = (T) this.top.getData();
            top = this.top.getNext(); //Guardamos la direccion del siguiente nodo
            return value;
        }else{
            throw new IllegalArgumentException("Esta vacio pa");
        }

    }

    public T toString(Node node){
        if(node.getNext()== null){
            return null;
        }
        else{
            System.out.println(node.getData());
            return toString(node.getNext());
        }
    }

    public PilaEnlazada(){
        this.top = new Node();
    }

    public PilaEnlazada(int value){
        Node node = new Node(value);
        this.top = node;
    }
}
