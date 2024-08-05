package net.gurudev.storytelling.client.gui.widget;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;

public class ButtonRowElement extends GuiElement {
    private final TextWidget text; private final ButtonWidget button;

    public ButtonRowElement(Screen screen, TextRenderer renderer, Integer offset, Text text, ButtonWidget.PressAction callback) {
        super(screen.width, screen.height);

        this.text = new TextWidget(leftAlign, centerY+offset, guiWidth, guiHeight, text, renderer); this.text.alignLeft();
        this.button = ButtonWidget.builder(Text.translatable("gui.proceed"), callback)
                .dimensions(rightAlign, centerY+offset, guiWidth, guiHeight).build();
    }

    public TextWidget getText() { return text; }
    public ButtonWidget getElement() { return button; }
}
