package Buscador;


public class Resultado <T> implements Comparable<Resultado> {
    private int coincidencia;
    private T objeto;

    //CONSTRUCTORES
    public Resultado(int coincidencia, T objeto ){
        this.coincidencia = coincidencia;
        this.objeto = objeto;
    }

    public Resultado(T objeto){
        this(-1, objeto);
    }

    public Resultado(int coincidencia){
        this(coincidencia, null);
    }

    public Resultado(){
        this(-1,null);
    }





    //METODOS DE LA INTERFAZ
    @Override
    public int compareTo(Resultado data) {
        int res=0;
        if(this.coincidencia < data.getCoincidencia()){
            res = -1;
        }else if(this.coincidencia > data.getCoincidencia()){
            res = 1;
        }

        //throw new UnsupportedOperationException("Not supported yet."); //
        return res;
    }


    //GETTERS NAD SETTERS

    @Override
    public String toString(){
        String cad = "relevancia: " + this.coincidencia + " obj: " + this.objeto.toString();
        return cad;
    }

    public int getCoincidencia() {
        return coincidencia;
    }

    public void setCoincidencia(int coincidencia) {
        this.coincidencia = coincidencia;
    }

    public T getObjeto() {
        return objeto;
    }

    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }



}

