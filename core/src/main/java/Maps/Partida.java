package Maps;

public class Partida implements Comparable<Partida> {

    public int id;
    private PathMatrix pathMatrix;
    private PathTree pathTree;

    public Partida(int id){
        this.id = id;
        this.pathMatrix = new PathMatrix<>(90,90);
        this.pathTree = new PathTree();
    }

    @Override
    public int compareTo(Partida obj){
        if(this.id > obj.id){
            return 1;
        }else if(this.id < obj.id){
            return -1;
        }else {
            return 0;
        }
    }
    @Override
    public String toString(){
        String cad = "" + this.id;
        return cad;
    }
}
