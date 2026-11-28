package com.robdu.survivalistsmagicks;

import com.mna.api.guidebook.RegisterGuidebooksEvent;
import com.mna.api.items.MACreativeTabs;
import com.mojang.logging.LogUtils;
import com.robdu.survivalistsmagicks.blocks.BlockRegistry;
import com.robdu.survivalistsmagicks.blocks.tileEntities.ClientTileRegistry;
import com.robdu.survivalistsmagicks.blocks.tileEntities.TileRegistry;
import com.robdu.survivalistsmagicks.effects.EffectRegistry;
import com.robdu.survivalistsmagicks.items.ItemRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SurvivalistsMagicks.MOD_ID)
public class SurvivalistsMagicks
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "survivalists_magicks";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public SurvivalistsMagicks(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        EffectRegistry.register(modEventBus);
        ItemRegistry.register(modEventBus);
        TileRegistry.register(modEventBus);
        BlockRegistry.register(modEventBus);

        if (FMLEnvironment.dist.isClient()) {
            modEventBus.register(ClientTileRegistry.class);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == MACreativeTabs.GENERAL) {
            event.accept(ItemRegistry.PYLON_WARMTH);
        }
    }

    @SubscribeEvent
    public void onRegisterCodex(RegisterGuidebooksEvent event) {
        event.getRegistry().addGuidebookPath(new ResourceLocation(MOD_ID, "guide"));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
