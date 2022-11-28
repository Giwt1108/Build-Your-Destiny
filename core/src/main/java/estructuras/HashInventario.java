package estructuras;

import java.util.Arrays;

public class HashInventario {
    //Constructor
    String [] arreglo;
    int tamanio, contador;
    public HashInventario (int tam) {
        tamanio=tam;
        arreglo=new String[tam];
        Arrays. fill(arreglo,"-1");
    }

    //Dar los KEYS o INDICES a los elementos
    public void funcionHash(String[] cadenaArreglo, String[] arreglo) {
        int i;
        for (i = 0; i < cadenaArreglo.length; i++) {
            String elemento = cadenaArreglo[i];
            int indiceArreglo = Integer.parseInt (elemento) % 7;
            System. out.println("El Indice es " + indiceArreglo +
                    " Para el Elemento o Valor "+ elemento);

            //Tratando las Colisones
            while (arreglo[indiceArreglo] !="-1") {

                indiceArreglo++;

                System. out.println("Ocurrió una Colision en el Indice "+
                        (indiceArreglo-1) + " Cambiar al indice "+
                        indiceArreglo) ;

                indiceArreglo%=tamanio;
            }
            arreglo[indiceArreglo]=elemento;
        }
    }
    //mostrar datos en la tabla
    public void mostrar(){
        int incremento = 0, i, j;
        for (i = 0; i <1; i++) {
            incremento += 8;

            for (j = 0; j < 71;j++){
                System.out.print("-");
            }
            System.out.println();

            for (j = incremento -8; j < incremento; j++) {
                System.out.format("| %3s "+ " ", j);
            }
            System.out.println("|");
            for (int n= 0; n< 71; n++) {
                System.out.print("-");
            }
            System.out.println();

            for (j = incremento - 8; j < incremento; j++) {

                if (arreglo[j].equals("-1")) {
                    System. out.print("|      ");
                } else {
                    System. out.print(String. format("| %3s" + " ", arreglo[j]));
                }
            }
            System.out.println("|");
            for (j = 0; j < 71; j++){
                System.out.print("-");
            }
            System.out.println();
        }
    }

    //Método para buscar y obtener KEY
    public String buscarKey(String elemento) {
        int indiceArreglo = Integer.parseInt(elemento) % 7;
        int contador = 0;
        while (arreglo[indiceArreglo] != "-1") {
            if (arreglo[indiceArreglo] == elemento) {
                System.out.println("El elemento " + elemento +
                        " fué encontrado en el índice " + indiceArreglo);
                return arreglo[indiceArreglo];
            }else{
                System.out.println("El elemento" + elemento + " fue encontrado en el índice" + indiceArreglo);
            }
            indiceArreglo++;
            indiceArreglo %= tamanio;
            contador++;
            if (contador > 7) {
                break;
            }
        }
        return null;
    }
}


