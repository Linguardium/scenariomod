package net.gurudev.storytelling.client.entity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.gurudev.storytelling.client.renderer.StorytellerRenderer;
import net.gurudev.storytelling.entity.EntityManager;
import net.gurudev.storytelling.client.model.StorytellerModel;

public class EntityManagerClient {

    public static void registerEntities() {
        registerEntityModelLayers();
        registerEntityRenderers();
    }


    public static void registerEntityModelLayers() {
        EntityModelLayerRegistry.registerModelLayer(StorytellerModel.TEXTURE, StorytellerModel::getTexturedModelData);
    }
    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(EntityManager.STORYTELLER, StorytellerRenderer::new);
    }
}
