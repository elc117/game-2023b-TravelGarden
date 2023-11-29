package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;


public class MyGdxGame extends ApplicationAdapter implements Screen {
  final Drop game;
  SpriteBatch batch;
  Texture img, tCat, tBall, tEnemy;
  private Sprite cat, ball;
  private float posX, posY, velocity, xBall, yBall;
  private boolean attack, gameover;
  private Array<Rectangle> enemies;
  private long lastEnemyTime;
  private int score, power, numEnemies;

  private FreeTypeFontGenerator generator;
  private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
  private BitmapFont bitmap;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("menina.jpg");
		font = new BitmapFont();

		question = "Qual é o bioma na cidade de Santa Maria?";
		options = new String[]{"Floresta Atlantica","Pampas", "Cerrado","Mediterraneo"};
		correctOption = 1; 
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);

		/////////////////////////

		Gdx.gl.glClearColor(1, 1, 1, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

   	      ScreenUtils.clear(1, 0, 0, 1);


		
		batch.end();
		
		 // Lógica para verificar a resposta ao tocar na tela
		 if (Gdx.input.isTouched()) {
        	float touchX = Gdx.input.getX();
        	float touchY = Gdx.input.getY();

        	// Verifique se o toque está dentro da área da opção correta
        	if (touchX > 100 && touchX < 500 && touchY > 200 && touchY < 250) {
            	// Implemente o que acontece quando a resposta está correta
            	Gdx.app.log("QuizGame", "Resposta correta!");
        	} else {
            	// Implemente o que acontece quando a resposta está incorreta
            	Gdx.app.log("QuizGame", "Resposta incorreta!");
        	}

		 }
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}

