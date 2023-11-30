package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

public class Quiz extends ApplicationAdapter implements Screen{
    final Drop game;
    SpriteBatch batch;
    Texture img;
    BitmapFont font;
    String[] options;
    String question;
    int correctOption;

    Stage stage;
    ImageButton nextButton;
    boolean quizConcluido;

    
    public Quiz (final Drop passed_game) {
        game = passed_game;
        batch = new SpriteBatch();
        font = new BitmapFont();
        img = new Texture("jardim.jpg");

        question = "Qual ano foi fundado o Centro de Ciências Naturais\n e Exatas (CCNE)?";
        options = new String[]{"1970", "1980", "1960", "1500"};
        correctOption = 0; 

        stage = new Stage();

        // Configuração do botão de avanço
        ImageButton.ImageButtonStyle nextButtonStyle = new ImageButton.ImageButtonStyle();
        nextButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("botao.png")));
        nextButton = new ImageButton(nextButtonStyle);
        nextButton.setPosition(Gdx.graphics.getWidth() / 2 - nextButton.getWidth() / 2, 50);
        nextButton.setVisible(false);  
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
              
               
                game.setScreen(new MyGdxGame(game));
               
                dispose();
            }
        });
        stage.addActor(nextButton);

     
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ScreenUtils.clear(1, 0, 0, 1);

        batch.begin();

        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(batch, question, 100, 300);
        for (int i = 0; i < options.length; i++) {
            font.draw(batch, (i + 1) + ". " + options[i], 100, 250 - i * 50);
        }

        batch.end();

        
        if (Gdx.input.isTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();
            Gdx.app.log("QuizGame", "Touch X: " + touchX + ", Touch Y: " + touchY);


            
            if (touchX > 88 && touchX < 170 && touchY > 500 && touchY < 650) {
                
                Gdx.app.log("QuizGame", "Resposta correta!");
           
                quizConcluido = true;
              
                nextButton.setVisible(true);
            } else {
              
                Gdx.app.log("QuizGame", "Resposta incorreta!");
            }
        }

      
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
 
        
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void resume() {
    }



    @Override
    public void pause() {
    }
}

