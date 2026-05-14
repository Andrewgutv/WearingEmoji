package com.andrew.conteststarter.registry;

import com.andrew.conteststarter.ContestStarter;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ContestStarter.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB =
        CREATIVE_MODE_TABS.register("main", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.conteststarter.main"))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .icon(() -> ModItems.LOVE_EMOJI_HELMET.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ModItems.LOVE_EMOJI_HELMET.get());
                output.accept(ModItems.ANGRY_EMOJI_HELMET.get());
                output.accept(ModItems.STARTER_ITEM.get());
                output.accept(ModItems.STARTER_BLOCK_ITEM.get());
            })
            .build());

    private ModCreativeModeTabs() {
    }

    public static void register(IEventBus modEventBus) {
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
