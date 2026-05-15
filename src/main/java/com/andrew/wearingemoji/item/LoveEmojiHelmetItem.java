package com.andrew.wearingemoji.item;

import com.andrew.wearingemoji.emoji.LoveEmojiEffect;
import com.andrew.wearingemoji.model.EmojiHelmetModel;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.AnimationController;
import com.geckolib.animation.RawAnimation;
import com.geckolib.model.GeoModel;

public final class LoveEmojiHelmetItem extends EmojiHelmetItem {
    public LoveEmojiHelmetItem(Properties properties) {
        super(properties, LoveEmojiEffect.INSTANCE, "item.wearingemoji.love_emoji_helmet.desc");
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("main", 0, state -> {
            if (state.controller().isPlayingTriggeredAnimation())
                return state.controller().getPlayState();
            return state.setAndContinue(RawAnimation.begin().thenLoop("idle"));
        }).triggerableAnim("trigger", RawAnimation.begin().thenPlay("trigger")));
    }

    @Override
    protected GeoModel<EmojiHelmetItem> createModel() {
        return new EmojiHelmetModel("love");
    }
}
