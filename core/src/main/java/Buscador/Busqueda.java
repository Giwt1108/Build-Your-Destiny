package Buscador;


import java.util.Scanner;

import Entities.Coleccionable;
import estructuras.Heaps;
import estructuras.ListaEnlazada;


public class Busqueda {
    private Heaps resultados;

    //CONSTRUCTORES
    public Busqueda(){
        this.resultados = new Heaps();

    }


    //METODOS

    //Nos dice si una subcadena está en una fraccion de una cadena,
    public boolean inPart(int ini, int fin, String sub, String cad){
        int j = 0;
        boolean bandera = true;

        while( bandera && ini <= fin){

            if(sub.charAt(j) != cad.charAt(ini)){
                bandera = false;
            }else{
                ini ++;
                j ++;
            }

        }

        return ini > fin;
    }

    //Nos dice si una cadena es subcadena de otra
    //Si si es subcadena, le settea una prioridad y la aniade al heap
    public boolean isSub(String sub, String cad, Resultado res){
        int i = 0;
        int nSub = sub.length();
        int nCad = cad.length();

        boolean bandera = false;

        while(!bandera && i<=nCad-nSub){
            bandera = inPart(i, i+nSub-1, sub, cad);
            i++;

        }
        //Si lo encontramos, seteamos la importancia
        if(bandera){
            //item de posicion donde esta la subcadena
            int priority = 50/i;

            //item de si la subcadena es de la longitud de la cadena
            if(nCad-nSub != 0){
                priority += 50/(nCad-nSub);
            }else{
                priority += 51;
            }

            res.setCoincidencia(priority);
            this.resultados.insert(res);
        }



        return bandera;
    }

    public void buscar(String sub, ListaEnlazada<Coleccionable> coles){
        this.resultados = new Heaps(); //Es necesario limpiar el heap antes de hacer otra busqueda
        for(int i = 0; i<coles.size(); i++){
            Resultado res = new Resultado( coles.getAt(i) );
            boolean x = isSub(sub, coles.getAt(i).getNombre(), res);
            System.out.println("esta en " + coles.getAt(i) + ": " + x);
        }

    }

    public void showResults(){
        int n = this.resultados.getArray().size();
        System.out.println("heaps size pre: " + n);
        System.out.print("[ ");
        for(int i=0; i<n; i ++){
            Resultado res = (Resultado) this.resultados.extractMax();
            System.out.print(res.toString() + ", ");
        }
        System.out.println(" ]");
        n = this.resultados.getArray().size();
        System.out.println("heaps size pos: " + n);
    }

    //GETTERS AND SETTERS

    public Heaps getResultados() {
        return resultados;
    }

    public void setResultados(Heaps resultados) {
        this.resultados = resultados;
    }




    /*
    public static void main(String[] args) {
        ListaEnlazada<Coleccionable> coles = new ListaEnlazada();
        coles.pushBack(new Coleccionable(0,0,"espada"));
        coles.pushBack(new Coleccionable(0,0,"moneda"));
        coles.pushBack(new Coleccionable(0,0,"alada"));
        coles.pushBack(new Coleccionable(0,0,"posion"));
        coles.pushBack(new Coleccionable(0,0,"municion"));

        Busqueda busq = new Busqueda();
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzaca la cadena a buscar. Cambio de linea para salir del programa: ");
        String input = sc.nextLine();
        while(!input.equals("\n")){
            busq.buscar(input, coles);
            busq.showResults();
            System.out.println("Introduzaca la cadena a buscar. Cambio de linea para salir del programa: ");
            input = sc.nextLine();

        }
        System.out.println("Terminado");

        //System.out.println(busq.isSub("cad", "subcadena", null));

    }*/


}
