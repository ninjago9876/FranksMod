package net.ninjago.franksmod.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.ninjago.franksmod.entity.ModEntities;
import net.ninjago.franksmod.item.ModItems;

public class VoidProjectileEntity extends ThrowableItemProjectile {
    public VoidProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public VoidProjectileEntity(Level pLevel) {
        super(ModEntities.VOID_PROJECTILE.get(), pLevel);
    }

    public VoidProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.VOID_PROJECTILE.get(), livingEntity, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.FRANKS_VOID.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (!this.level().isClientSide()) {
            Entity entity = pResult.getEntity();
            if (entity instanceof LivingEntity livingEntity) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 200));

            }
        }

        super.onHitEntity(pResult);
    }
}
