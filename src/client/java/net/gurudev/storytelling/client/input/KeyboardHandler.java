package net.gurudev.storytelling.client.input;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.gurudev.storytelling.entity.StorytellerEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.math.Box;
import org.lwjgl.glfw.GLFW;

import static net.gurudev.storytelling.entity.EntityManager.STORYTELLER;

public class KeyboardHandler {
    public static KeyBinding STORY_KEY_BINDING = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.storytelling.performStory",
            InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R,
            "category.storytelling.keybindings"
    ));

    public static void registerKeyboardEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(KeyboardHandler::handleKeyboardEvents);
    }

    public static void handleKeyboardEvents(MinecraftClient client) {
            while (STORY_KEY_BINDING.wasPressed()) {
                if (client.player == null || client.world == null) return;
                Box bounds = Box.of(client.player.getPos(), 32, 32, 32);

                client.world.getEntitiesByType(STORYTELLER, bounds, entity->true).forEach(StorytellerEntity::performStory);
            }
    }
}
