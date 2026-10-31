package com.robdu.survivalistsmagicks.events;

import com.mna.api.events.ComponentApplyingEvent;
import com.mna.api.spells.attributes.Attribute;
import com.mna.config.GeneralConfig;
import com.mna.effects.beneficial.EffectFireShield;
import com.mna.spells.components.ComponentFireShield;
import com.robdu.survivalistsmagicks.SurvivalistsMagicks;
import com.robdu.survivalistsmagicks.effects.EffectFrostShield;
import com.robdu.survivalistsmagicks.effects.EffectRegistry;
import com.robdu.survivalistsmagicks.spells.ComponentFrostShield;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import sfiomn.legendarysurvivaloverhaul.registry.AttributeRegistry;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = SurvivalistsMagicks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GeneralEventHandler {

    //TODO: Eventually Clean this up
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        MobEffectInstance frost_shield = event.getEntity().getEffect(EffectRegistry.FROST_SHIELD.get());
        boolean reduce = !event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY) && !event.getSource().is(DamageTypeTags.IS_FALL) && !event.getSource().is(DamageTypes.IN_WALL) && !event.getSource().is(DamageTypes.STARVE) && !event.getSource().is(DamageTypeTags.IS_DROWNING) && !event.getSource().is(DamageTypes.CRAMMING) && !event.getSource().is(DamageTypeTags.BYPASSES_RESISTANCE);

        if (event.getSource().is(DamageTypeTags.IS_FREEZING) && frost_shield != null && reduce) {
            float reduction = event.getAmount() * 0.1F * (float) (frost_shield.getAmplifier() + 1);
            event.setAmount(event.getAmount() - reduction);
        }

        Entity source = event.getSource().getEntity();
        LivingEntity living;

        if (source != null && source instanceof LivingEntity) {

            LivingEntity sourceLiving = (LivingEntity) source;

            if (source != event.getEntity()) {

                living = event.getEntity();
                double dist = living.distanceToSqr(sourceLiving);
                boolean retaliate = dist <= 25.0;

                float pct;

                if (frost_shield != null && retaliate) {
                    pct = (float)((frost_shield.getAmplifier() + 1) * 2);
                    sourceLiving.hurt(sourceLiving.damageSources().freeze(), pct * GeneralConfig.getDamageMultiplier());
                }
            }
        }
    }

    //This shit is the reason why I hate this world
    //It just refuses to work consistently with the != null check,
    //and replace it with instance of, and you're greeted with -
    //a nullPointer exception because frost_shield can be null
    //re add the != null check after the instance of, and you're
    //facing the same exact consistency issues where it won't -
    //work half the time unless you spam it
    //Oh and sometimes the getAmplifier() just refuses to work
    //Because god knows why, nullPointerException my ass
    //Thus we're doing this the ugly way and doing the component apply bs

    //I'm leaving this here just in case someone figures how to fix it or forge and/or mna -
    //Does something and it suddenly works

    /*
    @SubscribeEvent
    public static void onEffectAdded(MobEffectEvent.Added event) {

        if (event.getEntity() instanceof Player player) {

            MobEffectInstance frost_shield = event.getEntity().getEffect(EffectRegistry.FROST_SHIELD.get());
            MobEffectInstance fire_shield = event.getEntity().getEffect(EffectInit.FIRE_SHIELD.get());

            if (frost_shield != null) {
                AttributeMap attributes = player.getAttributes();

                AttributeModifier modifier = new AttributeModifier(
                        UUID.fromString("6632f493-7b82-40fe-9e12-9369812f411c"),
                        "frost_shield_resistance",
                        2.0 + frost_shield.getAmplifier(),
                        AttributeModifier.Operation.ADDITION);

                //Transient Modifiers are not written to disk and are removed on player death
                attributes.getInstance(AttributeRegistry.HEAT_RESISTANCE.get()).addTransientModifier(modifier);
            }
        }
    }
     */

    static final UUID heatResistanceModifierUUID = UUID.fromString("6632f493-7b82-40fe-9e12-9369812f411c");
    static final UUID coldResistanceModifierUUID = UUID.fromString("55022a91-f9a7-4d0b-be7a-2035af0ecac3");
    
    @SubscribeEvent
    public static void onShieldApply(ComponentApplyingEvent event) {
        if (event.getTarget().getLivingEntity() instanceof Player player) {
            AttributeMap attributes = player.getAttributes();

            event.getContext().getSpell().getComponents().forEach(component -> {
                if (component.getPart() instanceof ComponentFrostShield) {

                    float magnitude = component.getValue(Attribute.LESSER_MAGNITUDE);

                    AttributeModifier heatResistanceModifier = new AttributeModifier(
                            heatResistanceModifierUUID,
                            "frost_shield_resistance",
                            2.0 + magnitude,
                            AttributeModifier.Operation.ADDITION);

                    if (!attributes.hasModifier(AttributeRegistry.HEAT_RESISTANCE.get(), heatResistanceModifierUUID)) {
                        attributes.getInstance(AttributeRegistry.HEAT_RESISTANCE.get()).addTransientModifier(heatResistanceModifier);
                    }
                } else if (component.getPart() instanceof ComponentFireShield) {

                    float magnitude = component.getValue(Attribute.LESSER_MAGNITUDE);

                    AttributeModifier coldResistanceModifier = new AttributeModifier(
                            coldResistanceModifierUUID,
                            "fire_shield_resistance",
                            2.0 + magnitude,
                            AttributeModifier.Operation.ADDITION);

                    if (!attributes.hasModifier(AttributeRegistry.COLD_RESISTANCE.get(), coldResistanceModifierUUID)) {
                        attributes.getInstance(AttributeRegistry.COLD_RESISTANCE.get()).addTransientModifier(coldResistanceModifier);
                    }
                }
            });
        }
    }

    @SubscribeEvent
    public static void onShieldRemove(MobEffectEvent.Remove event) {
        if (event.getEntity() instanceof Player player) {
            AttributeMap attributes = player.getAttributes();

            if (event.getEffect() instanceof EffectFrostShield) {
                if (attributes.hasModifier(AttributeRegistry.HEAT_RESISTANCE.get(), heatResistanceModifierUUID)) {
                    attributes.getInstance(AttributeRegistry.HEAT_RESISTANCE.get()).removeModifier(heatResistanceModifierUUID);
                }
            } else if (event.getEffect() instanceof EffectFireShield) {
                if (attributes.hasModifier(AttributeRegistry.COLD_RESISTANCE.get(), coldResistanceModifierUUID)) {
                    attributes.getInstance(AttributeRegistry.COLD_RESISTANCE.get()).removeModifier(coldResistanceModifierUUID);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onShieldExpire(MobEffectEvent.Expired event) {
        if (event.getEntity() instanceof Player player) {
            AttributeMap attributes = player.getAttributes();

            if (event.getEffectInstance().getEffect() instanceof EffectFrostShield) {
                if (attributes.hasModifier(AttributeRegistry.HEAT_RESISTANCE.get(), heatResistanceModifierUUID)) {
                    attributes.getInstance(AttributeRegistry.HEAT_RESISTANCE.get()).removeModifier(heatResistanceModifierUUID);
                }
            } else if (event.getEffectInstance().getEffect() instanceof EffectFireShield) {
                if (attributes.hasModifier(AttributeRegistry.COLD_RESISTANCE.get(), coldResistanceModifierUUID)) {
                    attributes.getInstance(AttributeRegistry.COLD_RESISTANCE.get()).removeModifier(coldResistanceModifierUUID);
                }
            }
        }
    }
}
