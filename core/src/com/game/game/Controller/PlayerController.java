package com.game.game.Controller;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.game.game.Entities.*;

public class PlayerController {
    private Players players;
    private boolean isJumping;

    public PlayerController(Players play) {
        this.players = play;
    }

}
