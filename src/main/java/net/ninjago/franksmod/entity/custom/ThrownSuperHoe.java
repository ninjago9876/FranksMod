package net.ninjago.franksmod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.ninjago.franksmod.Franksmod;
import net.ninjago.franksmod.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.stream.Collectors;

public class ThrownSuperHoe extends ThrownTrident {
    private int radius = 4;
    private int height_radius = 2;

    private static final TagKey<Block> TILLABLE_TAG = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Franksmod.MODID, "tillable"));
    private static final TagKey<Block> IGNORE_TILL_TAG = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Franksmod.MODID, "ignore_till"));
    private static final TagKey<Block> CROPS_TAG = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Franksmod.MODID, "crops"));

    public ThrownSuperHoe(EntityType<? extends ThrownTrident> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        tridentItem = new ItemStack(ModItems.SUPER_HOE.get());
    }

    public ThrownSuperHoe(Level pLevel, LivingEntity pShooter, ItemStack pStack) {
        super(pLevel, pShooter, pStack);
        tridentItem = new ItemStack(ModItems.SUPER_HOE.get());
    }

    public void setThrownStack(ItemStack stack) {
        this.tridentItem = stack.copy();
        this.entityData.set(ThrownTrident.ID_LOYALTY, (byte) EnchantmentHelper.getLoyalty(stack));
    }

    public boolean isInGround() {
        return inGround;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult pResult) {
        super.onHitBlock(pResult);

        Level level = this.level();

        tryTillBlock(level, pResult.getBlockPos());

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                for (int y = -height_radius; y <= height_radius; y++) {
                    if (tryTillBlock(level, pResult.getBlockPos().offset(x, y, z))) {
                        break;
                    }
                }
            }
        }
    }

    private boolean tryTillBlock(Level level, BlockPos pos) {
        if (level.getBlockState(pos).is(TILLABLE_TAG) && (level.getBlockState(pos.above()).is(IGNORE_TILL_TAG) || level.getBlockState(pos.above()).is(CROPS_TAG))) {
            int maxAge = level.getBlockState(pos.above()).getProperties()
                    .stream()
                    .filter(p -> p.getName().equals("age") && p instanceof IntegerProperty)
                    .map(p -> ((IntegerProperty) p).getPossibleValues().stream()) // << now each is a Stream<Integer>
                    .flatMap(s -> s) // or use .flatMap(Stream::of) safely
                    .max(Integer::compareTo) // Find the max value
                    .orElse(0); // Fallback default

            if (level.getBlockState(pos.above()).is(CROPS_TAG) && level.getBlockState(pos.above()).getValue(BlockStateProperties.AGE_7) != maxAge) {
                return false;
            }
            this.level().setBlock(pos, Blocks.FARMLAND.defaultBlockState(), 3);
            this.level().destroyBlock(pos.above(), true);
            return true;
        }
        return false;
    }


    public boolean isReturningToOwner() {
        return this.isNoPhysics()
                && this.getOwner() != null
                && this.getOwner().isAlive()
                && EnchantmentHelper.getLoyalty(this.tridentItem) > 0;
    }
}
