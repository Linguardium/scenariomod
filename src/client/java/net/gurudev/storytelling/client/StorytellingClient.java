package net.gurudev.storytelling.client;

import net.fabricmc.api.ClientModInitializer;
import net.gurudev.storytelling.client.entity.EntityManagerClient;
import net.gurudev.storytelling.client.gui.ScreenManager;
import net.gurudev.storytelling.client.input.KeyboardHandler;

public class StorytellingClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		EntityManagerClient.registerEntities();
		KeyboardHandler.registerKeyboardEvents();
		ScreenManager.registerScreens();
	}
}