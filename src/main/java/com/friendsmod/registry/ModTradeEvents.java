package com.friendsmod.registry;

import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.entity.player.TradeWithVillagerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Если у игрока в инвентаре есть телефон (BRAWL_PHONE) — цена покупки у жителей снижается.
 */
@Mod.EventBusSubscriber(modid = "friendsmod")
public class ModTradeEvents {

    @SubscribeEvent
    public static void onTrade(TradeWithVillagerEvent event) {
        var player = event.getEntity();
        if (player == null) return;
        if (!player.getInventory().contains(new net.minecraft.world.item.ItemStack(ModItems.BRAWL_PHONE.get()))) {
            return;
        }
        MerchantOffer offer = event.getMerchantOffer();
        // Снижаем цену первого товара на 30%, минимум 1
        var cost = offer.getCostA();
        int discounted = Math.max(1, (int) (cost.getCount() * 0.7));
        cost.setCount(discounted);
    }
}
