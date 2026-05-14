package com.andrew.wearingemoji.item;

import com.andrew.wearingemoji.emoji.EmojiEffect;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

public class EmojiHelmetItem extends Item {
    private final EmojiEffect effect;
    private final String descriptionKey;

    public EmojiHelmetItem(Properties properties, EmojiEffect effect, String descriptionKey) {
        super(properties.equippableUnswappable(EquipmentSlot.HEAD));
        this.effect = effect;
        this.descriptionKey = descriptionKey;
    }

    public final EmojiEffect effect() {
        return effect;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder,
        TooltipFlag tooltipFlag) {
        builder.accept(Component.translatable(descriptionKey).withStyle(ChatFormatting.GRAY));
    }
}
