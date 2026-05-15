package com.andrew.wearingemoji.emoji;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;

public final class HappyEmojiEffect implements EmojiEffect {
    public static final HappyEmojiEffect INSTANCE = new HappyEmojiEffect();

    private static final double GAZE_RANGE = 8.0;
    private static final int REQUIRED_GAZE_TICKS = 12;
    private static final int COOLDOWN_TICKS = 80;
    private static final int CHEERFUL_TICKS = 120;

    private HappyEmojiEffect() {
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
        return target instanceof LivingEntity livingEntity
            && livingEntity.isAlive()
            && livingEntity != player
            && !(target instanceof Enemy);
    }

    @Override
    public void apply(ServerPlayer player, Entity target) {
        LivingEntity livingEntity = (LivingEntity) target;
        livingEntity.addEffect(new MobEffectInstance(MobEffects.SPEED, CHEERFUL_TICKS, 0));
        livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, CHEERFUL_TICKS, 0));

        if (livingEntity instanceof Mob mob) {
            mob.setTarget(null);
            mob.setAggressive(false);
            mob.getLookControl().setLookAt(player);
        }
    }

    @Override
    public Component triggeredMessage() {
        return Component.translatable("item.wearingemoji.happy_emoji_helmet.triggered");
    }
}
