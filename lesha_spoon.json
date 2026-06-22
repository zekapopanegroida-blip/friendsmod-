package com.friendsmod.entity;

import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * Сева — враждебный моб. Спавнится на равнинах.
 * Атакует жителей и игрока. Иногда впадает в "бешеный" режим (быстрый хаотичный бег),
 * иногда просто стоит/втыкает.
 */
public class SevaEntity extends Monster {

    private int crazyTimer = 0;
    private boolean isCrazy = false;
    private boolean recordSummoned = false;
    private int despawnTimer = -1; // только для призванных пластинкой

    public SevaEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public boolean isRecordSummoned() {
        return recordSummoned;
    }

    /** Помечает моба как временного, призванного пластинкой: будет кидаться снежками и исчезнет через time тиков. */
    public void markAsRecordSummoned(int lifeTicks) {
        this.recordSummoned = true;
        this.despawnTimer = lifeTicks;
        this.goalSelector.addGoal(0, new SevaThrowSnowGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 26.0)
                .add(Attributes.MOVEMENT_SPEED, 0.27)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.FOLLOW_RANGE, 32.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.3, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Villager.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            crazyTimer--;
            if (crazyTimer <= 0) {
                isCrazy = this.random.nextBoolean();
                crazyTimer = 200 + this.random.nextInt(200);
                this.getAttribute(Attributes.MOVEMENT_SPEED)
                        .setBaseValue(isCrazy ? 0.45 : 0.27);
            }

            if (recordSummoned && despawnTimer >= 0) {
                despawnTimer--;
                if (despawnTimer <= 0) {
                    this.discard();
                }
            }
        }
    }

    public boolean isCrazy() {
        return isCrazy;
    }

    @Override
    protected net.minecraft.resources.ResourceLocation getDefaultLootTable() {
        return new net.minecraft.resources.ResourceLocation("friendsmod", "entities/seva");
    }
}
