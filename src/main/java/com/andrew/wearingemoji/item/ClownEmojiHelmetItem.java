package com.andrew.wearingemoji.item;

import com.andrew.wearingemoji.emoji.ClownEmojiEffect;
import com.andrew.wearingemoji.emoji.ClownWearState;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public final class ClownEmojiHelmetItem extends EmojiHelmetItem {
    public ClownEmojiHelmetItem(Properties properties) {
        super(properties, ClownEmojiEffect.INSTANCE, "item.wearingemoji.clown_emoji_helmet.desc");
    }

    @Override
    public boolean usesDefaultEmojiEffectFlow() {
        return false;
    }

    @Override
    public void onUnequipped(Player player) {
        if (player.getAttribute(Attributes.MAX_HEALTH) != null) {
            player.getAttribute(Attributes.MAX_HEALTH).removeModifier(ClownEmojiEffect.maxHealthModifierId());
            if (player.getHealth() > player.getMaxHealth()) {
                player.setHealth(player.getMaxHealth());
            }
        }

        ClownWearState.clear(player);
    }
}
