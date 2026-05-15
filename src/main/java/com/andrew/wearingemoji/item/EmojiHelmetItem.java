package com.andrew.wearingemoji.item;

import com.andrew.wearingemoji.emoji.EmojiEffect;
import com.andrew.wearingemoji.model.EmojiHelmetModel;
import com.geckolib.animatable.GeoItem;
import com.geckolib.animatable.client.GeoRenderProvider;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.model.GeoModel;
import com.geckolib.renderer.GeoArmorRenderer;
import com.geckolib.util.GeckoLibUtil;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

public class EmojiHelmetItem extends Item implements GeoItem {
    private final EmojiEffect effect;
    private final String descriptionKey;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public EmojiHelmetItem(Properties properties, EmojiEffect effect, String descriptionKey) {
        super(properties.equippableUnswappable(EquipmentSlot.HEAD));
        this.effect = effect;
        this.descriptionKey = descriptionKey;
        GeoItem.registerSyncedAnimatable(this);
    }

    public final EmojiEffect effect() {
        return effect;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder,
        TooltipFlag tooltipFlag) {
        builder.accept(Component.translatable(descriptionKey).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoArmorRenderer<?, ?> renderer;

            @Override
            public GeoArmorRenderer<?, ?> getGeoArmorRenderer(ItemStack stack, EquipmentSlot slot) {
                if (renderer == null) {
                    GeoModel<EmojiHelmetItem> model = createModel();
                    renderer = new GeoArmorRenderer<>(model);
                }
                return renderer;
            }
        });
    }

    protected GeoModel<EmojiHelmetItem> createModel() {
        return new EmojiHelmetModel("happy");
    }
}
