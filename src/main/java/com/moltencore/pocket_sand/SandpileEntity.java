package com.moltencore.pocket_sand;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.world.level.block.Blocks;


public class SandpileEntity extends ThrowableItemProjectile {

    public SandpileEntity(EntityType<? extends SandpileEntity> type, Level level) {
        super(type, level);
    }

    public SandpileEntity(Level level, LivingEntity thrower) {
        super(ModEntities.SANDPILE.get(), thrower, level);
    }

    public SandpileEntity(Level level, double x, double y, double z) {
        super(ModEntities.SANDPILE.get(), x, y, z, level);
    }


    @Override
    protected Item getDefaultItem() {
        return ModItems.PILE_OF_SAND.get();
    }

    @Override
    protected float getGravity() {
        return 0.1F; // Default is ~0.03F; raise it to ~0.1F to make it fall faster
    }


    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        if (result.getEntity() instanceof LivingEntity target) {
            target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0));
        }

        this.discard();
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        this.discard(); // Disappear when it hits a block
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.level().addParticle(
                    new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.SAND.defaultBlockState()),
                    this.getX(), this.getY(), this.getZ(),
                    0, 0, 0);

        }
    }


}
