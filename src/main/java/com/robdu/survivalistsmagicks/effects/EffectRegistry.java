package com.robdu.survivalistsmagicks.effects;

import com.robdu.survivalistsmagicks.SurvivalistsMagicks;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import sfiomn.legendarysurvivaloverhaul.common.effects.SimpleAttributeEffect;
import sfiomn.legendarysurvivaloverhaul.registry.AttributeRegistry;

public class EffectRegistry {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, SurvivalistsMagicks.MOD_ID);

    public static final RegistryObject<MobEffect> FROST_SHIELD = EFFECTS.register("frost_shield", EffectFrostShield::new);

    public static final RegistryObject<MobEffect> WARMTH = EFFECTS.register("warmth", () -> new SimpleAttributeEffect(MobEffectCategory.BENEFICIAL, 0xffffff, 1.0)
            .addAttributeModifier(AttributeRegistry.HEATING_TEMPERATURE.get(), "7aa2468f-04d0-492b-a69b-a966ed6f6233", 1.0, AttributeModifier.Operation.ADDITION));

    public static final RegistryObject<MobEffect> COLD = EFFECTS.register("cold", () -> new SimpleAttributeEffect(MobEffectCategory.BENEFICIAL, 0xffffff, -1.0)
            .addAttributeModifier(AttributeRegistry.COOLING_TEMPERATURE.get(), "1b087c01-850b-45e7-87fc-bcf6b81d2ea5", -1.0, AttributeModifier.Operation.ADDITION));

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
