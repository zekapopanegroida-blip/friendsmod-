package com.friendsmod.registry;

import com.friendsmod.entity.SevaEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

/**
 * Когда игрок ставит пластинку Севы в проигрыватель — вокруг спавнятся
 * временные мобы Севы, которые кидаются снежками/льдом в игрока, а потом исчезают.
 */
@Mod.EventBusSubscriber(modid = "friendsmod")
public class ModJukeboxEvents {

    @SubscribeEvent
    public static void onJukeboxInteract(PlayerInteractEvent.RightClickBlock event) {
        if (event.getLevel().isClientSide) return;
        if (!event.getItemStack().is(ModItems.SEVA_RECORD.get())) return;
        if (!event.getLevel().getBlockState(event.getPos()).is(net.minecraft.world.level.block.Blocks.JUKEBOX)) return;

        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        Player player = event.getEntity();

        int count = 3 + level.random.nextInt(3); // 3-5 мобов
        for (int i = 0; i < count; i++) {
            double angle = (Math.PI * 2 / count) * i;
            double x = pos.getX() + 0.5 + Math.cos(angle) * 4;
            double z = pos.getZ() + 0.5 + Math.sin(angle) * 4;

            SevaEntity seva = ModEntities.SEVA.get().create(level);
            if (seva == null) continue;
            seva.moveTo(x, pos.getY(), z, 0, 0);
            seva.markAsRecordSummoned(600); // живёт ~30 сек
            seva.setTarget(player);
            ((net.minecraft.server.level.ServerLevel) level).addFreshEntityWithPassengers(seva);
        }
    }
}
