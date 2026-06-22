package com.friendsmod.registry;

import com.friendsmod.FriendsMod;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(ForgeRegistries.CREATIVE_MODE_TABS, FriendsMod.MODID);

    public static final RegistryObject<CreativeModeTab> FRIENDS_TAB = TABS.register("friendsmod_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.friendsmod"))
                    .icon(() -> new ItemStack(ModItems.LESHA_SPOON.get()))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.LESHA_SPOON.get());
                        output.accept(ModItems.MULBERRY.get());
                        output.accept(ModItems.LESHA_CAP.get());
                        output.accept(ModItems.TRICK_SCOOTER.get());
                        output.accept(ModItems.BRAWL_PHONE.get());
                        output.accept(ModItems.SEVA_RECORD.get());
                    })
                    .build());

    public static void register(IEventBus bus) {
        TABS.register(bus);
    }
}
