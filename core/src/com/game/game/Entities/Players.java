package com.game.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Players {
    private Body body;
    private Texture texture;
    private float width;
    private float height;
    private float groundY; // Vị trí y của mặt đất

    public Players(World world, float initialX, Background background, float width, float height) {
        // Load texture của player (ví dụ)
        texture = new Texture(Gdx.files.internal("player.png"));
        this.width = width;
        this.height = height;

        // Lấy vị trí y của mặt đất từ background
        this.groundY = background.getGroundY();

        // Tạo player trong thế giới vật lí
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        // Đặt vị trí ban đầu của player sao cho nó nằm chính xác trên mặt đất
        float playerInitialY = groundY + height / 2;
        bodyDef.position.set(initialX, playerInitialY);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2); // Hình dạng hình hộp cho player

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.3f;

        body.createFixture(fixtureDef);

        shape.dispose();
    }


    public void handleInput() {
        // Xử lý input từ người dùng (ví dụ: di chuyển player)
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            body.applyLinearImpulse(new Vector2(0.1f, 0), body.getWorldCenter(), true);
        }
    } public void update() {
        // Cập nhật trạng thái của player (nếu cần)
        checkGroundCollision(); // Kiểm tra va chạm với mặt đất
        checkBounds(); // Kiểm tra giới hạn màn hình
    }

    private void checkGroundCollision() {
        // Lấy vị trí của player
        Vector2 position = body.getPosition();
        float halfWidth = width / 2;
        float halfHeight = height / 2;

        // Lấy vị trí y của mặt đất
        float groundTop = groundY + halfHeight;

        // Kiểm tra nếu player đụng vào mặt đất
        if (position.y - halfHeight <= groundTop) {
            // Đặt lại vị trí của player
            body.setTransform(position.x, groundTop + halfHeight, body.getAngle());
            body.setLinearVelocity(0, 0); // Ngừng player di chuyển khi đụng mặt đất
        }
    }

    private void checkBounds() {
        Vector2 position = body.getPosition();
        float halfWidth = width / 2;

        // Lấy giới hạn màn hình dựa trên kích thước cửa sổ
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        // Đặt giới hạn để player không vượt ra khỏi màn hình
        if (position.x < halfWidth) {
            body.setTransform(halfWidth, position.y, body.getAngle()); // Đặt lại vị trí nếu ra khỏi bên trái
        } else if (position.x > screenWidth - halfWidth) {
            body.setTransform(screenWidth - halfWidth, position.y, body.getAngle()); // Đặt lại vị trí nếu ra khỏi bên phải
        }

        // Có thể thêm các điều kiện cho trường hợp player vượt quá phạm vi màn hình theo chiều y nếu cần
    }


    public void draw(Batch batch) {
        Vector2 position = body.getPosition();
        batch.draw(texture, position.x - width / 2, position.y - height / 2, width, height);
    }

    public void dispose() {
        texture.dispose();
    }

    public Body getBody() {
        return body;
    }
}
