package com.robdu.survivalistsmagicks.blocks.tileEntities;

import com.mna.blocks.tileentities.renderers.PylonRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientTileRegistry {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(TileRegistry.POTION_PYLON.get(), PylonRenderer::new);
    }
}
