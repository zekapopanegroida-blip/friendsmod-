package com.friendsmod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.level.Level;
import com.friendsmod.registry.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.HitResult;

/**
 * Телефон, кинутый игроком — взрывается при попадании (как яйцо, но с уроном по области).
 */
public class PhoneProjectile extends ThrowableItemProjectile {

    public PhoneProjectile(EntityType<? extends PhoneProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.BRAWL_PHONE.get();
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(),
                    1.5f, net.minecraft.world.level.Level.ExplosionInteraction.NONE);
            this.discard();
        }
    }
}
