package Entities;

public abstract class Estado {
    private int efecto;
    private boolean activo;
    private long tiempoAccion;
    private long countdown;

    //CONSTRUCTOR
    public Estado(){
        this(0,false,0,0);
    }

    public Estado(int efecto, boolean activo, long tiempoAccion, long countdown) {
        this.efecto = efecto;
        this.activo = activo;
        this.tiempoAccion = tiempoAccion;
        this.countdown = countdown;
    }

    //Metodos

    public abstract void performance(Jugador jugador, Enemigo enemigo);

    //GETTERS AND SETTERS
    public int getEfecto() {
        return efecto;
    }

    public void setEfecto(int efecto) {
        this.efecto = efecto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public long getTiempoAccion() {
        return tiempoAccion;
    }

    public void setTiempoAccion(long tiempoAccion) {
        this.tiempoAccion = tiempoAccion;
    }

    public long getCountdown() {
        return countdown;
    }

    public void setCountdown(long countdown) {
        this.countdown = countdown;
    }
}
