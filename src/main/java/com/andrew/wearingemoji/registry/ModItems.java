package com.andrew.wearingemoji.registry;

import com.andrew.wearingemoji.WearingEmojiMod;
import com.andrew.wearingemoji.item.AngryEmojiHelmetItem;
import com.andrew.wearingemoji.item.ClownEmojiHelmetItem;
import com.andrew.wearingemoji.item.HappyEmojiHelmetItem;
import com.andrew.wearingemoji.item.LoveEmojiHelmetItem;
import com.andrew.wearingemoji.item.QuestionEmojiHelmetItem;
import com.andrew.wearingemoji.item.SleepyEmojiHelmetItem;
import com.andrew.wearingemoji.item.ThinkEmojiHelmetItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WearingEmojiMod.MOD_ID);

    public static final DeferredItem<LoveEmojiHelmetItem> LOVE_EMOJI_HELMET = ITEMS.registerItem(
        "love_emoji_helmet",
        LoveEmojiHelmetItem::new,
        properties -> properties.stacksTo(1)
    );
    public static final DeferredItem<AngryEmojiHelmetItem> ANGRY_EMOJI_HELMET = ITEMS.registerItem(
        "angry_emoji_helmet",
        AngryEmojiHelmetItem::new,
        properties -> properties.stacksTo(1)
    );
    public static final DeferredItem<SleepyEmojiHelmetItem> SLEEPY_EMOJI_HELMET = ITEMS.registerItem(
        "sleepy_emoji_helmet",
        SleepyEmojiHelmetItem::new,
        properties -> properties.stacksTo(1)
    );
    public static final DeferredItem<QuestionEmojiHelmetItem> QUESTION_EMOJI_HELMET = ITEMS.registerItem(
        "question_emoji_helmet",
        QuestionEmojiHelmetItem::new,
        properties -> properties.stacksTo(1)
    );
    public static final DeferredItem<HappyEmojiHelmetItem> HAPPY_EMOJI_HELMET = ITEMS.registerItem(
        "happy_emoji_helmet",
        HappyEmojiHelmetItem::new,
        properties -> properties.stacksTo(1)
    );
    public static final DeferredItem<ClownEmojiHelmetItem> CLOWN_EMOJI_HELMET = ITEMS.registerItem(
        "clown_emoji_helmet",
        ClownEmojiHelmetItem::new,
        properties -> properties.stacksTo(1)
    );
    public static final DeferredItem<ThinkEmojiHelmetItem> THINK_EMOJI_HELMET = ITEMS.registerItem(
        "think_emoji_helmet",
        ThinkEmojiHelmetItem::new,
        properties -> properties.stacksTo(1)
    );

    private ModItems() {
    }

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
