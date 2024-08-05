package net.gurudev.storytelling.client.renderer;

import net.gurudev.storytelling.entity.StorytellerEntity;
import net.gurudev.storytelling.client.model.StorytellerModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import static net.gurudev.storytelling.StorytellingMod.MOD_ID;

public class StorytellerRenderer extends MobEntityRenderer<StorytellerEntity, StorytellerModel> {
    public StorytellerRenderer(EntityRendererFactory.Context context) {
        super(context, new StorytellerModel(context.getPart(StorytellerModel.TEXTURE)), .5f);
    }

    @Override
    public Identifier getTexture(StorytellerEntity entity) {
        return Identifier.of(MOD_ID, "textures/entity/steve.png");
    }
}