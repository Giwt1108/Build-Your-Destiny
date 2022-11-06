/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import estructuras.Nodes;
/**
 *
 * @author Sebastian
 * @param <T>
 */
public class PathTree<T> {
    public Nodes<T> root;
    public Nodes<T> current;
    final public int maxNumOfNodes = 4;
    
    public void insert(T data){
        if(this.root!= null){
            this.current.nodes.add(new Nodes(data));
            this.current.nodes.get(this.current.nodes.size()-1).setParent(this.current);
            this.current = (Nodes) this.current.nodes.get(this.current.nodes.size()-1);
        }else{
            this.root = new Nodes(data);
            this.current = this.root;
        }
    }
}
