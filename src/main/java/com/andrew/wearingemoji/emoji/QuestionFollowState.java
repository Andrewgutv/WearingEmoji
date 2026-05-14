package com.andrew.wearingemoji.emoji;

import java.util.UUID;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Mob;

public final class QuestionFollowState {
    private static final String FOLLOW_PLAYER_TAG = "wearingemoji.questionFollowPlayer";
    private static final String FOLLOW_TICKS_TAG = "wearingemoji.questionFollowTicks";

    private QuestionFollowState() {
    }

    public static void startFollowing(Mob mob, ServerPlayer player, int followTicks) {
        CompoundTag data = mob.getPersistentData();
        data.store(FOLLOW_PLAYER_TAG, UUIDUtil.CODEC, player.getUUID());
        data.putInt(FOLLOW_TICKS_TAG, followTicks);
    }

    public static UUID getFollowPlayerId(Mob mob) {
        return mob.getPersistentData().read(FOLLOW_PLAYER_TAG, UUIDUtil.CODEC).orElse(null);
    }

    public static int tickDown(Mob mob) {
        CompoundTag data = mob.getPersistentData();
        int remainingTicks = data.getIntOr(FOLLOW_TICKS_TAG, 0);
        if (remainingTicks <= 0) {
            clear(mob);
            return 0;
        }

        remainingTicks--;
        if (remainingTicks <= 0) {
            clear(mob);
            return 0;
        }

        data.putInt(FOLLOW_TICKS_TAG, remainingTicks);
        return remainingTicks;
    }

    public static void clear(Mob mob) {
        CompoundTag data = mob.getPersistentData();
        data.remove(FOLLOW_PLAYER_TAG);
        data.remove(FOLLOW_TICKS_TAG);
    }
}
