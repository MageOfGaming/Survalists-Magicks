package com.robdu.survivalistsmagicks.spells;

import com.mna.api.affinity.Affinity;
import com.mna.api.faction.IFaction;
import com.mna.api.spells.attributes.Attribute;
import com.mna.api.spells.attributes.AttributeValuePair;
import com.mna.factions.Factions;
import com.mna.spells.components.PotionEffectComponent;
import com.robdu.survivalistsmagicks.effects.EffectRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import sfiomn.legendarysurvivaloverhaul.registry.SoundRegistry;

public class ComponentFrostShield extends PotionEffectComponent {
    public ComponentFrostShield(ResourceLocation guiIcon) {
        super(guiIcon, EffectRegistry.FROST_SHIELD, new AttributeValuePair(Attribute.DURATION, 15.0f, 15.0f, 100.0f,5.0f, 2.0f), new AttributeValuePair(Attribute.LESSER_MAGNITUDE, 1.0f, 1.0f, 3.0f,1.0f,4.0f));
    }

    @Override
    public Affinity getAffinity() {
        return Affinity.ICE;
    }

    @Override
    public float initialComplexity() {
        return 20.0f;
    }

    @Override
    public int requiredXPForRote() {
        return 100;
    }

    @Override
    public IFaction getFactionRequirement() {
        return Factions.UNDEAD;
    }

    @Override
    public boolean canBeOnRandomStaff() {
        return true;
    }

    @Override
    public int baselineCooldown() {
        return 10;
    }

    @Override
    public SoundEvent SoundEffect() {
        return SoundRegistry.FROSTBITE.get();
    }
}
