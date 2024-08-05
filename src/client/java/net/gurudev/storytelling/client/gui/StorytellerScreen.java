package net.gurudev.storytelling.client.gui;

import net.gurudev.storytelling.client.gui.widget.GuiElement;
import net.gurudev.storytelling.entity.StorytellerEntity;
import net.gurudev.storytelling.client.gui.widget.ButtonRowElement;
import net.gurudev.storytelling.client.gui.widget.TextRowElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;

public class StorytellerScreen extends Screen {
    private final StorytellerEntity entity;

    public StorytellerScreen(StorytellerEntity entity) {
        super(Text.empty()); this.entity = entity;
    }

    @Override
    protected void init() {
        super.init(); GuiElement gui = new GuiElement(width, height);

        TextRowElement textureRow = new TextRowElement(this, this.textRenderer, -gui.offset*2, Text.translatable("storytelling.settings.texture"), entity.getTexture());
        this.addDrawableChild(textureRow.getText()); this.addDrawableChild(textureRow.getElement());

        TextRowElement modelRow = new TextRowElement(this, this.textRenderer, -gui.offset, Text.translatable("storytelling.settings.model"), entity.getModel());
        this.addDrawableChild(modelRow.getText()); this.addDrawableChild(modelRow.getElement());

        ButtonRowElement storylineRow = new ButtonRowElement(this, this.textRenderer, gui.offset, Text.translatable("storytelling.settings.storyline"), callback ->
                MinecraftClient.getInstance().setScreen(new StorylineScreen(entity)));
        this.addDrawableChild(storylineRow.getText()); this.addDrawableChild(storylineRow.getElement());

        TextWidget titleText = new TextWidget(gui.centerX-gui.guiWidth/2, gui.centerY/2-gui.guiHeight/2, gui.guiWidth, gui.guiHeight,
                Text.translatable("storytelling.settings.title"), this.textRenderer);

        ButtonWidget successButton = ButtonWidget.builder(Text.translatable("gui.done"), callback -> {
            NbtCompound nbtCompound = new NbtCompound();
            nbtCompound.putString("texture", textureRow.getElement().getText());
            nbtCompound.putString("model", modelRow.getElement().getText());
            entity.readCustomDataFromNbt(nbtCompound);
            MinecraftClient.getInstance().setScreen(null);
        }).dimensions(gui.centerX-gui.guiWidth/2, gui.centerY+gui.centerY/2-gui.guiHeight/2, gui.guiWidth, gui.guiHeight).build();
        this.addDrawableChild(titleText); this.addDrawableChild(successButton);
    }

    //@Override public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {}

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
    }

//    drawableElement.setX(drawableElement.getX()-drawableElement.getWidth()/2);
//    drawableElement.setY(drawableElement.getX()-drawableElement.getHeight()/2);

}