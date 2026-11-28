package com.robdu.survivalistsmagicks.blocks;

import com.mna.api.affinity.Affinity;
import com.mna.api.blocks.PylonBlock;
import com.mna.blocks.BlockInit;
import com.robdu.survivalistsmagicks.SurvivalistsMagicks;
import com.robdu.survivalistsmagicks.effects.EffectRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SurvivalistsMagicks.MOD_ID);

    private static final BlockBehaviour.Properties PYLON_PROPERTIES =
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.CHIME)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .strength(3.0F, 10.0F)
                    .isSuffocating(BlockInit.NEVER);

    public static final RegistryObject<PylonBlock> PYLON_WARMTH =
            BLOCKS.register("pylon/warmth", () ->
                    new IHateForgePotionPylonBlock(PYLON_PROPERTIES, EffectRegistry.WARMTH, "curse you fuck my life mod loader")
                            .addPowerRequirement(Affinity.FIRE, 0.2f));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
