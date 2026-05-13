package com.andrew.conteststarter.event;

import com.andrew.conteststarter.emoji.EmojiEffect;
import com.andrew.conteststarter.emoji.GazeTrackingState;
import com.andrew.conteststarter.item.EmojiHelmetItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public final class EmojiHelmetEffectHandler {
    private EmojiHelmetEffectHandler() {
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        ItemStack helmetStack = player.getItemBySlot(EquipmentSlot.HEAD);
        if (!(helmetStack.getItem() instanceof EmojiHelmetItem helmetItem)) {
            GazeTrackingState.reset(player);
            return;
        }

        EmojiEffect effect = helmetItem.effect();
        if (player.getCooldowns().isOnCooldown(helmetStack)) {
            GazeTrackingState.reset(player);
            return;
        }

        Entity target = findTarget(player, effect);
        if (target == null) {
            GazeTrackingState.reset(player);
            return;
        }

        if (!hasCompletedGaze(player, effect, target)) {
            return;
        }

        effect.apply(player, target);
        player.getCooldowns().addCooldown(helmetStack, effect.cooldownTicks());
        player.sendSystemMessage(effect.triggeredMessage());
        GazeTrackingState.reset(player);
    }

    private static Entity findTarget(ServerPlayer player, EmojiEffect effect) {
        HitResult hitResult = ProjectileUtil.getHitResultOnViewVector(player, entity -> effect.canAffect(player, entity), effect.gazeRange());
        return hitResult instanceof EntityHitResult entityHitResult ? entityHitResult.getEntity() : null;
    }

    private static boolean hasCompletedGaze(ServerPlayer player, EmojiEffect effect, Entity target) {
        int lookTicks = GazeTrackingState.advance(player, target.getUUID());
        return lookTicks >= effect.requiredGazeTicks();
    }
}
