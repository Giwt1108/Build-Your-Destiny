package estructuras;

public class ListaEnlazada <T>{
    private Node head;
    private Node tail;
    private int size;

    public ListaEnlazada(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public ListaEnlazada clean(){
        this.head = null;
        this.tail = null;
        this.size = 0;
        return this;
    }


    public void setTail(Node tail){
        this.tail = tail;
    }

    public Node getTail(){
        return this.tail;
    }

    public void setHead(Node head){
        this.head = head;
    }

    public Node getHead(){
        return this.head;
    }

    public int size(){
        return this.size;
    }

    public ListaEnlazada unir(ListaEnlazada list2){
        this.size = this.size + list2.size();
        //Si esta lista esta vacia, la union es la lista 2
        if(empty()){
            this.head = list2.getHead();
            this.tail = list2.getTail();
        }else{
            // obtenemos la cola de lista 1 y que esta apunte a la cabeza de lista 2
            this.tail.setNext(list2.getHead());
            // Verificamos que la lista que vamos a unir no esté vacía
            if(list2.getHead()!=null){
                // la cola de lista 1 ahora va a hacer la de lista 2
                this.tail = list2.getTail();
            }

        }

        return this;

    }


    public boolean empty(){
        return this.head == null;
    }


    public void pushFront(T el){
        Node n = new Node(el);
        n.setNext(this.head);
        this.head = n;
        if(this.tail == null){
            this.tail = head;
        }
        this.size += 1;

    }

    public void pushBack( T el){

        // CON TAIL (Constante)
        Node n = new Node(el);
        if(empty()){
            this.head = n;
            this.tail = this.head;
        }else{
            this.tail.setNext(n);
            this.tail = n;
        }
        this.size += 1;
    }

    public T popBack(){

        // CON TAIL (lineal)
        T cad = null;
        if(!empty()){
            if(this.head == this.tail){
                this.head = null;
                this.tail = null;
            }else{
                Node aux = this.head;
                while(aux.getNext() != this.tail){
                    aux = aux.getNext();
                }
                cad = (T) aux.getNext().getData();
                aux.setNext(null);
                this.tail = aux;
            }
            this.size -= 1;
        }
        return cad;
    }

    public T popFront(){
        T cad = null;
        if(!empty()){
            cad = (T) this.head.getData();
            this.head = this.head.getNext();
            if(this.head == null){
                this.tail = null;
            }
            this.size -= 1;
        }

        return cad;
    }

    // Hacer pop en una posicion específica
    public T getAt(int index){
        T res = null;
        if(!empty() && index < this.size){
            int i = 0;
            Node aux = this.head;
            while(i < index){
                aux = aux.getNext();
                i+=1;
            }
            res = (T) aux.getData();
        }
        return res;
    }

    //Nos devuelve la "posicion" en la que el elemento buscado esta
    // Si el elemento no está, entonces i = size();
    /* Hay que porgramar un compare para poder hacer ese != dentro del while
    public int buscar(int element){
        int i= 0;
        Node n = this.head;
        while(n!=null && n.getData() != element){
            i ++;
            n = n.getNext();
        }

        return i;
    }*/

    public void StringTo(){
        String cad = "[";
        if(empty()){
            cad += " ]";
        }else{
            Node n = this.head;
            while(n.getNext() != null){
                cad += n.getData() + " ";
                n = n.getNext();
            }
            cad += n.getData() + "]";
        }
        System.out.println(cad);
    }

}


