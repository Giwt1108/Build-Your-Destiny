/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import estructuras.Nodes;
import estructuras.DoubleLinkedList;
import Maps.PathTree;

import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Sebastian
 */
public class PathMatrix<T> {
    public int rows;
    public int cols;
    public List<List<Nodes<Integer[]>>> matrix;

    
    public PathMatrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.matrix = buildMatrix();
    }
    
    private List<List<Nodes<Integer[]>>> buildMatrix(){
        List<List<Nodes<Integer[]>>> matrix = initMatrix();
        for(int i = 0; i<this.rows; i++){
            for(int j = 0; j<this.cols;j++){
                try{
                    matrix.get(i).get(j).nodes.add(matrix.get(i-1).get(j));
                }catch(Exception e){}
                try{
                    matrix.get(i).get(j).nodes.add(matrix.get(i).get(j+1));
                }catch(Exception e){}
                try{
                    matrix.get(i).get(j).nodes.add(matrix.get(i+1).get(j));
                }catch(Exception e){}
                try{
                    matrix.get(i).get(j).nodes.add(matrix.get(i).get(j-1));
                }catch(Exception e){}
            }
        }
        return matrix;
    }
    
    private List<List<Nodes<Integer[]>>> initMatrix(){
        List<List<Nodes<Integer[]>>> matrix = new ArrayList<List<Nodes<Integer[]>>>();
        for(int i = 0; i<this.rows; i++){
            List<Nodes<Integer[]>> cols = new ArrayList<Nodes<Integer[]>>();
            for(int j = 0; j<this.cols;j++){
                cols.add(new Nodes<Integer[]>(new Integer[] {i,j}));
            }
            matrix.add(cols);
        }
        return matrix;
    }
    
    public DoubleLinkedList<Integer[]> findPath(){
        PathTree<Nodes<Integer[]>> tree = new PathTree<Nodes<Integer[]>>();
        DoubleLinkedList<Integer[]> path = new DoubleLinkedList<Integer[]>();
        
        int row = ThreadLocalRandom.current().nextInt(0, this.rows),
                col = ThreadLocalRandom.current().nextInt(0, this.cols);
        
        //System.out.println(row+","+col);
        
        tree.insert(this.matrix.get(row).get(col));
        path.pushBack(tree.current.data.data);
        
        Nodes<Nodes<Integer[]>> current = tree.current;
        int height = 1;
        
        while(height!=this.rows*this.cols){
            List<Nodes<Integer[]>> accessibleNodes = current.data.getAccesibleNodes(path);
            for(int i =0; i<current.nodes.size();i++){
                try{
                    accessibleNodes.remove(current.nodes.get(i).data);
                }catch(Exception e){}
            }
            if(!accessibleNodes.isEmpty()){
                int num = ThreadLocalRandom.current().nextInt(0, accessibleNodes.size());
                Nodes<Integer[]> node = accessibleNodes.get(num);
                tree.insert(node);
                path.pushBack(tree.current.data.data);
                //System.out.println("["+node.data[0]+","+node.data[1]+"] - added");
                height++;
            }else{
                //System.out.println("["+tree.current.data.data[0]+","+tree.current.data.data[1]+"] - deleted");
                tree.current = tree.current.parent;
                path.popBack();
                height--;
            }
            current = tree.current;
            if(current == null){
                row = ThreadLocalRandom.current().nextInt(0, this.rows);
                col = ThreadLocalRandom.current().nextInt(0, this.cols);
        
                //System.out.println(row+","+col);
                tree.root = tree.current;
                tree.insert(this.matrix.get(row).get(col));
                path.pushBack(tree.current.data.data);
                
                current = tree.current;
                height = 1;
            }
        }
        return path;
    }
    
    public void printMatrix(){
        for(int i = 0; i<this.rows; i++){
            for(int j=0;j<this.cols;j++){
                Nodes<Integer[]> node = matrix.get(i).get(j);
                System.out.print("["+node.data[0]+","+node.data[1]+"]"+"---");
                for(int k = 0;k<4;k++){
                    try{
                        Nodes<Integer[]> nodeIn = node.nodes.get(k);
                        System.out.print("["+nodeIn.data[0]+","+nodeIn.data[1]+"]");
                    }catch(Exception e){}
                }
                System.out.println();
            }
        }
    }


}
