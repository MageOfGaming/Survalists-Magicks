package com.robdu.survivalistsmagicks.events;

import com.robdu.survivalistsmagicks.SurvivalistsMagicks;
import com.robdu.survivalistsmagicks.effects.EffectRegistry;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SurvivalistsMagicks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class onDamageHandler {

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        MobEffectInstance frost_shield = event.getEntity().getEffect(EffectRegistry.FROST_SHIELD.get());
        boolean reduce = !event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY) && !event.getSource().is(DamageTypeTags.IS_FALL) && !event.getSource().is(DamageTypes.IN_WALL) && !event.getSource().is(DamageTypes.STARVE) && !event.getSource().is(DamageTypeTags.IS_DROWNING) && !event.getSource().is(DamageTypes.CRAMMING) && !event.getSource().is(DamageTypeTags.BYPASSES_RESISTANCE);

        if (event.getSource().is(DamageTypeTags.IS_FREEZING) && frost_shield != null && reduce) {
            float reduction = event.getAmount() * 0.1F * (float)(frost_shield.getAmplifier() + 1);
            event.setAmount(event.getAmount() - reduction);
        }

        //TODO: Implement the Retaliation effect
    }
}
