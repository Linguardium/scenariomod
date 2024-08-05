package net.gurudev.storytelling.item;

import net.gurudev.storytelling.StorytellingMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemManager {
    public static Item STORYLINER_ITEM;

    public static void registerItems() {
        STORYLINER_ITEM = Registry.register(
                Registries.ITEM, Identifier.of(StorytellingMod.MOD_ID, "storyliner"),
                new StorylinerItem(new Item.Settings().maxCount(1))
        );
    }
}
