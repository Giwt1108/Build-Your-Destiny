package BuildYourDestiny.Game;

import Screens.Splash;
import Screens.Levels;
import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends Game {
	
    
    public final String Title = "Game Turorial 1.0.0.0.0";
    
    @Override
    public void create() {
        setScreen(new Levels());
    }

    @Override
    public void render() {
        super.render();	
    }

    @Override
    public void dispose() {
        super.dispose();
    }
    
    @Override
    public void resize(int width, int height){
        super.resize(width,height);
    }
    
    @Override
    public void pause(){
        super.pause();
        
    }
    
    @Override
    public void resume(){
        super.resume();
        
    }
}