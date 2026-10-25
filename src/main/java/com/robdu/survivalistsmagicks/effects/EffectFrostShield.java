package com.robdu.survivalistsmagicks.effects;

import com.mna.capabilities.entity.MAPFX;
import com.mna.effects.interfaces.INoCreeperLingering;
import com.mna.effects.particles.EffectWithCustomClientParticles;
import net.minecraft.world.effect.MobEffectCategory;

public class EffectFrostShield extends EffectWithCustomClientParticles implements INoCreeperLingering {
    protected EffectFrostShield() {
        super(MobEffectCategory.BENEFICIAL, 1, MAPFX.Flag.AURA_OF_FROST);
    }
}
