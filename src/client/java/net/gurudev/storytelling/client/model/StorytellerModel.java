package net.gurudev.storytelling.client.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import com.google.common.collect.ImmutableList;
import net.gurudev.storytelling.entity.StorytellerEntity;
import net.minecraft.util.math.MathHelper;

import static net.gurudev.storytelling.StorytellingMod.MOD_ID;

public class StorytellerModel extends EntityModel<StorytellerEntity> {
    public static final EntityModelLayer TEXTURE = new EntityModelLayer(Identifier.of(MOD_ID, "storyteller"), "main");

    private final ModelPart head; private final ModelPart headwear;
    private final ModelPart body; private final ModelPart jacket;
    private final ModelPart left_arm; private final ModelPart left_sleve;
    private final ModelPart right_arm; private final ModelPart right_sleve;
    private final ModelPart left_leg; private final ModelPart left_pants;
    private final ModelPart right_leg; private final ModelPart right_pants;

    public StorytellerModel(ModelPart root) {
        super();
        this.head = root.getChild("head"); this.headwear = root.getChild("headwear");
        this.body = root.getChild("body"); this.jacket = root.getChild("jacket");
        this.left_arm = root.getChild("left_arm"); this.left_sleve = root.getChild("left_sleve");
        this.right_arm = root.getChild("right_arm"); this.right_sleve = root.getChild("right_sleve");
        this.left_leg = root.getChild("left_leg"); this.left_pants = root.getChild("left_pants");
        this.right_leg = root.getChild("right_leg"); this.right_pants = root.getChild("right_pants");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData(); ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, new Dilation(.0f)), ModelTransform.pivot(.0f, .0f, .0f));
        modelPartData.addChild("headwear", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, new Dilation(.5f)), ModelTransform.pivot(.0f, .0f, .0f));
        modelPartData.addChild("body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0f, .0f, -2.0f, 8.0f, 12.0f, 4.0f, new Dilation(.0f)), ModelTransform.pivot(.0f, .0f, .0f));
        modelPartData.addChild("jacket", ModelPartBuilder.create().uv(16, 32).cuboid(-4.0f, .0f, -2.0f, 8.0f, 12.0f, 4.0f, new Dilation(.25f)), ModelTransform.pivot(.0f, .0f, .0f));
        modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(32, 48).cuboid(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, new Dilation(.0f)), ModelTransform.pivot(5.0f, 2.0f, .0f));
        modelPartData.addChild("left_sleve", ModelPartBuilder.create().uv(48, 48).cuboid(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, new Dilation(.25f)), ModelTransform.pivot(5.0f, 2.0f, .0f));
        modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(40, 16).cuboid(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, new Dilation(.0f)), ModelTransform.pivot(-5.0f, 2.0f, .0f));
        modelPartData.addChild("right_sleve", ModelPartBuilder.create().uv(40, 32).cuboid(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, new Dilation(.25f)), ModelTransform.pivot(-5.0f, 2.0f, .0f));
        modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(16, 48).cuboid(-2.0f, .0f, -2.0f, 4.0f, 12.0f, 4.0f, new Dilation(.0f)), ModelTransform.pivot(2.0f, 12.0f, .0f));
        modelPartData.addChild("left_pants", ModelPartBuilder.create().uv(0, 48).cuboid(-2.0f, .0f, -2.0f, 4.0f, 12.0f, 4.0f, new Dilation(.25f)), ModelTransform.pivot(2.0f, 12.0f, .0f));
        modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0f, .0f, -2.0f, 4.0f, 12.0f, 4.0f, new Dilation(.0f)), ModelTransform.pivot(-2.0f, 12.0f, .0f));
        modelPartData.addChild("right_pants", ModelPartBuilder.create().uv(0, 32).cuboid(-2.0f, .0f, -2.0f, 4.0f, 12.0f, 4.0f, new Dilation(.25f)), ModelTransform.pivot(-2.0f, 12.0f, .0f));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(StorytellerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        float armSwing = limbDistance * .5f * 2; float legSwing = limbDistance * 1.4f;

        this.head.yaw = headYaw * .017453292f; this.head.pitch = headPitch * .017453292f;
        this.left_arm.pitch = MathHelper.cos(animationProgress * .6662f) * armSwing;
        this.right_arm.pitch = MathHelper.cos(animationProgress * .6662f + MathHelper.PI) * armSwing;
        this.left_arm.roll = 0f; this.right_arm.roll = 0f;
        this.left_leg.pitch = MathHelper.cos(animationProgress * .6662f + MathHelper.PI) * legSwing;
        this.right_leg.pitch = MathHelper.cos(animationProgress * .6662f) * legSwing;
        this.left_leg.roll = 0f; this.right_leg.roll = 0f;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        ImmutableList.of(
                this.head, this.headwear, this.body, this.jacket,
                this.left_arm, this.left_sleve, this.left_leg, this.left_pants,
                this.right_arm, this.right_sleve, this.right_leg, this.right_pants
        ).forEach((modelPart) -> modelPart.render(matrices, vertices, light, overlay, color));
    }
}