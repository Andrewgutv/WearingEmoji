package com.andrew.conteststarter.emoji;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;

public final class LoveEmojiEffect implements EmojiEffect {
    public static final LoveEmojiEffect INSTANCE = new LoveEmojiEffect();

    private static final double GAZE_RANGE = 8.0;
    private static final int REQUIRED_GAZE_TICKS = 12;
    private static final int COOLDOWN_TICKS = 60;

    private LoveEmojiEffect() {
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
        return target instanceof Animal animal && isAdultBreedable(animal);
    }

    @Override
    public void apply(ServerPlayer player, Entity target) {
        ((Animal) target).setInLove(player);
    }

    @Override
    public Component triggeredMessage() {
        return Component.translatable("item.conteststarter.love_emoji_helmet.triggered");
    }

    private static boolean isAdultBreedable(Animal animal) {
        return animal.isAlive() && animal.getAge() == 0 && animal.canFallInLove();
    }
}
