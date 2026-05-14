package com.andrew.wearingemoji.emoji;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

public final class SleepyEmojiEffect implements EmojiEffect {
    public static final SleepyEmojiEffect INSTANCE = new SleepyEmojiEffect();

    private static final double GAZE_RANGE = 8.0;
    private static final int REQUIRED_GAZE_TICKS = 12;
    private static final int COOLDOWN_TICKS = 80;
    private static final int DROWSY_TICKS = 80;
    private static final int DROWSY_AMPLIFIER = 2;

    private SleepyEmojiEffect() {
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
        LivingEntity livingEntity = (LivingEntity) target;
        livingEntity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, DROWSY_TICKS, DROWSY_AMPLIFIER));

        if (livingEntity instanceof Mob mob) {
            mob.setTarget(null);
            mob.setAggressive(false);
        }
    }

    @Override
    public Component triggeredMessage() {
        return Component.translatable("item.wearingemoji.sleepy_emoji_helmet.triggered");
    }
}
