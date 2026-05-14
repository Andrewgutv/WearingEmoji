package com.andrew.wearingemoji.event;

import com.andrew.wearingemoji.emoji.QuestionFollowState;
import java.util.UUID;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Mob;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

public final class QuestionFollowHandler {
    private static final double FOLLOW_SPEED = 1.0;
    private static final double FOLLOW_STOP_DISTANCE_SQR = 4.0;

    private QuestionFollowHandler() {
    }

    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof Mob mob) || mob.level().isClientSide()) {
            return;
        }

        UUID followPlayerId = QuestionFollowState.getFollowPlayerId(mob);
        if (followPlayerId == null) {
            return;
        }

        ServerPlayer player = ((ServerLevel) mob.level()).getServer().getPlayerList().getPlayer(followPlayerId);
        int remainingTicks = QuestionFollowState.tickDown(mob);
        if (player == null || remainingTicks <= 0 || !player.isAlive()) {
            mob.getNavigation().stop();
            QuestionFollowState.clear(mob);
            return;
        }

        mob.getLookControl().setLookAt(player);
        if (mob.distanceToSqr(player) > FOLLOW_STOP_DISTANCE_SQR) {
            mob.getNavigation().moveTo(player, FOLLOW_SPEED);
        } else {
            mob.getNavigation().stop();
        }
    }
}
