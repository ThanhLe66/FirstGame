package com.game.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.game.Entities.GameScreen;

public class MyGameClass extends Game {
	private SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		// Tạo và đặt màn hình chơi game là GameScreen, truyền vào batch
		this.setScreen(new GameScreen(batch));
	}

	@Override
	public void render() {
		super.render(); // Gọi phương thức render() của màn hình hiện tại (GameScreen)
	}

	@Override
	public void dispose() {
		super.dispose();
		// Bổ sung xử lý giải phóng tài nguyên khi game kết thúc
		batch.dispose(); // Giải phóng SpriteBatch
	}
}
