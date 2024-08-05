package net.gurudev.storytelling.client.gui.widget;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.ScrollableWidget;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ActionsScrollableWidget extends ScrollableWidget {
    private final List<ClickableWidget> childs = new ArrayList<>();

    public ActionsScrollableWidget(int x, int y, int width, int height) {
        super(x, y, width, height, Text.empty());
    }
    public void addChild(ClickableWidget element) { childs.add(element); }
    public List<ClickableWidget> getChilds() { return childs; }

    private ClickableWidget getFocusedChild() {
        return childs.stream().filter(ClickableWidget::isFocused).findFirst().orElse(null); }
    private ClickableWidget getHoveredChild(double scrollY, double mouseX, double mouseY) {
        return childs.stream().filter(child->child.isMouseOver(mouseX,mouseY+scrollY)).findFirst().orElse(null); }
    private void setFocus(ClickableWidget target) { childs.forEach(child->child.setFocused(false)); target.setFocused(true); }

    @Override public void onClick(double mouseX, double mouseY) {
        ClickableWidget child = getHoveredChild(getScrollY(), mouseX, mouseY);
        if (child != null) setFocus(child); }
    @Override public void onRelease(double mouseX, double mouseY) {
        ClickableWidget child = getHoveredChild(getScrollY(), mouseX, mouseY);
        if (child != null) setFocus(child); }
    @Override public boolean charTyped(char chr, int modifiers) {
        ClickableWidget child = getFocusedChild();
        if (child != null) child.charTyped(chr, modifiers);
        return super.charTyped(chr, modifiers); }
    @Override public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        ClickableWidget child = getFocusedChild();
        if (child != null) child.keyPressed(keyCode, scanCode, modifiers);
        return super.keyPressed(keyCode, scanCode, modifiers); }
    @Override public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        ClickableWidget child = getFocusedChild();
        if (child != null) child.keyReleased(keyCode, scanCode, modifiers);
        return super.keyReleased(keyCode, scanCode, modifiers); }

    @Override protected void renderContents(DrawContext context, int mouseX, int mouseY, float delta) {
        childs.forEach(element -> element.render(context, mouseX, mouseY, delta)); }
    @Override protected int getContentsHeight() {
        return childs.stream().mapToInt(ClickableWidget::getHeight).sum(); }

    @Override protected double getDeltaYPerScroll() { return 16; }
    @Override protected void appendClickableNarrations(NarrationMessageBuilder builder) {}
    @Override protected void drawBox(DrawContext context) {}
}
