package com.robdu.survivalistsmagicks.blocks.tileEntities;

import com.mna.api.blocks.tile.PotionEffectPylonTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class IHateForgePotionPylonTile extends PotionEffectPylonTile {
    public IHateForgePotionPylonTile(BlockPos pPos, BlockState pBlockState) {
        super(TileRegistry.POTION_PYLON.get(), pPos, pBlockState);
    }
}
