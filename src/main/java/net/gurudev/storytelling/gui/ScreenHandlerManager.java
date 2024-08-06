package net.gurudev.storytelling.gui;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.gurudev.storytelling.StorytellingMod;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ScreenHandlerManager {
    public static ScreenHandlerType<StorytellerScreenHandler> STORYTELLER_SCREEN_HANDLER_TYPE = Registry.register(Registries.SCREEN_HANDLER, Identifier.of(StorytellingMod.MOD_ID,"storyteller_screenhandler"),new ExtendedScreenHandlerType<>(StorytellerScreenHandler::new, PacketCodecs.UNLIMITED_NBT_COMPOUND));
    public static void registerScreenHandlers() { }
}
