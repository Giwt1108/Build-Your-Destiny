package data;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import estructuras.ListaEnlazada;

public abstract class Ataque {
    //Animacion
    private Estado estado;
    private boolean activo; //o bien indica que el ataque esta activo
    private boolean buttonActivo;

    //METODODS ATAQUES
    public abstract void setFrases(ListaEnlazada<String> frases);
    public abstract void performanceButton(boolean pressedButton, boolean pressedScreen, Jugador jugador, float delta);
    public abstract TextButton ataqueDistancia(boolean pressedButton, boolean pressedScreen, Jugador jugador, boolean primero, float delta);
    public abstract void ataqueCorto(Jugador jugador, Enemigo enemigo);

    //ATAQUES ARENGA/EGO



    //CONSTRUCTOR
    public Ataque(){
        this.estado = null;
        this.activo = false;
        this.buttonActivo = false;
    }

    //GETTER AND SETTER
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isButtonActivo() {
        return buttonActivo;
    }

    public void setButtonActivo(boolean buttonActivo) {
        this.buttonActivo = buttonActivo;
    }
}
