package estructuras;


public abstract class Tree <T extends Comparable<T>> {

    private NodeTree root;

    //CONSTRUCTORES

    public Tree(NodeTree root){
        this.root = root;
    }


    //METODOS


    //INSERT
    public abstract void insert(T key);

    //REMOVE
    public abstract void remove(T key);

    //FIND
    //devuelve la posicion del nodo o donde deberia estar el nodo
    public NodeTree find(T i, NodeTree n) {
        NodeTree find = n;
        if (n.getKey().compareTo(i) > 0 ) {
            if (n.getLeft() != null) {
                find = find(i, n.getLeft());
            }
        } else if ( n.getKey().compareTo(i) < 0) {
            if (n.getRight() != null) {
                find = find(i, n.getRight());
            }
        }

        return find;
    }

    public NodeTree findNode(T i) {
        return find(i, this.root);
    }

    public boolean isNode(T i) {
        NodeTree n = findNode(i);
        return n.getKey().compareTo(i) == 0;
    }

    //NEXT

    public void next(T i) {
        NodeTree next = nextNode(i, this.root, this.root);
        if (next != null) {
            System.out.println("next: " + next.getKey());
        } else {
            System.out.println("No hay next");
        }
    }

    public NodeTree nextNode(T i, NodeTree n, NodeTree parent) {

        /*buscamos el nodo*/
        if (n.getKey().compareTo(i) > 0) {//lo buscamos en el lado izquierdo

            if (n.getLeft() != null) { // como en find
                NodeTree root = nextNode(i, n.getLeft(), n);
                if (root == null) {
                    if (parent.getKey().compareTo(i) > 0) {
                        root = parent;
                    } else {
                        return null;
                    }
                }
                return root;
                //return nextNode(i, n.getLeft(), n);
            } else { //Implicitamente tambien estamos buscando el minimo
                /*si el izq es nulo, entonces el nodo de key i no esta en el arbol
            luego, al tratarse del hijo izquierdo, el siguiente a este es el nodo mas pequeño cercano*/
                return n;
            }

        } else { //Si lo encontramos o esta en el derecho
            if (n.getRight() != null) {
                NodeTree root = nextNode(i, n.getRight(), n);
                if (root == null) {
                    if (parent.getKey().compareTo(i) > 0) {
                        root = parent;
                    }

                }
                return root;
                //return nextNode(i, n.getRight(), n);
            } else {
                if (parent.getKey().compareTo(i) > 0) {
                    return parent;
                } else {
                    return null;
                }
            }
        }

    }

    //RANGEF
    //Obviamente x > y
    public PilaEnlazada rangeSearch(T x, T y) {
        PilaEnlazada<T> range = new PilaEnlazada();
        NodeTree n = nextNode(x, this.root, this.root);
        boolean bandera = n != null;

        if (isNode(x)) {
            range.push(x);
        }
        if (bandera) {
            if (n.getKey().compareTo(y) <= 0 ) {
                range.push((T) n.getKey());
            }
        }
        while (bandera) {
            n = nextNode((T) n.getKey(), this.root, this.root);
            if (n == null) {
                bandera = false;
            } else {
                if (n.getKey().compareTo(y) > 0) {
                    bandera = false;
                } else {
                    range.push( (T) n.getKey());
                }
            }

        }
        return range;
    }

    public void rangePrint(T x, T y) {
        String cad = "[ ";
        PilaEnlazada<T> range = rangeSearch(x, y);
        while (!range.empty()) {
            cad += range.pop() + " ";
        }
        System.out.println(cad + " ]");

    }

    //MAX

    public NodeTree maxNode(NodeTree p) {
        NodeTree n = p;
        if (p != null) {
            while (n.getRight() != null) {
                n = n.getRight();
            }
        }

        return n;
    }

    //MIN

    public NodeTree minNode(NodeTree p) {
        NodeTree n = p;
        if (p != null) {
            while (n.getLeft() != null) {
                n = n.getLeft();
            }
        }
        return n;
    }

    //DFS
    public void preOrder(NodeTree p) {
        if (p != null) {

            System.out.println(p.getKey());

            if (p.getLeft() != null) {
                preOrder(p.getLeft());
            }

            if (p.getRight() != null) {
                preOrder(p.getRight());
            }
        }
    }

    public void inOrder(NodeTree p) {
        if (p != null) {
            //System.out.println("p: " + p.getKey());
            if (p.getLeft() != null) {
                inOrder(p.getLeft());
            }

            System.out.println(p.getKey());

            if (p.getRight() != null) {
                inOrder(p.getRight());
            }
        }

    }

    public void postOrder(NodeTree p) {
        if (p != null) {

            if (p.getLeft() != null) {
                postOrder(p.getLeft());
            }

            if (p.getRight() != null) {
                postOrder(p.getRight());
            }

            System.out.println(p.getKey());
        }

    }

    //BFS
    public void bfs(NodeTree p) {
        ColaEnlazada<NodeTree> cola = new ColaEnlazada<>();
        if (this.root != null) {
            cola.Encolar(this.root);
        }

        while (!cola.empty()) {
            NodeTree n = cola.Desencolar();
            if (n.getLeft() != null) {
                cola.Encolar(n.getLeft());
            }
            if (n.getRight() != null) {
                cola.Encolar(n.getRight());
            }
            System.out.println(n.getKey() + " lvl: " + n.getHeight());

        }

    }


    //GETTERS AND SETTERS
    public NodeTree getRoot() {
        return root;
    }


    public void setRoot(NodeTree root) {
        this.root = root;
    }

}

