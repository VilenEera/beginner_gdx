package com.chhx.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import javax.microedition.khronos.opengles.GL;

public class MyGdxGame implements ApplicationListener {
	private Stage stage;
    private Mario mario;
    private Image image;

	@Override
	public void create() {
        image = new Image(new Texture(Gdx.files.internal("data/13.jpg")));
        image.setPosition(0,170);
        stage = new Stage(480,320,false);
        mario = new Mario(100,190);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(image);
        stage.addActor(mario);
        stage.addActor(mario.buttonL);
        stage.addActor(mario.buttonR);
    }

	@Override
	public void dispose() {
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
