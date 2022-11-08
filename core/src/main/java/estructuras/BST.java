package estructuras;


public class BST<T extends Comparable<T>> extends Tree<T>{


    //CONSTRUCTORES
    public BST(NodeTree root){
        super(root);
    }

    public BST() {
        this(null);
    }

    //METODOS


    //INSERT
    private NodeTree insertBST(NodeTree root, T key) {
        if (root == null) {
            return new NodeTree(key);
        } else {
            if (root.getKey().compareTo(key) > 0) {
                root.setLeft(insertBST(root.getLeft(), key));
            } else if (root.getKey().compareTo(key) < 0) {
                root.setRight(insertBST(root.getRight(), key));
            }
            return root;
        }
    }

    @Override
    public void insert(T key) {
        this.setRoot( insertBST(this.getRoot(),key )  );
    }

    /*En la recursion, vamos asignando de nuevo el nodo a un lado izquierdo o derecho
    del arbol pq es como si fueramos buscando, eliminando y asignando todo al mismo tiempo */
    private NodeTree deleteBST(T i, NodeTree root) {
        if (root != null) {
            if (root.getKey().compareTo(i) > 0 ) { //lo buscamos
                root.setLeft(deleteBST(i, root.getLeft()));
            } else if (root.getKey().compareTo(i) < 0) { // lo buscamos
                root.setRight(deleteBST(i, root.getRight()));
            } else { // si lo encontramos
                if (root.getLeft() == null && root.getRight() == null) {
                    root = null;
                } else if (root.getLeft() == null) {
                    root = root.getRight();
                } else if (root.getRight() == null) {
                    root = root.getLeft();

                } else {
                    NodeTree n = minNode(root.getRight());
                    root.setKey(n.getKey());
                    root.setRight(deleteBST((T) n.getKey(), root.getRight()));
                }

            }

        }
        return root;

    }

    @Override
    public void remove(T i) {
        this.setRoot( deleteBST(i,this.getRoot() )  );

    }



}

