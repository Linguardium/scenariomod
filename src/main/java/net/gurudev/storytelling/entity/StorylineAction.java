package net.gurudev.storytelling.entity;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import java.util.HashMap;
import java.util.Map;

public class StorylineAction {
    private String type; private HashMap<String, Object> params;

    public void editParam(String key, Object value) { params.put(key, value); }

    public StorylineAction(String type, HashMap<String, Object> params) {
        this.type = type; this.params = params;
    }

    @SuppressWarnings("DataFlowIssue")
    public StorylineAction(NbtCompound nbtCompound) {
        HashMap<String, Object> map = new HashMap<>();
        NbtCompound list = nbtCompound.getCompound("params");
        for (var key : list.getKeys()) {

            switch (list.get(key).getType()) {
                case NbtElement.STRING_TYPE: map.put(key, list.getString(key)); break;
                case NbtElement.INT_TYPE: map.put(key, list.getInt(key)); break;
                default: throw new IllegalStateException();
            } this.type = nbtCompound.getString("type"); this.params = map; }
    }

    public void execute(StorytellerEntity entity) {
        switch(this.type) {
            case "wait": System.out.println("Something waiting..? " + entity.getName()); break;
            case "move": break;
            case "speak": break;
            default: throw new UnsupportedOperationException();
        }
    }

    public Map<String, Object> getParams() { return params; }
    public String getType() { return type; }
}