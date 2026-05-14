package com.andrew.wearingemoji.emoji;

import java.util.UUID;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public final class GazeTrackingState {
    private static final String LOOK_TARGET_TAG = "wearingemoji.emojiLookTarget";
    private static final String LOOK_TICKS_TAG = "wearingemoji.emojiLookTicks";

    private GazeTrackingState() {
    }

    public static int advance(ServerPlayer player, UUID targetId) {
        CompoundTag data = player.getPersistentData();
        UUID trackedTargetId = data.read(LOOK_TARGET_TAG, UUIDUtil.CODEC).orElse(null);

        int lookTicks = targetId.equals(trackedTargetId) ? data.getIntOr(LOOK_TICKS_TAG, 0) + 1 : 1;
        data.store(LOOK_TARGET_TAG, UUIDUtil.CODEC, targetId);
        data.putInt(LOOK_TICKS_TAG, lookTicks);
        return lookTicks;
    }

    public static void reset(ServerPlayer player) {
        CompoundTag data = player.getPersistentData();
        data.remove(LOOK_TARGET_TAG);
        data.remove(LOOK_TICKS_TAG);
    }
}
