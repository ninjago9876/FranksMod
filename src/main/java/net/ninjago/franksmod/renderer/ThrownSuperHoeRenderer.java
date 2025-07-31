package net.ninjago.franksmod.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.ninjago.franksmod.entity.custom.ThrownSuperHoe;
import net.ninjago.franksmod.item.ModItems;
import org.jetbrains.annotations.NotNull;
import org.joml.AxisAngle4d;
import org.joml.Quaternionf;

import java.util.HashMap;
import java.util.Map;

public class ThrownSuperHoeRenderer extends EntityRenderer<ThrownSuperHoe> {
    private final ItemRenderer itemRenderer;

    private final Map<Integer, Float> spinProgress = new HashMap<>();

    static final float SPIN_SPEED = 3;

    public ThrownSuperHoeRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(ThrownSuperHoe entity, float yaw, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int light) {
        float spin = spinProgress.merge(entity.getId(), partialTicks / 20 * SPIN_SPEED, Float::sum);

        if (entity.isInGround() || entity.isReturningToOwner()) {
            spin = entity.getXRot();
        }
        
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
        poseStack.scale(1.0F, 1.0F, 1.0F);
        poseStack.rotateAround(new Quaternionf(new AxisAngle4d(-Math.PI/2, 0d, 1d, 0d)), 0, 0, 0);
        poseStack.rotateAround(new Quaternionf(new AxisAngle4d(Math.PI*2*spin, 0d, 0d, 1d)), 0, 0, 0);

        // Render the hoe item instead of the trident
        this.itemRenderer.renderStatic(
                new ItemStack(ModItems.SUPER_HOE.get()),
                ItemDisplayContext.GROUND,
                light,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                buffer,
                entity.level(),
                entity.getId()
        );

        poseStack.popPose();
        super.render(entity, yaw, partialTicks, poseStack, buffer, light);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ThrownSuperHoe entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}