package com.game.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class Background extends Actor implements Disposable {
    private Texture skyTexture;
    private Texture groundTexture;
    private float groundY;

    public Background() {
        skyTexture = new Texture(Gdx.files.internal("sky.jpg"));
        groundTexture = new Texture(Gdx.files.internal("grounds.png"));

        setWidth(Gdx.graphics.getWidth());
        setHeight(Gdx.graphics.getHeight());

        // Set ground position and height
        groundY = 0; // Start ground at the bottom of the screen
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Draw ground texture at the bottom of the screen
        batch.draw(groundTexture, getX(), groundY, getWidth(), groundTexture.getHeight());

        // Draw sky texture above the ground
        float skyHeight = getHeight() - groundTexture.getHeight(); // Calculate sky height
        batch.draw(skyTexture, getX(), groundY + groundTexture.getHeight(), getWidth(), skyHeight);
    }

    @Override
    public void act(float delta) {
        // Perform actions per frame if needed
    }

    @Override
    public void dispose() {
        skyTexture.dispose();
        groundTexture.dispose();
    }

    public float getGroundY() {
        return groundY;
    }
}
