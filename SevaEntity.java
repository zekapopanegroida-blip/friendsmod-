package com.friendsmod.registry;

import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.function.Supplier;

public class ModArmorMaterials {
    public static final ArmorMaterial LESHA_CAP = new ArmorMaterial() {
        @Override public int getDurabilityForType(EquipmentSlot slot) { return 50; }
        @Override public int getDefenseForType(EquipmentSlot slot) {
            // только шлем: +3 брони, остальное 0 (кепка - только головной убор)
            return slot == EquipmentSlot.HEAD ? 3 : 0;
        }
        @Override public int getEnchantmentValue() { return 9; }
        @Override public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_LEATHER; }
        @Override public net.minecraft.world.item.crafting.Ingredient getRepairIngredient() {
            return Ingredient.EMPTY;
        }
        @Override public String getName() { return "friendsmod:lesha_cap"; }
        @Override public float getToughness() { return 0f; }
        @Override public float getKnockbackResistance() { return 0f; }
    };
}
