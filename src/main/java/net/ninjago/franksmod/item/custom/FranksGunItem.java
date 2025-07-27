package net.ninjago.franksmod.item.custom;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.ninjago.franksmod.Franksmod;
import net.ninjago.franksmod.sound.ModSounds;
import org.jetbrains.annotations.NotNull;

public class FranksGunItem extends Item {
    public FranksGunItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        if (pLevel.isClientSide) return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
        pPlayer.displayClientMessage(Component.literal("You fumble the gun and shoot yourself!"), false);
        pPlayer.hurt(new DamageSource(pLevel.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(Franksmod.MODID, "franks_gun_damage_type")))
                        , pPlayer, pPlayer),
                100
        );

        pLevel.playSeededSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                ModSounds.FRANKS_GUN_SHOT.get(), SoundSource.NEUTRAL, 1, 1, 0);

        pPlayer.startUsingItem(pUsedHand);
        return InteractionResultHolder.sidedSuccess(pPlayer.getItemInHand(pUsedHand), pLevel.isClientSide());
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return 20;
    }
}
