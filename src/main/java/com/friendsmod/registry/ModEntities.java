package com.friendsmod.registry;

import com.friendsmod.FriendsMod;
import com.friendsmod.entity.LeshaEntity;
import com.friendsmod.entity.SevaEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FriendsMod.MODID);

    public static final RegistryObject<EntityType<LeshaEntity>> LESHA = ENTITIES.register("lesha",
            () -> EntityType.Builder.of(LeshaEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.9f)
                    .build("lesha"));

    public static final RegistryObject<EntityType<SevaEntity>> SEVA = ENTITIES.register("seva",
            () -> EntityType.Builder.of(SevaEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 2.0f)
                    .build("seva"));

    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }
}
