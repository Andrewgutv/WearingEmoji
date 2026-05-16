package com.andrew.wearingemoji.emoji;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public final class ClownEmojiEffect implements EmojiEffect {
    public static final ClownEmojiEffect INSTANCE = new ClownEmojiEffect();

    private static final int OFFHAND_DROP_COOLDOWN_TICKS = 40;
    private static final int MUTATION_TICKS = 20 * 60 * 5;
    private static final Identifier MAX_HEALTH_MODIFIER_ID = Identifier.fromNamespaceAndPath("wearingemoji", "clown_mutation");

    private ClownEmojiEffect() {
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
        return Component.translatable("item.wearingemoji.clown_emoji_helmet.triggered");
    }

    @Override
    public void tickWorn(ServerPlayer player, ItemStack helmetStack) {
        ClownWearState.advanceWearTicks(player);
        int dropCooldown = ClownWearState.tickDropCooldown(player);

        if (dropCooldown <= 0 && !player.getOffhandItem().isEmpty()) {
            ItemStack offhand = player.getOffhandItem().copy();
            player.setItemInHand(net.minecraft.world.InteractionHand.OFF_HAND, ItemStack.EMPTY);
            player.drop(offhand, true, false);
            ClownWearState.resetDropCooldown(player, OFFHAND_DROP_COOLDOWN_TICKS);
        }

        if (!ClownWearState.isMutated(player) && player.getPersistentData().getIntOr("wearingemoji.clownWearTicks", 0) >= MUTATION_TICKS) {
            player.getAttribute(Attributes.MAX_HEALTH).addOrUpdateTransientModifier(
                new AttributeModifier(MAX_HEALTH_MODIFIER_ID, 20.0D, AttributeModifier.Operation.ADD_VALUE)
            );
            player.setHealth(player.getMaxHealth());
            ClownWearState.markMutated(player);
        }
    }

    public static Identifier maxHealthModifierId() {
        return MAX_HEALTH_MODIFIER_ID;
    }
}
