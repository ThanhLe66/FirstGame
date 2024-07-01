package com.game.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Obstacle {
    private Body body;
    private Texture texture;

    public Obstacle(World world) {
        // Tạo vật cản trong thế giới vật lí
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody; // Vật cản là StaticBody (không di chuyển)
        bodyDef.position.set(5, 1); // Vị trí của vật cản

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1, 1); // Hình dạng hình hộp cho vật cản

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.3f;

        body.createFixture(fixtureDef);

        shape.dispose();

        // Load texture của vật cản (ví dụ)
        texture = new Texture("obstacle.png");
    }

    public void update() {
        // Cập nhật trạng thái của vật cản (nếu cần)
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }
}
