package com.friendsmod;

import com.friendsmod.registry.ModEntities;
import com.friendsmod.registry.ModItems;
import com.friendsmod.registry.ModSounds;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraft.world.entity.monster.Monster;

@Mod(FriendsMod.MODID)
public class FriendsMod {
    public static final String MODID = "friendsmod";

    public FriendsMod() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modBus);
        ModEntities.register(modBus);
        ModSounds.register(modBus);
        com.friendsmod.registry.ModCreativeTab.register(modBus);

        modBus.addListener(this::onAttributeCreate);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(ModEntities.LESHA.get(),
                com.friendsmod.entity.LeshaEntity.createAttributes().build());
        event.put(ModEntities.SEVA.get(),
                com.friendsmod.entity.SevaEntity.createAttributes().build());

        SpawnPlacements.register(ModEntities.SEVA.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster.checkAnyLightMonsterSpawnRules);
    }
}
