package net.ninjago.franksmod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.ninjago.franksmod.Franksmod;
import net.ninjago.franksmod.entity.custom.VoidProjectileEntity;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Franksmod.MODID);

    public static final RegistryObject<EntityType<VoidProjectileEntity>> VOID_PROJECTILE =
            ENTITY_TYPES.register("void_projectile", () -> EntityType.Builder.<VoidProjectileEntity>of(VoidProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("void_projectile"));

    public static void register(IEventBus eventBus) { ENTITY_TYPES.register(eventBus); }

}
