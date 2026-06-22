package com.friendsmod.client;

import com.friendsmod.entity.SevaEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;

public class SevaRenderer extends HumanoidMobRenderer<SevaEntity, HumanoidModel<SevaEntity>> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("friendsmod", "textures/entity/seva.png");

    public SevaRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(SevaEntity entity) {
        return TEXTURE;
    }
}
