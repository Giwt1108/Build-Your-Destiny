package data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import estructuras.ListaEnlazada;

public class AtaqueDistancia extends Ataque{

    private int llamados;
    private float count;
    private ListaEnlazada<TextButton> botones;
    private ListaEnlazada<String> frases;

    //METODOS
    @Override
    public TextButton ataqueDistancia(boolean pressedButton, boolean pressedScreen, Jugador jugador,boolean primero, float delta){
        TextButton button = null;
        if(primero){
            this.llamados = (this.llamados%this.frases.size())+1;
            System.out.println("Entro en: " + this.llamados);
            llenarBotones();
            setActivo(true);
            setButtonActivo(true);
            button = botones.popFront();
            return button;
        }
        performanceButton(pressedButton,pressedScreen,jugador,delta);
        //Si dio el golpe y aun quedan botones, que mande otro boton
        if(!isButtonActivo() &&!this.botones.empty() && isActivo()){
            button = botones.popFront();
        }

        //Si se acabo el ataque da un boton nulo pero si continua da el siguiente boton
        return button;
    }

    @Override
    public void ataqueCorto(Jugador jugador, Enemigo enemigo) {}

    @Override
    //Lo usamos para saber si el usuario clickeo el boton (dio el golpe), o si clikeo en otra parte o se acabo el tiempo (fallo el golpe)
    public void performanceButton(boolean pressedButton, boolean pressedScreen, Jugador jugador, float delta) {
        if (pressedScreen) {
            if (pressedButton) {
                System.out.println("tocado");
                golpeaCombo(jugador);
                setActivo(true);

            } else {
                fallaCombo(jugador);
                System.out.println("Equivocado");
                setActivo(false);
                this.botones.clean();
            }
            this.count = 0;
            setButtonActivo(false);

        } else if (this.count > 3) {
            System.out.println("Se acabo el tiempo");
            fallaCombo(jugador);
            this.count = 0;
            setActivo(false);
            setButtonActivo(false);
            this.botones.clean();
        }
        this.count += delta;

    }


    //Al fallar la renga le baja la vida
    public void fallaCombo(Jugador jugador){
        float salud = jugador.getHabilidad().getSalud();
        salud -= 0.1;
        jugador.getHabilidad().setSalud(salud);
    }

    //Al asestar el golpe le aumenta el ataque en 0.1
    public void golpeaCombo(Jugador jugador){
        float ataque = jugador.getHabilidad().getAtaque();
        jugador.getHabilidad().setAtaque(ataque += 0.1);
    }

    public TextButton crearButton(){
        int n = MathUtils.random(0, this.frases.size()-1);
        String label = this.frases.getAt(n);
        ButtonAtack buttonAtack =  new ButtonAtack(label);
        TextButton button = buttonAtack.getButton();
        int widthButton = (int) button.getWidth();
        int heightButton = (int) button.getHeight();
        int posX = MathUtils.random(0, Gdx.graphics.getWidth() - widthButton);
        int posY = MathUtils.random(0, Gdx.graphics.getHeight() - heightButton);
        button.setX(posX);
        button.setY(posY);
        return button;
    }

    //LLenamos la lista con los botones a presionar, el entero le dira que habilidad llamo el ataque
    public void llenarBotones(){
        int count = 0; //es el contador de hasta donde hay que llenarlo
        while(count < this.llamados){
            TextButton boton = crearButton();
            botones.pushBack(boton);
            count ++;
        }
    }


    //GETTERS AND SETTERS
    public int getLlamados() {
        return llamados;
    }

    public void setLlamados(int llamados) {
        this.llamados = llamados;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }


    public ListaEnlazada<TextButton> getBotones() {
        return botones;
    }

    public void setBotones(ListaEnlazada<TextButton> botones) {
        this.botones = botones;
    }

    public ListaEnlazada<String> getFrases() {
        return frases;
    }

    public void setFrases(ListaEnlazada<String> frases) {
        this.frases = frases;
    }

    //CONSTRUCTORES
    public AtaqueDistancia(){
        this.llamados = 0;
        this.count = 0;
        this.botones =  new ListaEnlazada();
        this.frases = new ListaEnlazada();
    }
}
