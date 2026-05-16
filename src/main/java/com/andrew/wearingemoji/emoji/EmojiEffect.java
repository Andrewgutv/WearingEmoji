package com.andrew.wearingemoji.emoji;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public interface EmojiEffect {
    double gazeRange();

    int requiredGazeTicks();

    int cooldownTicks();

    boolean canAffect(ServerPlayer player, Entity target);

    void apply(ServerPlayer player, Entity target);

    Component triggeredMessage();

    default void tickWorn(ServerPlayer player, ItemStack helmetStack) {
    }

    default void tickAffected(Entity entity) {
    }

}
