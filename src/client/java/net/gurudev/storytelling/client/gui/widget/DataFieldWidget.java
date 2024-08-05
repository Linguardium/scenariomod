package net.gurudev.storytelling.client.gui.widget;

import net.gurudev.storytelling.entity.StorylineAction;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class DataFieldWidget extends TextFieldWidget  {
    private final StorylineAction action;
    private final byte nbtType; private final String option;

    public DataFieldWidget(TextRenderer textRenderer, int x, int y, int width, int height, StorylineAction action, byte type, String option) {
        super(textRenderer, x, y, width, height, Text.empty());
        this.action = action; this.nbtType = type; this.option = option; }

    public byte getNbtType() { return nbtType; }
    public StorylineAction getAction() { return action; }
    public String getOption() { return option; }
}
