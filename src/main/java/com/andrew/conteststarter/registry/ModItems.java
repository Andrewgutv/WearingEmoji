package com.andrew.conteststarter.registry;

import com.andrew.conteststarter.ContestStarter;
import com.andrew.conteststarter.item.AngryEmojiHelmetItem;
import com.andrew.conteststarter.item.LoveEmojiHelmetItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ContestStarter.MOD_ID);

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

    private ModItems() {
    }

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
