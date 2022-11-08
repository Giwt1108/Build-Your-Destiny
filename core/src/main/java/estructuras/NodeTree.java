package estructuras;


public class NodeTree<T extends Comparable<T>> {

    private T key;
    private int height;
    private NodeTree right;
    private NodeTree left;

    //CONSTRUCTORES

    public NodeTree(T key, int height){
        this.key = key;
        this.height = height;
        this.right = this.left = null;
    }

    public NodeTree(T key){
        this(key,1);
    }

    public NodeTree(){
        this(null,1);
    }

    //GETTERS AND SETTERS

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }



    public NodeTree getRight() {
        return right;
    }

    public void setRight(NodeTree right) {
        this.right = right;
    }

    public NodeTree getLeft() {
        return left;
    }

    public void setLeft(NodeTree left) {
        this.left = left;
    }


}