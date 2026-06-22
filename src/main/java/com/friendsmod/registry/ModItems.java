package com.friendsmod.registry;

import com.friendsmod.FriendsMod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FriendsMod.MODID);

    // --- ЛЁША ---
    // Ложка-меч: урон 7, при попадании слепота+тошнота
    public static final RegistryObject<Item> LESHA_SPOON = ITEMS.register("lesha_spoon",
            () -> new SwordItem(ModTiers.SPOON_TIER, 4, -2.0f,
                    new Item.Properties()) {
                @Override
                public boolean hurtEnemy(net.minecraft.world.item.ItemStack stack,
                                          net.minecraft.world.entity.LivingEntity target,
                                          net.minecraft.world.entity.LivingEntity attacker) {
                    target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0));
                    target.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0));
                    return super.hurtEnemy(stack, target, attacker);
                }
            });

    public static final RegistryObject<Item> MULBERRY = ITEMS.register("mulberry",
            () -> new Item(new Item.Properties().food(
                    new FoodProperties.Builder()
                            .nutrition(8).saturationMod(1.0f).build())));

    public static final RegistryObject<Item> LESHA_CAP = ITEMS.register("lesha_cap",
            () -> new ArmorItem(ModArmorMaterials.LESHA_CAP, ArmorItem.Type.HELMET,
                    new Item.Properties()));

    // --- СЕВА ---
    public static final RegistryObject<Item> TRICK_SCOOTER = ITEMS.register("trick_scooter",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> BRAWL_PHONE = ITEMS.register("brawl_phone",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SEVA_RECORD = ITEMS.register("seva_record",
            () -> new RecordItem(13, ModSounds.SEVA_RECORD_SOUND, new Item.Properties().stacksTo(1), 200));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
