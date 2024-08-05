package net.gurudev.storytelling.client.gui;

import net.gurudev.storytelling.client.gui.widget.ActionsScrollableWidget;
import net.gurudev.storytelling.client.gui.widget.DataFieldWidget;
import net.gurudev.storytelling.client.gui.widget.GuiElement;
import net.gurudev.storytelling.entity.StorylineAction;
import net.gurudev.storytelling.entity.StorytellerEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
// TODO: Handled screen with server interaction 
public class StorylineScreen extends Screen {
	private final StorytellerEntity entity;

	public StorylineScreen(StorytellerEntity entity) {
		super(Text.empty()); this.entity = entity;
	}

	@Override
	protected void init() {
		super.init(); GuiElement gui = new GuiElement(width, height);

		ActionsScrollableWidget scrollableWidget = new ActionsScrollableWidget((gui.centerX-gui.guiWidth)/2, gui.centerY/2, gui.centerX+gui.guiWidth, gui.offset*4);
		List<StorylineAction> actions = entity.getActions();

		for (int i = 0; i < actions.size(); i++) {
			StorylineAction action = actions.get(i); Text actionText = Text.of("Unknown block");
			Map<String, Object> params = action.getParams();

			int offsetY = gui.centerY+gui.offset*(i-2); switch(action.getType()) {
				case "wait":
					DataFieldWidget time = new DataFieldWidget(this.textRenderer, gui.rightAlign, offsetY, gui.guiWidth, gui.guiHeight,
						action, NbtElement.INT_TYPE, "time");
					time.setMaxLength(63); time.setText(params.get("time").toString()); scrollableWidget.addChild(time);
					actionText = Text.translatable("storytelling.actions.wait"); break;
				case "move": break;
				case "speak": break;
				default: throw new UnsupportedOperationException();
			}

			scrollableWidget.addChild(new TextWidget(
					gui.leftAlign, offsetY, gui.guiWidth, gui.guiHeight, actionText, this.textRenderer
			).alignLeft());
		}

		ButtonWidget storylineButton = ButtonWidget.builder(Text.translatable("storytelling.actions.new"), callback -> {
			HashMap<String, Object> map = new HashMap<>(); map.put("time", 1);
			entity.addAction(new StorylineAction("wait", map));
			MinecraftClient.getInstance().setScreen(this);
		}).dimensions(gui.leftAlign, (gui.centerY*3-gui.guiHeight)/2, gui.guiWidth, gui.guiHeight).build();

		ButtonWidget successButton = ButtonWidget.builder(Text.translatable("gui.done"), callback -> {
			scrollableWidget.getChilds().stream().filter(child -> child instanceof DataFieldWidget).forEach(child -> {
				var field = (DataFieldWidget) child; var option = field.getOption(); var value = field.getText();
				switch (field.getNbtType()) {
					case NbtElement.STRING_TYPE: field.getAction().editParam(option, value); break;
					case NbtElement.INT_TYPE: field.getAction().editParam(option, Integer.valueOf(value)); break;
				}
			}); MinecraftClient.getInstance().setScreen(null);
		}).dimensions(gui.rightAlign, (gui.centerY*3-gui.guiHeight)/2, gui.guiWidth, gui.guiHeight).build();
		addDrawableChild(storylineButton); addDrawableChild(successButton); addDrawableChild(scrollableWidget);
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		super.render(context, mouseX, mouseY, delta);
	}

	//@Override public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {}
}