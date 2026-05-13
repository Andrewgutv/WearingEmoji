package com.andrew.conteststarter.registry;

import com.andrew.conteststarter.ContestStarter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ContestStarter.MOD_ID);

    public static final DeferredBlock<Block> STARTER_BLOCK = BLOCKS.registerSimpleBlock(
        "starter_block",
        properties -> properties.mapColor(MapColor.STONE).strength(1.5F, 6.0F)
    );

    private ModBlocks() {
    }

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
}
