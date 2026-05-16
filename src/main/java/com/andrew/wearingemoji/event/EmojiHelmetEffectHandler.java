package com.andrew.wearingemoji.event;

import com.andrew.wearingemoji.emoji.AngryEmojiEffect;
import com.andrew.wearingemoji.emoji.EmojiEffect;
import com.andrew.wearingemoji.emoji.EmojiEffectRuntimeState;
import com.andrew.wearingemoji.emoji.GazeTrackingState;
import com.andrew.wearingemoji.emoji.HappyEmojiEffect;
import com.andrew.wearingemoji.emoji.LoveEmojiEffect;
import com.andrew.wearingemoji.emoji.QuestionEmojiEffect;
import com.andrew.wearingemoji.emoji.SleepyEmojiEffect;
import com.andrew.wearingemoji.item.EmojiHelmetItem;
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
        if (event.getEntity() instanceof ServerPlayer player) {
            tickPlayerEffects(player);
            return;
        }

        tickAffectedEntity(event.getEntity());
    }

    private static void tickPlayerEffects(ServerPlayer player) {
        ItemStack helmetStack = player.getItemBySlot(EquipmentSlot.HEAD);
        if (!(helmetStack.getItem() instanceof EmojiHelmetItem helmetItem)) {
            GazeTrackingState.reset(player);
            return;
        }

        EmojiEffect effect = helmetItem.effect();

        effect.tickWorn(player, helmetStack);



        if (!player.isCrouching()) {
            GazeTrackingState.reset(player);
            return;
        }
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

    private static void tickAffectedEntity(Entity entity) {
        String effectId = EmojiEffectRuntimeState.getEffectId(entity);
        if (effectId.isEmpty()) {
            return;
        }

        EmojiEffect effect = resolveRuntimeEffect(effectId);
        if (effect == null) {
            EmojiEffectRuntimeState.clear(entity);
            return;
        }

        effect.tickAffected(entity);
    }

    private static Entity findTarget(ServerPlayer player, EmojiEffect effect) {
        HitResult hitResult = ProjectileUtil.getHitResultOnViewVector(player, entity -> effect.canAffect(player, entity), effect.gazeRange());
        return hitResult instanceof EntityHitResult entityHitResult ? entityHitResult.getEntity() : null;
    }

    private static boolean hasCompletedGaze(ServerPlayer player, EmojiEffect effect, Entity target) {
        int lookTicks = GazeTrackingState.advance(player, target.getUUID());
        return lookTicks >= effect.requiredGazeTicks();
    }

    private static EmojiEffect resolveRuntimeEffect(String effectId) {
        return switch (effectId) {
            case QuestionEmojiEffect.EFFECT_ID -> QuestionEmojiEffect.INSTANCE;
            case "love" -> LoveEmojiEffect.INSTANCE;
            case "angry" -> AngryEmojiEffect.INSTANCE;
            case "sleepy" -> SleepyEmojiEffect.INSTANCE;
            case "happy" -> HappyEmojiEffect.INSTANCE;
            default -> null;
        };
    }
}
