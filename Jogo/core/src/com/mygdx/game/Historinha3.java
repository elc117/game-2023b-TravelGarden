package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;




public class Historinha3 implements Screen {
    final Drop game;
    static private int WIDTH = 600;
    static private int HEIGHT = 100;
    private Stage stage;
    private Texture buttonTexture;
    private Texture backgroundTexture;
    
    OrthographicCamera camera;

    public Historinha3(final Drop passed_game) {
        game = passed_game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);

        stage = new Stage();
        buttonTexture = new Texture(Gdx.files.internal("Button1.png"));
        backgroundTexture = new Texture(Gdx.files.internal("garota.jpeg")); 



        


        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        // Desenhe o fundo
        game.batch.draw(backgroundTexture, 0, 0, WIDTH, HEIGHT);

        game.font.draw(game.batch, "", 50, 50);
        game.font.draw(game.batch, "", 60, 60);
        game.batch.end();

        

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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
    public void dispose() {
        stage.dispose();
        buttonTexture.dispose(); backgroundTexture.dispose(); 
    
    }

    @Override
    public void pause() {
    }
}