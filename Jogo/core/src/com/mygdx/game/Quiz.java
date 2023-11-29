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

        question = "Qual é a capital do Brasil?";
        options = new String[]{"Brasília", "Rio de Janeiro", "São Paulo", "Belo Horizonte"};
        correctOption = 0;  // A opção correta, neste caso, é "Brasília"

        stage = new Stage();

        // Configuração do botão de avanço
        ImageButton.ImageButtonStyle nextButtonStyle = new ImageButton.ImageButtonStyle();
        nextButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("botao.png")));
        nextButton = new ImageButton(nextButtonStyle);
        nextButton.setPosition(Gdx.graphics.getWidth() / 2 - nextButton.getWidth() / 2, 50);
        nextButton.setVisible(false);  // Inicialmente, mantenha o botão invisível
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Avance para a próxima tela (substitua ProximaTela pela classe da próxima tela)
                Gdx.app.log("QuizGame", "Avançando para a próxima tela...");
                game.setScreen(new MyGdxGame(game));
                // Libere os recursos da tela atual
                dispose();
            }
        });
        stage.addActor(nextButton);

        // Configuração do palco como processador de entrada
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

        // Lógica para verificar a resposta ao tocar na tela
        if (Gdx.input.isTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();
            Gdx.app.log("QuizGame", "Touch X: " + touchX + ", Touch Y: " + touchY);


            // Verifique se o toque está dentro da área da opção correta
            if (touchX > 88 && touchX < 170 && touchY > 500 && touchY < 650) {
                // Implemente o que acontece quando a resposta está correta
                Gdx.app.log("QuizGame", "Resposta correta!");
                // Marque o quiz como concluído
                quizConcluido = true;
                // Torna o botão de avanço visível
                nextButton.setVisible(true);
            } else {
                // Implemente o que acontece quando a resposta está incorreta
                Gdx.app.log("QuizGame", "Resposta incorreta!");
            }
        }

        // Atualize o palco
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        // Desenhe o palco
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        // Libere os recursos da textura do botão
        
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

