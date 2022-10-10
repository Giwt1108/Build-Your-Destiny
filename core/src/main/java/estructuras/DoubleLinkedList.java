/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

/**
 *
 * @author Sebastian
 */
public class DoubleLinkedList<T> {
    private DoubleNode<T> head;
    private DoubleNode<T> tail;
    private long pos = -1;
    
    public DoubleLinkedList(){
        this.head = null;
        this.tail = null;
    }
    
    public boolean isEmpty(){
        return this.head == null;
    }
    
    public void pushFront(T key){
        if(this.head == null){
            this.head = new DoubleNode<T>(key);
            this.tail=this.head;   
        }else{
            DoubleNode<T> newDoubleNode = new DoubleNode<T>(key);
            newDoubleNode.setNext(this.head);
            newDoubleNode.getNext().setPrev(newDoubleNode);
            this.head = newDoubleNode;
        }       
    }
    
    public T topFront(){
        if(!this.isEmpty()){
            return this.head.getData();
        }
        return null;
    }
    
    public T popFront(){
        if(!this.isEmpty()){
            T data = this.head.getData();
            if(this.head == this.tail){
                this.tail = null;
            }
            if(this.head.getNext() !=null){
                this.head = this.head.getNext();
                this.head.setPrev(null);
            }else{
                this.head = null;
                this.tail = this.head;
            }
            return data;
        }else{
            System.out.println("Empty Linked List");
            return null;
        }   
    }
    
    public void pushBack(T key){
        if(this.tail == null){
            this.tail = new DoubleNode<T>(key);
            this.head = this.tail;
        }else{
            this.tail.setNext(new DoubleNode<T>(key));
            this.tail.getNext().setPrev(this.tail);
            this.tail = this.tail.getNext();
        }
    }
    
    public T topBack(){
        if(!this.isEmpty()){
            return this.tail.getData();
        }
        else{
            System.out.println("Empty Linked List");
            return null;
        }
    }
    
    public T popBack(){
        if(!this.isEmpty()){
            T data = this.tail.getData();
            if(this.head!=this.tail){
                this.tail=this.tail.getPrev();
                this.tail.setNext(null);
            }else{
                this.head = null;
                this.tail = null;
            }
            return data;
        }else{
            System.out.println("Empty Linked List");
            return null;
        }
    }
    
    public boolean find(T key){
        DoubleNode<T> curr = this.head;
        int counter = 0;
        while(curr!=null){
            if(curr.getData() == key){
                this.pos = counter;
                return true;
            }
            curr = curr.getNext();
            counter++;
        }
        return false;
    }
    
    public boolean erase(T key){
        if(this.find(key)){
            DoubleNode<T> curr=this.head;
            for(int i = 0;i<pos-1;i++){
                curr = curr.getNext();
            }
            curr.setNext(curr.getNext().getNext());
            return true;
        }
        return false;
    }
    
    public void addBefore(DoubleNode<T> Doublenode, T key){
        if(Doublenode==this.head){
            this.pushFront(key);
        }else{
            DoubleNode<T> newDoubleNode = new DoubleNode<T>(key);
            newDoubleNode.setPrev(Doublenode.getPrev());
            newDoubleNode.setNext(Doublenode);
            newDoubleNode.getPrev().setNext(newDoubleNode);
            newDoubleNode.getNext().setPrev(newDoubleNode);
        }
    }
    
    public void addAfter(DoubleNode<T> Doublenode, T key){
        DoubleNode<T> newDoubleNode =  new DoubleNode<T>(key);

        newDoubleNode.setNext(Doublenode.getNext());
        Doublenode.setNext(newDoubleNode);
    }
    
    public T getKey(Integer index){
        return null;
    }
    
    public T getNodeKey(DoubleNode node){
        return (T) node.getData();
    }
    
    public void popNode(DoubleNode node){
        if(node.getPrev()!=null){
            node.getPrev().setNext(node.getNext());
        }        
        if(node.getNext()!=null){
            node.getNext().setPrev(node.getPrev());
        }
        if(node.getNext()==null && node.getPrev()==null){
            head = null;
            tail = null;
        }
    }
    
    public void pushBackNode(DoubleNode node){
        if(this.tail == null){
            this.tail = node;
            this.head = this.tail;
        }else{
            this.tail.setNext(node);
            this.tail.getNext().setPrev(this.tail);
            this.tail = this.tail.getNext();
        }
    }
    
    public void printList(){
        while(!isEmpty()){
            System.out.println(this.popBack());
        }
    }
    
    public DoubleNode<T> getHead() {
        return head;
    }
}
