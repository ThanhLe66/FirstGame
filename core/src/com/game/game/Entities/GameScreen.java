package com.game.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen extends ScreenAdapter {
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Background background;
    private Players player;

    public GameScreen(SpriteBatch batch) {
        this.batch = batch;
        world = new World(new Vector2(0, -9.81f), true);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Khởi tạo background
        background = new Background();

        // Lấy vị trí y của mặt đất từ background
        float groundY = background.getGroundY();

        // Khởi tạo player
        float playerScale = 0.5f; // Tỉ lệ kích thước player so với màn hình
        player = new Players(world, 2, background, Gdx.graphics.getWidth() * playerScale, Gdx.graphics.getHeight() * playerScale);
    }

    @Override
    public void render(float delta) {
        // Xóa màn hình
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Cập nhật và vẽ các đối tượng
        update(delta);
        draw();
    }

    private void update(float delta) {
        // Cập nhật thế giới vật lý
        world.step(delta, 6, 2);

        // Cập nhật trạng thái của background
        background.act(delta);

        // Cập nhật trạng thái của player và xử lý input
        player.handleInput();
        player.update();

        // Kiểm tra va chạm
        checkCollision();
    }

    private void draw() {
        // Vẽ background và player
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        background.draw(batch, 1); // parentAlpha = 1
        player.draw(batch);
        batch.end();
    }

    private void checkCollision() {
        // Xử lý va chạm giữa player và obstacle
        // Bỏ qua phần kiểm tra va chạm nếu không cần
    }

    @Override
    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
        background.dispose();
        player.dispose();
    }
}
