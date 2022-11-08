package estructuras;


public class AVL<T extends Comparable<T>> extends Tree<T> {

    //CONSTRUCTORES

    public AVL(NodeTree root){
        super(root);
    }

    public AVL(){
        this(null);
    }


    //METODOS
    public int balanceFactor(NodeTree n){
        if(n == null){
            return 0;

        }else{
            int hLeft = heightNode(n.getLeft());

            int hRight = heightNode( n.getRight() );

            return Math.abs( hLeft - hRight);
        }

    }

    public void updateHeight(NodeTree n){

        n.setHeight( 1 + Math.max( heightNode( n.getRight() ), heightNode( n.getLeft() ) )  );
    }

    public int heightNode(NodeTree n){
        if(n == null){
            return 0;
        }else{
            return n.getHeight();

        }
    }

    public NodeTree rotateRight(NodeTree n){
        NodeTree leftChild = n.getLeft();
        if(leftChild != null){
            n.setLeft(leftChild.getRight());
            leftChild.setRight(n);

            updateHeight(n );
            updateHeight( leftChild );
            System.out.println("left before lvl " + leftChild.getHeight() );
            System.out.println("left left: " + heightNode(leftChild.getLeft()) + " left right" + heightNode(leftChild.getRight()) );
            System.out.println("left after lvl " + leftChild.getHeight() );
        }

        return leftChild;
    }


    public NodeTree rotateLeft(NodeTree n){
        NodeTree rightChild = n.getRight();
        if(rightChild != null){
            n.setRight(rightChild.getLeft());
            rightChild.setLeft(n);
            updateHeight( n );
            updateHeight( rightChild );

        }

        return rightChild;

    }


    public NodeTree rebalanceo(NodeTree n){
        NodeTree right = n.getRight();
        NodeTree left = n.getLeft();
        NodeTree root = n;

        if( balanceFactor(n)> 1 ){ // si tenemos que rebalancear
            if( heightNode(right) > heightNode(left) ){ // SI el derecho es el mas pesado
                if( heightNode(  right.getLeft() ) > heightNode( right.getRight())  ){ // si es caso d+i
                    NodeTree x = rotateRight(right); //rotamos primero a derecha
                    n.setRight(x);
                }

                root = rotateLeft(n); //luego rotamos a izquierda

            }else{ //si el izquierdo es mas pesado (No van a hacer iguales pq, sino, el factor de balanceo habria sido 0)
                if(heightNode( left.getRight() )  >  heightNode( left.getLeft() ) ){ // si es caso i +d
                    NodeTree x = rotateLeft(left);
                    n.setLeft(x);
                }

                root = rotateRight(n);

            }

        }
        else{
            updateHeight(n);

        }


        return root;
    }



    public NodeTree insertAVL(NodeTree root,T key){
        if(root == null){
            return new NodeTree(key);
        }
        else {
            if( key.compareTo( (T) root.getKey() ) <0){
                root.setLeft(  insertAVL(root.getLeft(),  key)  );
            }
            else if(key.compareTo( (T) root.getKey() ) >0){
                root.setRight(  insertAVL(root.getRight(), key)  );

            }
            return rebalanceo(root);
        }

    }

    @Override
    public void insert(T key){
        this.setRoot( insertAVL(this.getRoot(), key)  );


    }

    public NodeTree deleteAVL(NodeTree root, T key){
        if(root!=null){
            if( key.compareTo( (T) root.getKey() ) < 0){ // buscamos el nodo a leiminar
                root.setLeft( deleteAVL(root.getLeft(), key)  );
            }else if( key.compareTo( (T) root.getKey() ) > 0){ // lo seguimos buscando
                root.setRight( deleteAVL(root.getRight(), key));
            }else{ //si lo encontramos
                if(root.getLeft() == null && root.getRight() == null){
                    return null;
                }else if( root.getLeft() == null){
                    root = root.getRight();
                }else if( root.getRight() == null ){
                    root = root.getLeft();
                }else{
                    NodeTree min = minNode(root.getRight());
                    root.setKey( min.getKey() );
                    root.setRight( deleteAVL( root.getRight(), (T) min.getKey() ) );
                }

            }

            root = rebalanceo(root);


        }
        return root;

    }


    @Override
    public void remove(T key){
        this.setRoot( deleteAVL(this.getRoot(), key)  );
    }
}

