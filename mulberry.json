package com.friendsmod.entity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.EnumSet;

/**
 * Используется только "призрачными" Сева, заспавненными пластинкой —
 * вместо обычной ближней атаки кидает снежки/лёд в игрока.
 */
public class SevaThrowSnowGoal extends Goal {
    private final SevaEntity seva;
    private int cooldown = 0;

    public SevaThrowSnowGoal(SevaEntity seva) {
        this.seva = seva;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return seva.isRecordSummoned() && seva.getTarget() != null;
    }

    @Override
    public void tick() {
        LivingEntity target = seva.getTarget();
        if (target == null) return;
        seva.getLookControl().setLookAt(target, 30f, 30f);

        if (--cooldown <= 0) {
            cooldown = 30; // раз в 1.5 сек
            Snowball snowball = new Snowball(seva.level(), seva);
            double dx = target.getX() - seva.getX();
            double dy = target.getEyeY() - seva.getEyeY();
            double dz = target.getZ() - seva.getZ();
            snowball.shoot(dx, dy + 0.2, dz, 1.6f, 6.0f);
            seva.level().addFreshEntity(snowball);
        }
    }
}
