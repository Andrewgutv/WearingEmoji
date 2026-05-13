package com.andrew.conteststarter;

import com.andrew.conteststarter.event.EmojiHelmetEffectHandler;
import com.mojang.logging.LogUtils;
import com.andrew.conteststarter.registry.ModBlocks;
import com.andrew.conteststarter.registry.ModCreativeModeTabs;
import com.andrew.conteststarter.registry.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(ContestStarter.MOD_ID)
public final class ContestStarter {
    public static final String MOD_ID = "conteststarter";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ContestStarter(IEventBus modEventBus) {
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        NeoForge.EVENT_BUS.register(EmojiHelmetEffectHandler.class);
        LOGGER.info("Loading {}", MOD_ID);
    }
}
