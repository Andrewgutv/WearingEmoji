package com.andrew.wearingemoji.emoji;

import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

public final class QuestionEmojiEffect implements EmojiEffect {
    public static final QuestionEmojiEffect INSTANCE = new QuestionEmojiEffect();

    private static final double GAZE_RANGE = 8.0;
    private static final int REQUIRED_GAZE_TICKS = 12;
    private static final int COOLDOWN_TICKS = 60;
    private static final int FOLLOW_TICKS = 80;

    private QuestionEmojiEffect() {
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
        return target instanceof LivingEntity livingEntity && livingEntity.isAlive() && livingEntity != player;
    }

    @Override
    public void apply(ServerPlayer player, Entity target) {
        target.lookAt(EntityAnchorArgument.Anchor.EYES, player.getEyePosition());

        if (target instanceof Mob mob) {
            mob.getLookControl().setLookAt(player);
            QuestionFollowState.startFollowing(mob, player, FOLLOW_TICKS);
        }
    }

    @Override
    public Component triggeredMessage() {
        return Component.translatable("item.wearingemoji.question_emoji_helmet.triggered");
    }
}
