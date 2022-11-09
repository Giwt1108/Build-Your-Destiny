package Buscador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class ResultButton {
    private Texture texture;
    private String label;
    private ImageButton imgButton;
    private TextButton txtButton;

    //CONSTRUCTOR
    public ResultButton(Texture texture, String label, Skin skin, BitmapFont font){
        this.label = label;
        this.texture = texture;
        //Creamos img button y su estilo
        ImageButton.ImageButtonStyle styleImg = new ImageButton.ImageButtonStyle();

        styleImg.imageUp =  new TextureRegionDrawable(new TextureRegion(texture));
        ImageButton imgButton = new ImageButton(styleImg);
        this.imgButton = imgButton;

        //Creamos textButton y su estilo
        TextButton.TextButtonStyle txtStyle = new TextButton.TextButtonStyle();
        txtStyle.up = skin.getDrawable("cuadroDescripcion");
        txtStyle.font = font;
        TextButton txtButton = new TextButton(label,txtStyle);
        this.txtButton = txtButton;
        this.txtButton.setTransform(true);
        //this.txtButton.setScale(0.7f,0.3f );
        float width = 80f;
        float height = 30f;
        this.txtButton.setWidth(width);
        this.txtButton.setHeight(height);
        //this.txtButton.getLabel().setFontScale(10,(float) 10 );
        this.txtButton.setVisible(false);

        //Ponemos el listener para que salga la descripción solo cuando hay hover
        imgButton.addListener(
            new ClickListener(){
                @Override
                public void enter(InputEvent e, float x, float y, int pointer, Actor fromActor){
                    txtButton.setPosition(imgButton.getX() + Gdx.graphics.getWidth()/2 ,imgButton.getY() - txtButton.getHeight());
                    txtButton.setVisible(true);
                }
                @Override
                public void exit(InputEvent e, float x, float y, int pointer, Actor fromActor){
                    txtButton.setVisible(false);
                }
            });



    }


    //GETTERS AND SETTERS


    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ImageButton getImgButton() {
        return imgButton;
    }

    public void setImgButton(ImageButton imgButton) {
        this.imgButton = imgButton;
    }

    public TextButton getTxtButton() {
        return txtButton;
    }

    public void setTxtButton(TextButton txtButton) {
        this.txtButton = txtButton;
    }
}
