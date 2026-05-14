package com.andrew.wearingemoji.registry;

import com.andrew.wearingemoji.WearingEmojiMod;
import com.andrew.wearingemoji.item.AngryEmojiHelmetItem;
import com.andrew.wearingemoji.item.LoveEmojiHelmetItem;
import com.andrew.wearingemoji.item.QuestionEmojiHelmetItem;
import com.andrew.wearingemoji.item.SleepyEmojiHelmetItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WearingEmojiMod.MOD_ID);

    public static final DeferredItem<Item> STARTER_ITEM = ITEMS.registerSimpleItem("starter_item");
    public static final DeferredItem<BlockItem> STARTER_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("starter_block", ModBlocks.STARTER_BLOCK);
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

    private ModItems() {
    }

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
