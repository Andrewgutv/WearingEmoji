package com.andrew.wearingemoji.item;

import com.andrew.wearingemoji.emoji.NoOpEmojiEffect;

public final class ThinkEmojiHelmetItem extends EmojiHelmetItem {
    public ThinkEmojiHelmetItem(Properties properties) {
        super(properties, NoOpEmojiEffect.INSTANCE, "item.wearingemoji.think_emoji_helmet.desc");
    }

    @Override
    public boolean usesDefaultEmojiEffectFlow() {
        return false;
    }
}
