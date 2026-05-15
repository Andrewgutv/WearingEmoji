package com.andrew.wearingemoji.item;

import com.andrew.wearingemoji.emoji.SleepyEmojiEffect;
import com.andrew.wearingemoji.model.EmojiHelmetModel;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.AnimationController;
import com.geckolib.animation.RawAnimation;
import com.geckolib.model.GeoModel;

public final class SleepyEmojiHelmetItem extends EmojiHelmetItem {
    public SleepyEmojiHelmetItem(Properties properties) {
        super(properties, SleepyEmojiEffect.INSTANCE, "item.wearingemoji.sleepy_emoji_helmet.desc");
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
        return new EmojiHelmetModel("sleepy");
    }
}
