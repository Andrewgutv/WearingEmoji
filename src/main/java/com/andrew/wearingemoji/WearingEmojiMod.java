package com.andrew.wearingemoji;

import com.andrew.wearingemoji.event.EmojiHelmetEffectHandler;
import com.andrew.wearingemoji.registry.ModBlocks;
import com.andrew.wearingemoji.registry.ModCreativeModeTabs;
import com.andrew.wearingemoji.registry.ModItems;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(WearingEmojiMod.MOD_ID)
public final class WearingEmojiMod {
    public static final String MOD_ID = "wearingemoji";
    public static final Logger LOGGER = LogUtils.getLogger();

    public WearingEmojiMod(IEventBus modEventBus) {
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        NeoForge.EVENT_BUS.register(EmojiHelmetEffectHandler.class);
        LOGGER.info("Loading {}", MOD_ID);
    }
}
