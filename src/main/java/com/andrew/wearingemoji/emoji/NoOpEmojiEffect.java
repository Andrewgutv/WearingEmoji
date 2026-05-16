package com.andrew.wearingemoji.emoji;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public final class NoOpEmojiEffect implements EmojiEffect {
    public static final NoOpEmojiEffect INSTANCE = new NoOpEmojiEffect();

    private NoOpEmojiEffect() {
    }

    @Override
    public double gazeRange() {
        return 0;
    }

    @Override
    public int requiredGazeTicks() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int cooldownTicks() {
        return 0;
    }

    @Override
    public boolean canAffect(ServerPlayer player, Entity target) {
        return false;
    }

    @Override
    public void apply(ServerPlayer player, Entity target) {
    }

    @Override
    public Component triggeredMessage() {
        return Component.empty();
    }
}
