package Entities;

import Entities.Enemies.Enemigo;
import Entities.Enemies.LinkedCroc;
import Entities.Enemies.QueueGusanin;
import Entities.Enemies.cainBoss;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import estructuras.ListaEnlazada;

public class AtaqueCorto extends Ataque{


    //Me dice si acerte el golpe o lo falle
    public boolean acertar(Jugador jugador, Enemigo enemigo){
        return true;
    }



    @Override
    public void ataqueCorto(Jugador jugador, Enemigo enemigo){
    }
    public boolean ataqueCorto(Jugador jugador, LinkedCroc enemigo) {
        float X=jugador.getSprite().getX();
        float Y= jugador.getSprite().getY();
         if(X<0){X=X*(-1);}
        if(Y<0){Y=Y*(-1);}
        double distance;
        double x=(double)X-enemigo.getSprite().getX();
        double y=(double)Y-enemigo.getSprite().getY();
        distance=(x*x)+(y*y);
        distance=Math.sqrt(distance);
        if(distance<20&& jugador.isAttacking() ){
            //SI acierta el golpe le baja vida al enemigo y le da un estado
            if(enemigo.canGetDamage()==true){
                float salud = enemigo.getSalud();
                salud -= 10;
                if(salud<=0){
                    enemigo.kill();
                    enemigo.removeChild();
                    return true;
                }
                enemigo.setSalud(salud);
            }
            
            Estado estado = getEstado(); //Tomamos el estado que este ataque genera
            //enemigo.setEstado(estado); //le damos este estado al enemigo
            //estado.performance(jugador, enemigo); //ejecutamos lo que hace el estado
        }
        return false;
    }
    
    public boolean ataqueCorto(Jugador jugador, QueueGusanin enemigo) {
        float X=jugador.getSprite().getX();
        float Y= jugador.getSprite().getY();
         if(X<0){X=X*(-1);}
        if(Y<0){Y=Y*(-1);}
        double distance;
        double x=(double)X-enemigo.getSprite().getX();
        double y=(double)Y-enemigo.getSprite().getY();
        distance=(x*x)+(y*y);
        distance=Math.sqrt(distance);
        if(distance<20&& jugador.isAttacking() ){
            //SI acierta el golpe le baja vida al enemigo y le da un estado
            if(enemigo.canGetDamage()==true){
                float salud = enemigo.getSalud();
                salud -= 10;
                if(salud<=0){
                    enemigo.kill();
                    return true;
                }
                enemigo.setSalud(salud);
            }
            
            Estado estado = getEstado(); //Tomamos el estado que este ataque genera
            //enemigo.setEstado(estado); //le damos este estado al enemigo
            //estado.performance(jugador, enemigo); //ejecutamos lo que hace el estado
        }
        return false;
    }
    
    
    public boolean ataqueCorto(Jugador jugador, cainBoss enemigo) {
        float X=jugador.getSprite().getX();
        float Y= jugador.getSprite().getY();
         if(X<0){X=X*(-1);}
        if(Y<0){Y=Y*(-1);}
        double distance;
        double x=(double)X-enemigo.getSprite().getX();
        double y=(double)Y-enemigo.getSprite().getY();
        distance=(x*x)+(y*y);
        distance=Math.sqrt(distance);
        if(distance<20&& jugador.isAttacking() ){
            //SI acierta el golpe le baja vida al enemigo y le da un estado
            float salud = enemigo.getSalud();
            salud -= 10;
            if(salud<=0){
                enemigo.kill();
                return true;
            }
            enemigo.setSalud(salud);
            
         
            Estado estado = getEstado(); //Tomamos el estado que este ataque genera
            //enemigo.setEstado(estado); //le damos este estado al enemigo
            //estado.performance(jugador, enemigo); //ejecutamos lo que hace el estado
        }
        return false;
    }

    @Override
    public void setFrases(ListaEnlazada<String> frases) {

    }

    @Override
    public void performanceButton(boolean pressedButton, boolean pressedScreen, Jugador jugador, float delta) {

    }

    @Override
    public TextButton ataqueDistancia(boolean pressedButton, boolean pressedScreen, Jugador jugador, boolean primero, float delta) {
        return null;
    }


}
