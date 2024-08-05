package net.gurudev.storytelling.entity;


import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.gurudev.storytelling.StorytellingMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EntityManager {
    public static EntityType<StorytellerEntity> STORYTELLER = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(StorytellingMod.MOD_ID, "storyteller"),
            FabricEntityType.Builder.createLiving(StorytellerEntity::new, SpawnGroup.MISC,
                    living->living.defaultAttributes(StorytellerEntity::createMobAttributes))
                    .dimensions(0.6f, 1.8f).eyeHeight(1.62f)
                    .vehicleAttachment(.5f).build("storyteller")
    );

    public static void registerEntities() {

    }
}
