package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainMenuScreen implements Screen {
    final Drop game;
    static private int WIDTH = 600;
    static private int HEIGHT = 100;
    private Stage stage;
    private Texture buttonTexture;
    private Texture backgroundTexture;
    
    OrthographicCamera camera;

    public MainMenuScreen(final Drop passed_game) {
        game = passed_game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);

        stage = new Stage();
        buttonTexture = new Texture(Gdx.files.internal("botao.png"));
        backgroundTexture = new Texture(Gdx.files.internal("background.png")); // Carrega a textura do fundo


        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        ImageButton startButton = new ImageButton(style);
        
        startButton.setPosition(WIDTH / 2 - startButton.getWidth() / 2, HEIGHT / 2 - startButton.getHeight() / 2);

        // Adicione um ouvinte de clique ao botão
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Quiz(game));
                dispose();
            }
        });

        // Adiciona o botão ao palco
        stage.addActor(startButton);

        // Adiciona o palco como processador de entrada
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
        buttonTexture.dispose(); backgroundTexture.dispose(); // Libera os recursos da textura do fundo
    
    }

    @Override
    public void pause() {
    }
}
