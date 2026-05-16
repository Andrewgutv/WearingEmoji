package com.andrew.wearingemoji.emoji;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public final class ClownWearState {
    private static final String WEAR_TICKS_TAG = "wearingemoji.clownWearTicks";
    private static final String DROP_COOLDOWN_TAG = "wearingemoji.clownDropCooldown";
    private static final String MUTATED_TAG = "wearingemoji.clownMutated";

    private ClownWearState() {
    }

    public static int advanceWearTicks(Player player) {
        CompoundTag data = player.getPersistentData();
        int wearTicks = data.getIntOr(WEAR_TICKS_TAG, 0) + 1;
        data.putInt(WEAR_TICKS_TAG, wearTicks);
        return wearTicks;
    }

    public static int tickDropCooldown(Player player) {
        CompoundTag data = player.getPersistentData();
        int remaining = data.getIntOr(DROP_COOLDOWN_TAG, 0);
        if (remaining <= 0) {
            return 0;
        }

        remaining--;
        if (remaining <= 0) {
            data.remove(DROP_COOLDOWN_TAG);
            return 0;
        }

        data.putInt(DROP_COOLDOWN_TAG, remaining);
        return remaining;
    }

    public static void resetDropCooldown(Player player, int cooldownTicks) {
        player.getPersistentData().putInt(DROP_COOLDOWN_TAG, cooldownTicks);
    }

    public static boolean isMutated(Player player) {
        return player.getPersistentData().getBooleanOr(MUTATED_TAG, false);
    }

    public static void markMutated(Player player) {
        CompoundTag data = player.getPersistentData();
        data.putBoolean(MUTATED_TAG, true);
    }

    public static void clear(Player player) {
        CompoundTag data = player.getPersistentData();
        data.remove(WEAR_TICKS_TAG);
        data.remove(DROP_COOLDOWN_TAG);
        data.remove(MUTATED_TAG);
    }
}
