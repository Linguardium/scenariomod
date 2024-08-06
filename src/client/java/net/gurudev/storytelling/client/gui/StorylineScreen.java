package net.gurudev.storytelling.client.gui;

import com.google.common.collect.ImmutableList;
import net.gurudev.storytelling.client.gui.widget.ActionsScrollableWidget;
import net.gurudev.storytelling.client.gui.widget.DataFieldWidget;
import net.gurudev.storytelling.client.gui.widget.GuiElement;
import net.gurudev.storytelling.entity.StorylineAction;
import net.gurudev.storytelling.entity.StorytellerEntity;
import net.gurudev.storytelling.gui.StorytellerScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;

import java.util.List;
import java.util.Map;
// TODO: Handled screen with server interaction 
public class StorylineScreen extends HandledScreen<StorytellerScreenHandler> {
	//private final StorytellerEntity entity;
	Screen parent;
	public StorylineScreen(StorytellerScreenHandler handler, PlayerInventory inventory, Screen parent) {
		super(handler, inventory, Text.empty());
		this.parent = parent;
	}
//	public StorylineScreen(StorytellerEntity entity) {
//		super(Text.empty()); this.entity = entity;
//	}
	private List<StorylineAction> getEntityActions() {
		return this.handler.getStorytellerEntity().map(StorytellerEntity::getActions).orElse(ImmutableList.of());
	}
	@Override
	protected void init() {
		super.init();
		GuiElement gui = new GuiElement(width, height);

		ActionsScrollableWidget scrollableWidget = new ActionsScrollableWidget((gui.centerX-gui.guiWidth)/2, gui.centerY/2, gui.centerX+gui.guiWidth, gui.offset*4);
		List<StorylineAction> actions = getEntityActions();

		for (int i = 0; i < actions.size(); i++) {
			StorylineAction action = actions.get(i);

			Map<String, Object> params = action.getParams();

			int offsetY = gui.centerY+gui.offset*(i-2);
			// TODO: handle action display as action widgets
			// possibly register factory to a clientside widget registry with action ids
			switch(action.getType()) {
				case "wait":
					DataFieldWidget time = new DataFieldWidget(this.textRenderer, gui.rightAlign, offsetY, gui.guiWidth, gui.guiHeight,
						action, NbtElement.INT_TYPE, "time");
					time.setMaxLength(63);
					time.setText(params.get("time").toString());
					scrollableWidget.addChild(time);
					break;
				case "move": break;
				case "speak": break;
				default: throw new UnsupportedOperationException();
			}

			scrollableWidget.addChild(new TextWidget(
					gui.leftAlign, offsetY, gui.guiWidth, gui.guiHeight, action.getDisplayText(), this.textRenderer
			).alignLeft());
		}

		ButtonWidget storylineButton = ButtonWidget.builder(Text.translatable("storytelling.actions.new"), callback -> {
			//HashMap<String, Object> map = new HashMap<>();
			//map.put("time", 1);
			// send packet to update screenhandler
			// entity.addAction(new StorylineAction("wait", map));
			// MinecraftClient.getInstance().setScreen(this);
			// TODO: add feature to allow handler to add action

		}).dimensions(gui.leftAlign, (gui.centerY*3-gui.guiHeight)/2, gui.guiWidth, gui.guiHeight).build();

		ButtonWidget successButton = ButtonWidget.builder(Text.translatable("gui.done"), callback -> {

//			scrollableWidget
//				.getChilds()
//				.stream()
//				.filter(child -> child instanceof DataFieldWidget)
//				.map(child->(DataFieldWidget)child)
//				.forEach(field -> {
//					String option = field.getOption();
//					String value = field.getText();
//					switch (field.getNbtType()) {
//						case NbtElement.STRING_TYPE: field.getAction().editParam(option, value); break;
//						case NbtElement.INT_TYPE: field.getAction().editParam(option, Integer.valueOf(value)); break;
//					}
//			});
			this.saveAndClose();
		}).dimensions(gui.rightAlign, (gui.centerY*3-gui.guiHeight)/2, gui.guiWidth, gui.guiHeight).build();

		addDrawableChild(storylineButton);
		addDrawableChild(successButton);
		addDrawableChild(scrollableWidget);
	}

	@Override
	protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {

	}

	@Override
	public void close() {
		if (this.client != null) {
			this.client.setScreen(parent);
		}else {
			super.close();
		}
	}
	private void save() {
		// TODO: use handler to save data

	}
	private void saveAndClose() {
		this.save();
		this.close();
	}
}