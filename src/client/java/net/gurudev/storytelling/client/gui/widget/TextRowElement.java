package net.gurudev.storytelling.client.gui.widget;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;

public class TextRowElement extends GuiElement {
    private final TextWidget text; private final TextFieldWidget field;

    public TextRowElement(Screen screen, TextRenderer renderer, Integer offset, Text text, String placeholder) {
        super(screen.width, screen.height);

        this.text = new TextWidget(leftAlign, centerY+offset, guiWidth, guiHeight, text, renderer); this.text.alignLeft();
        this.field = new TextFieldWidget(renderer, rightAlign, centerY+offset, guiWidth, guiHeight, Text.empty());
        this.field.setText(placeholder); this.field.setMaxLength(63);
    }

    public TextWidget getText() { return text; }
    public TextFieldWidget getElement() { return field; }
}
