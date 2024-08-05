package net.gurudev.storytelling.client.gui.widget;

public class GuiElement {
    public final int centerX, centerY, guiWidth, guiHeight;
    public final int leftAlign, rightAlign, offset;

    public GuiElement(int width, int height) {
        this.guiWidth = width / 4;
        this.guiHeight = height / 15;

        this.centerX = width / 2;
        this.centerY = height / 2;

        this.leftAlign = centerX / 2 - guiWidth / 4;
        this.rightAlign = centerX + leftAlign - guiWidth / 2;

        this.offset = (int) (guiHeight * 1.5);
    }
}