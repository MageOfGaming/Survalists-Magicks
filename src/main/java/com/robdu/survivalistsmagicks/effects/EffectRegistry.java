package com.robdu.survivalistsmagicks.effects;

import com.robdu.survivalistsmagicks.SurvivalistsMagicks;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectRegistry {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, SurvivalistsMagicks.MOD_ID);

    public static final RegistryObject<MobEffect> FROST_SHIELD = EFFECTS.register("frost_shield", EffectFrostShield::new);

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
