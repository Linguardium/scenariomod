package net.gurudev.storytelling.client.gui;

import net.gurudev.storytelling.gui.ScreenHandlerManager;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ScreenManager {
    public static void registerScreens() {
        HandledScreens.register(ScreenHandlerManager.STORYTELLER_SCREEN_HANDLER_TYPE, StorytellerScreen::new);
    }
}
