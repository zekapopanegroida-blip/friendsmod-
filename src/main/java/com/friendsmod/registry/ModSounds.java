package com.friendsmod.registry;

import com.friendsmod.FriendsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FriendsMod.MODID);

    public static final RegistryObject<SoundEvent> LESHA_AMBIENT = SOUNDS.register("lesha_ambient",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FriendsMod.MODID, "lesha_ambient")));

    public static final RegistryObject<SoundEvent> LESHA_HURT = SOUNDS.register("lesha_hurt",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FriendsMod.MODID, "lesha_hurt")));

    public static final RegistryObject<SoundEvent> SEVA_RECORD_SOUND = SOUNDS.register("seva_record",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FriendsMod.MODID, "seva_record")));

    public static void register(IEventBus bus) {
        SOUNDS.register(bus);
    }
}
