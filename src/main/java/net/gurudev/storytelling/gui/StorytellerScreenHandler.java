package net.gurudev.storytelling.gui;

import net.gurudev.storytelling.entity.StorytellerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class StorytellerScreenHandler extends ScreenHandler {
    StorytellerEntity entity;
    public StorytellerScreenHandler(int syncId, PlayerInventory inventory, NbtCompound data) {
        this(ScreenHandlerManager.STORYTELLER_SCREEN_HANDLER_TYPE, syncId);
    }

    public StorytellerScreenHandler(int syncId, PlayerInventory inventory, StorytellerEntity entity) {
        this(syncId, inventory, new NbtCompound());
        this.entity = entity;
    }

    protected StorytellerScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }
    public Optional<StorytellerEntity> getStorytellerEntity() {
        return Optional.of(this.entity);
    }
    public String getEntityTextureId() {
        if (this.entity != null) {
            return this.entity.getTexture();
        }
        // TODO: set actual default texture id
        return "default";
    }
    public String getEntityModelId() {
        if (this.entity != null) {
            return this.entity.getModel();
        }
        // TODO: set actual default model id
        return "default";
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return false;
    }
}
