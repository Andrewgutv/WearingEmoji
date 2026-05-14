package com.andrew.wearingemoji.emoji;

import java.util.UUID;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;

public final class EmojiEffectRuntimeState {
    private static final String EFFECT_ID_TAG = "wearingemoji.runtimeEffectId";
    private static final String TARGET_PLAYER_TAG = "wearingemoji.runtimeTargetPlayer";
    private static final String RUNTIME_TICKS_TAG = "wearingemoji.runtimeTicks";

    private EmojiEffectRuntimeState() {
    }

    public static void start(Entity entity, String effectId, UUID targetPlayerId, int runtimeTicks) {
        CompoundTag data = entity.getPersistentData();
        data.putString(EFFECT_ID_TAG, effectId);
        data.store(TARGET_PLAYER_TAG, UUIDUtil.CODEC, targetPlayerId);
        data.putInt(RUNTIME_TICKS_TAG, runtimeTicks);
    }

    public static String getEffectId(Entity entity) {
        return entity.getPersistentData().getStringOr(EFFECT_ID_TAG, "");
    }

    public static UUID getTargetPlayerId(Entity entity) {
        return entity.getPersistentData().read(TARGET_PLAYER_TAG, UUIDUtil.CODEC).orElse(null);
    }

    public static int tickDown(Entity entity) {
        CompoundTag data = entity.getPersistentData();
        int remainingTicks = data.getIntOr(RUNTIME_TICKS_TAG, 0);
        if (remainingTicks <= 0) {
            clear(entity);
            return 0;
        }

        remainingTicks--;
        if (remainingTicks <= 0) {
            clear(entity);
            return 0;
        }

        data.putInt(RUNTIME_TICKS_TAG, remainingTicks);
        return remainingTicks;
    }

    public static void clear(Entity entity) {
        CompoundTag data = entity.getPersistentData();
        data.remove(EFFECT_ID_TAG);
        data.remove(TARGET_PLAYER_TAG);
        data.remove(RUNTIME_TICKS_TAG);
    }
}
