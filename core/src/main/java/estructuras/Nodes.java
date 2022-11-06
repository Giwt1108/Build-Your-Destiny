/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sebastian
 */
public class Nodes<T> {
    public T data;
    public List<Nodes> nodes;
    public Nodes parent;
    
    public Nodes(T data){
        this.data = data;
        this.nodes = new ArrayList<Nodes>(4);
        this.parent = null;
    }
    
    public List<Nodes<T>> getAccesibleNodes(DoubleLinkedList<T> path){
        List<Nodes<T>> accessNodes = new ArrayList<Nodes<T>>();
        for(int i = 0; i<nodes.size(); i++){
            if(this.nodes.get(i)!=null && !path.find((T) this.nodes.get(i).data)){
                accessNodes.add(this.nodes.get(i));
            }
        }
        return accessNodes;
    }
    
    public void setParent(Nodes newParent){
        this.parent = newParent;
    }
    
}
