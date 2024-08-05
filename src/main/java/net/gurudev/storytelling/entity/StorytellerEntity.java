package net.gurudev.storytelling.entity;

import com.google.common.collect.ImmutableList;
import net.gurudev.storytelling.StorytellingMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class StorytellerEntity extends PathAwareEntity {
	private final List<StorylineAction> actions = new ArrayList<>();
	private String texture;
	private String model;
	public int currentStep;

	public ImmutableList<StorylineAction> getActions() { return ImmutableList.copyOf(this.actions); }
	public void addAction(StorylineAction action) { this.actions.add(action); }

	public StorytellerEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}

	public void performStory() {
		StorytellingMod.LOGGER.debug("Performing story actions. Action list size: {}", actions.size());
		if (currentStep < actions.size()) {
			actions.get(currentStep).execute(this);
		} else { currentStep = 0; }
	}

	public String getTexture() { return texture; }
	public String getModel() { return model; }

	// TODO: entity components
	public void writeCustomDataToNbt(NbtCompound nbt) {
		nbt.putString("texture", this.texture); nbt.putString("model", this.model);
		NbtList actions = new NbtList(); this.getActions().forEach(data -> {
			NbtCompound action = new NbtCompound(); action.putString("type", data.getType());
			NbtCompound params = new NbtCompound(); for (var entry : data.getParams().entrySet()) {
				if (entry.getValue() instanceof String) { params.putString(entry.getKey(), (String) entry.getValue()); }
				else if (entry.getValue() instanceof Integer) { params.putInt(entry.getKey(), (Integer) entry.getValue()); }
			} action.put("params", params); actions.add(action); }); nbt.put("actions", actions);
		nbt.putInt("currentStep", this.currentStep); }

	public void readCustomDataFromNbt(NbtCompound nbt) {
		this.texture = nbt.getString("texture"); this.model = nbt.getString("model");
		NbtList actions = nbt.getList("actions", NbtElement.COMPOUND_TYPE);
		for (int i = 0; i < actions.size(); i++) {
			this.addAction(new StorylineAction(actions.getCompound(i)));
		} this.currentStep = nbt.getInt("currentStep"); }
}