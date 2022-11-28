package Entities;

import estructuras.DoubleLinkedList;
import estructuras.DinamicArray;
import estructuras.HashInventario;


public class Crafteo {
    HashInventario inventario;
    DinamicArray coleccionables;
    String[] crafteables;

    public void setColeccionables(DinamicArray coleccionables) {
        this.coleccionables = coleccionables;
    }

    public void setCrafteables(String[] crafteables) {
        this.crafteables = crafteables;
    }

    public DoubleLinkedList seleccion(String[] elementos) {
        DoubleLinkedList numeros = new DoubleLinkedList<Object>();
        int indice = -1;
        boolean valido = true;
        if (elementos[0] == coleccionables.get(0) && elementos[1] == coleccionables.get(1)) {
            indice = 0;
            numeros.pushBack(coleccionables.get(0));
            numeros.pushBack(coleccionables.get(1));
        } else {
            valido = false;
        }
        if (elementos[0] == coleccionables.get(2) && elementos[1] == coleccionables.get(3)) {
            indice = 1;
            numeros.pushBack(coleccionables.get(2));
            numeros.pushBack(coleccionables.get(3));
        } else {
            valido = false;
        }
        if (elementos[0] == coleccionables.get(4) && elementos[1] == coleccionables.get(5)) {
            indice = 2;
            numeros.pushBack(coleccionables.get(4));
            numeros.pushBack(coleccionables.get(5));
        } else {
            valido = false;
        }
        if (elementos[0] == coleccionables.get(6) && elementos[1] == coleccionables.get(7)) {
            indice = 3;
            numeros.pushBack(coleccionables.get(6));
            numeros.pushBack(coleccionables.get(7));
        } else {
            valido = false;
        }
        if (elementos[0] == coleccionables.get(6) && elementos[8] == coleccionables.get(9)) {
            indice = 4;
            numeros.pushBack(coleccionables.get(8));
            numeros.pushBack(coleccionables.get(9));
        } else {
            valido = false;
        }
        numeros.pushBack(indice);
        numeros.pushBack(valido);
        if (valido == false) {
            return null;
        } else {
            return numeros;
        }
    }

    public DinamicArray craft(DoubleLinkedList numeros) {
        int indice = (int) numeros.popFront();
        String obj1 = numeros.popFront().toString();
        String obj2 = numeros.popFront().toString();
        boolean valido = (boolean) numeros.popFront();
        if (valido == true) {
            for (int i = 0; i < coleccionables.size() - 1; i++) {
                if (coleccionables.get(i) == obj1) {
                    coleccionables.removes(i);
                    coleccionables.removes(i + 1);
                    coleccionables.pushBack(crafteables[indice]);
                }
            }
        } else {
            System.out.println("No existen combinaciones posibles entre los coleccionables./n Intenta de nuevo más tarde");
        }
        return coleccionables;
    }
}
