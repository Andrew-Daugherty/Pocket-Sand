package com.moltencore.pocket_sand;

import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.Vec3;

public class SandpileDispenseBehavior implements DispenseItemBehavior {

    @Override
    public ItemStack dispense(BlockSource source, ItemStack stack) {
        if (!(source.getLevel() instanceof ServerLevel)) return stack;
        ServerLevel level = (ServerLevel) source.getLevel();


        Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);

        // Move the spawn point slightly outside the dispenser face
        Vec3 pos = Vec3.atCenterOf(source.getPos()).add(
                direction.getStepX() * 0.6,
                direction.getStepY() * 0.6,
                direction.getStepZ() * 0.6
        );

        SandpileEntity entity = new SandpileEntity(level, pos.x, pos.y, pos.z);
        entity.shoot(direction.getStepX(), direction.getStepY(), direction.getStepZ(), 1.25F, 0F);

        level.addFreshEntity(entity);

        // Play the dispenser sound manually
        source.getLevel().levelEvent(1000, source.getPos(), 0);

        stack.shrink(1);
        return stack;
    }
}
