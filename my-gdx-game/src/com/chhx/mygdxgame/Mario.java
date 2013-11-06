package com.chhx.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by star on 11/5/13.
 */
public class Mario extends Actor {
    public static float x;
    public static float y;
    public float statetime;
    private Texture texture;
    private TextureRegion currentFrame;
    //按钮
    ImageButton buttonL;
    ImageButton buttonR;
    //动画类
    Animation animRight;
    Animation animLeft;
    Animation animIdle;
    //枚举类
    STATE state;
    enum STATE{
        Left,Right,Idel
    }

    public Mario(float x,float y) {
        this.x = x;
        this.y = y;
        this.statetime = 0;
        this.show();
        state = STATE.Idel;
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        statetime += Gdx.graphics.getDeltaTime();
        this.update();
        this.check();
        batch.draw(currentFrame,x,y);
    }

    private void check() {
        if(state == STATE.Left){
            currentFrame = animLeft.getKeyFrame(statetime,true);
        }else if(state == STATE.Right){
            currentFrame = animRight.getKeyFrame(statetime,true);
        }else if (state == STATE.Idel){
            currentFrame = animIdle.getKeyFrame(statetime,true);
        }
    }

    private void update() {
        if(state == STATE.Left){
            this.x -= 1.5f;
            if(this.x < 20){
                this.x = 20;
            }
        }else if(state == STATE.Right){
            this.x += 1.5f;
            if(this.x > 400){
                this.x = 400;
            }
        }
        this.x = x;
    }

    public void show() {
        texture = new Texture(Gdx.files.internal("data/Mario.png"));
        TextureRegion[][] spilt = TextureRegion.split(texture,64,64);
        TextureRegion[][] miror = TextureRegion.split(texture,64,64);
        for(TextureRegion[] region1 : miror){
            for(TextureRegion region2 :region1){
                region2.flip(true,false);
            }
        }
        //右
        TextureRegion[] regionR = new TextureRegion[3];
        regionR[0] = spilt[0][1];
        regionR[1] = spilt[0][2];
        regionR[2] = spilt[0][0];
        animRight = new Animation(0.1f,regionR);
        //左
        TextureRegion[] reginoL = new TextureRegion[3];
        reginoL[0] = miror[0][1];
        reginoL[1] = miror[0][2];
        reginoL[2] = miror[0][0];
        animLeft = new Animation(0.1f,reginoL);
        //空闲
        TextureRegion[] regionI = new TextureRegion[1];
        regionI[0] = spilt[0][0];
        animIdle = new Animation(0.1f,regionI);
        //
        buttonL = new ImageButton(new TextureRegionDrawable(spilt[1][0]),new TextureRegionDrawable(spilt[1][1]));
        buttonR = new ImageButton(new TextureRegionDrawable(miror[1][0]),new TextureRegionDrawable(miror[1][1]));
        buttonL.setPosition(20,20);
        buttonR.setPosition(100,20);

        buttonL.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                state = STATE.Idel;
                super.touchUp(event, x, y, pointer, button);
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                state = STATE.Left;
                return true;
            }
        });
        buttonR.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                state = STATE.Right;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                state = STATE.Idel;
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }

}
