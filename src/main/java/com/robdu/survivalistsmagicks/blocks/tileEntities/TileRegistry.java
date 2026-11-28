package com.robdu.survivalistsmagicks.blocks.tileEntities;

import com.robdu.survivalistsmagicks.SurvivalistsMagicks;
import com.robdu.survivalistsmagicks.blocks.BlockRegistry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TileRegistry {
    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SurvivalistsMagicks.MOD_ID);

    public static final RegistryObject<BlockEntityType<IHateForgePotionPylonTile>> POTION_PYLON =
            TILES.register("potion_pylon",
                    () -> BlockEntityType.Builder.of(IHateForgePotionPylonTile::new,
                            BlockRegistry.PYLON_WARMTH.get()).build(null));

    public static void register(IEventBus modEventBus) {
        TILES.register(modEventBus);
    }
}
