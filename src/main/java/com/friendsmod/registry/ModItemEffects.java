package com.friendsmod.registry;

import com.friendsmod.entity.SevaEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Логика "в руке держит самокат/телефон" — баффы и эффект призыва Сев.
 * Регистрируется на общую шину Forge (см. FriendsMod конструктор).
 */
@Mod.EventBusSubscriber(modid = "friendsmod")
public class ModItemEffects {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;
        ItemStack main = player.getMainHandItem();
        ItemStack off = player.getOffhandItem();

        boolean holdingScooter = main.is(ModItems.TRICK_SCOOTER.get()) || off.is(ModItems.TRICK_SCOOTER.get());
        boolean holdingPhone = main.is(ModItems.BRAWL_PHONE.get()) || off.is(ModItems.BRAWL_PHONE.get());

        if (holdingScooter) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 1, false, false));
            // "Дурное севыно знамение": каждые ~5 сек агрим всех Сев в большом радиусе на игрока
            if (player.tickCount % 100 == 0 && !player.level().isClientSide) {
                player.level().getEntitiesOfClass(SevaEntity.class, player.getBoundingBox().inflate(48))
                        .forEach(seva -> seva.setTarget(player));
            }
        }

        if (holdingPhone) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 0, false, false));
            // Скидки у жителей реализуются через TradeEvent (см. ModTradeEvents) при наличии телефона в инвентаре
        }
    }
}
