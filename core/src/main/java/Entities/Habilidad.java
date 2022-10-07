package data;


public abstract class Habilidad {
    private float velocidad; //Debería ser 100? deberia ser final?
    private float estamina;
    private float alcance;
    private float suerte;
    private float velocidadAtaque;
    private float ataque;
    private float salud;
    private Habilidad habilidadRight ;
    private Habilidad habilidadLeft ;
    private Ataque ataqueDistancia;
    private Ataque ataqueCorto;


    //CONSTRUCTORS

    public Habilidad(){
        this(0,0,0,0,0,0,0);
    }


    public Habilidad(float velocidad, float estamina, float alcance, float suerte, float velocidadAtaque, float ataque, float salud) {
        this.velocidad = velocidad;
        this.estamina = estamina;
        this.alcance = alcance;
        this.suerte = suerte;
        this.velocidadAtaque = velocidadAtaque;
        this.ataque = ataque;
        this.salud = salud;
        this.habilidadRight = null;
        this.habilidadLeft = null;
        this.ataqueDistancia = new AtaqueDistancia();
        this.ataqueCorto = new AtaqueCorto();
    }




    //GETTERS NAD SETTERS

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public float getEstamina() {
        return estamina;
    }

    public void setEstamina(float estamina) {
        this.estamina = estamina;
    }

    public float getAlcance() {
        return alcance;
    }

    public void setAlcance(float alcance) {
        this.alcance = alcance;
    }

    public float getSuerte() {
        return suerte;
    }

    public void setSuerte(float suerte) {
        this.suerte = suerte;
    }

    public float getVelocidadAtaque() {
        return velocidadAtaque;
    }

    public void setVelocidadAtaque(float velocidadAtaque) {
        this.velocidadAtaque = velocidadAtaque;
    }

    public float getAtaque() {
        return ataque;
    }

    public void setAtaque(float ataque) {
        this.ataque = ataque;
    }

    public float getSalud() {
        return salud;
    }

    public void setSalud(float salud) {
        this.salud = salud;
    }

    public Habilidad getHabilidadRight() {
        return habilidadRight;
    }

    public void setHabilidadRight(Habilidad habilidadRight) {
        this.habilidadRight = habilidadRight;
    }

    public Habilidad getHabilidadLeft() {
        return habilidadLeft;
    }

    public void setHabilidadLeft(Habilidad habilidadLeft) {
        this.habilidadLeft = habilidadLeft;
    }

    public Ataque getAtaqueDistancia() {
        return ataqueDistancia;
    }

    public void setAtaqueDistancia(Ataque ataque) {
        this.ataqueDistancia = ataque;
    }

    public Ataque getAtaqueCorto() {
        return ataqueCorto;
    }

    public void setAtaqueCorto(Ataque ataqueCorto) {
        this.ataqueCorto = ataqueCorto;
    }
}

