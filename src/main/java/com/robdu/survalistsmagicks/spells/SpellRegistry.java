package com.robdu.survalistsmagicks.spells;

import com.mna.Registries;
import com.mna.api.spells.parts.SpellEffect;
import com.robdu.survalistsmagicks.SurvivalistsMagicks;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = SurvivalistsMagicks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SpellRegistry {

    public static SpellEffect WARMUP;
    public static SpellEffect COOLDOWN;

    @SubscribeEvent
    public static void register(RegisterEvent event) {
        event.register(Registries.SpellEffect.get().getRegistryKey(), (registerHelper -> {
            registerHelper.register(new ResourceLocation(SurvivalistsMagicks.MOD_ID, "components/warmup"), SpellRegistry.WARMUP);
            registerHelper.register(new ResourceLocation(SurvivalistsMagicks.MOD_ID, "components/cooldown"), SpellRegistry.COOLDOWN);
        }));
    }

    static {
        SpellRegistry.WARMUP = new ComponentWarmUp(new ResourceLocation(SurvivalistsMagicks.MOD_ID, "textures/components/warmup.png"));
        SpellRegistry.COOLDOWN = new ComponentCoolDown(new ResourceLocation(SurvivalistsMagicks.MOD_ID, "textures/components/cooldown.png"));
    }
}
