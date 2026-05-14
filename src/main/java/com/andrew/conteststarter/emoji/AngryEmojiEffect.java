package com.andrew.conteststarter.emoji;

import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;

public final class AngryEmojiEffect implements EmojiEffect {
    public static final AngryEmojiEffect INSTANCE = new AngryEmojiEffect();

    private static final double GAZE_RANGE = 8.0;
    private static final int REQUIRED_GAZE_TICKS = 12;
    private static final int COOLDOWN_TICKS = 80;

    private AngryEmojiEffect() {
    }

    @Override
    public double gazeRange() {
        return GAZE_RANGE;
    }

    @Override
    public int requiredGazeTicks() {
        return REQUIRED_GAZE_TICKS;
    }

    @Override
    public int cooldownTicks() {
        return COOLDOWN_TICKS;
    }

    @Override
    public boolean canAffect(ServerPlayer player, Entity target) {
        return target instanceof Mob mob && target instanceof Enemy && mob.isAlive();
    }

    @Override
    public void apply(ServerPlayer player, Entity target) {
        Mob mob = (Mob) target;
        mob.setAggressive(true);
        mob.lookAt(EntityAnchorArgument.Anchor.EYES, player.getEyePosition());
        if (mob.canAttack(player)) {
            mob.setTarget(player);
        }
    }

    @Override
    public Component triggeredMessage() {
        return Component.translatable("item.conteststarter.angry_emoji_helmet.triggered");
    }
}
