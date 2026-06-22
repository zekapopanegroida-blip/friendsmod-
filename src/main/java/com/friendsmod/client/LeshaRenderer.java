package com.friendsmod.client;

import com.friendsmod.entity.LeshaEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;

public class LeshaRenderer extends HumanoidMobRenderer<LeshaEntity, HumanoidModel<LeshaEntity>> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("friendsmod", "textures/entity/lesha.png");

    public LeshaRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(LeshaEntity entity) {
        return TEXTURE;
    }
}
