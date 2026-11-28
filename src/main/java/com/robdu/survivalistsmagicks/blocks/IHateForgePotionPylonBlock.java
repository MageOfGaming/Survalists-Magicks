package com.robdu.survivalistsmagicks.blocks;

import com.mna.api.blocks.PotionPylonBlock;
import com.robdu.survivalistsmagicks.blocks.tileEntities.IHateForgePotionPylonTile;
import com.robdu.survivalistsmagicks.blocks.tileEntities.TileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class IHateForgePotionPylonBlock extends PotionPylonBlock {
    public IHateForgePotionPylonBlock(Properties pProperties, Supplier<MobEffect> applicationEffect, String runeword) {
        super(pProperties, applicationEffect, runeword);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        var tile = new IHateForgePotionPylonTile(pos, state);
        tile.setRuneword(runeword);
        return tile;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return type == TileRegistry.POTION_PYLON.get()
                ? (lvl, pos, st, be) -> IHateForgePotionPylonTile.Tick(lvl, pos, st, (IHateForgePotionPylonTile) be)
                : null;
    }
}
