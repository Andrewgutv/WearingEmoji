package com.andrew.conteststarter.emoji;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public interface EmojiEffect {
    double gazeRange();

    int requiredGazeTicks();

    int cooldownTicks();

    boolean canAffect(ServerPlayer player, Entity target);

    void apply(ServerPlayer player, Entity target);

    Component triggeredMessage();
}
