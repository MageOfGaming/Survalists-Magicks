package com.robdu.survivalistsmagicks.items;

import com.mna.api.items.OffsetPlacerItem;
import com.robdu.survivalistsmagicks.SurvivalistsMagicks;
import com.robdu.survivalistsmagicks.blocks.BlockRegistry;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SurvivalistsMagicks.MOD_ID);

    public static final RegistryObject<OffsetPlacerItem> PYLON_WARMTH = ITEMS.register("pylon/warmth", () ->
            new OffsetPlacerItem(BlockRegistry.PYLON_WARMTH.get(), new Item.Properties(), BlockRegistry.PYLON_WARMTH.get()::adjustPlacement));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
