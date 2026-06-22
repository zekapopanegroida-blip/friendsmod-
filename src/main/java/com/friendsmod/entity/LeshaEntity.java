package com.friendsmod.entity;

import com.friendsmod.registry.ModItems;
import com.friendsmod.registry.ModSounds;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;

/**
 * Лёша — дружелюбный моб. Бродит, иногда "ест" Украинской ложкой.
 * Если игрок его ударит — становится злым (HOSTILE) и атакует ложкой.
 */
public class LeshaEntity extends PathfinderMob implements NeutralMob {

    private static final EntityDataAccessor<Boolean> ANGRY =
            SynchedEntityData.defineId(LeshaEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> EATING =
            SynchedEntityData.defineId(LeshaEntity.class, EntityDataSerializers.BOOLEAN);

    private int remainingPersistentAngerTime;

    public LeshaEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.FOLLOW_RANGE, 24.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ANGRY, false);
        this.entityData.define(EATING, false);
    }

    @Override
    protected void registerGoals() {
        // Когда зол - гонится и бьёт ложкой как обычный враждебный моб
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, true) {
            @Override
            public boolean canUse() {
                return isAngry() && super.canUse();
            }
        });
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true,
                this::isAngry));
    }

    public boolean isAngry() {
        return this.entityData.get(ANGRY);
    }

    public void setAngry(boolean angry) {
        this.entityData.set(ANGRY, angry);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean result = super.hurt(source, amount);
        if (result && source.getEntity() instanceof Player) {
            setAngry(true);
            this.playSound(ModSounds.LESHA_HURT.get(), 1.0f, 1.0f);
        }
        return result;
    }

    @Override
    public void tick() {
        super.tick();
        // Случайная "анимация еды" ложкой когда спокоен
        if (!isAngry() && this.level().isClientSide && this.random.nextInt(400) == 0) {
            this.entityData.set(EATING, true);
        }
        // Периодически бубнит фразы
        if (!this.level().isClientSide && this.random.nextInt(600) == 0) {
            this.playSound(ModSounds.LESHA_AMBIENT.get(), 1.0f, 1.0f);
        }
    }

    public boolean isEating() {
        return this.entityData.get(EATING);
    }

    @Override
    public net.minecraft.world.level.storage.loot.BuiltInLootTables getLootTable() {
        // Используем кастомный loot table: friendsmod:entities/lesha
        return null;
    }

    @Override
    protected net.minecraft.resources.ResourceLocation getDefaultLootTable() {
        return new net.minecraft.resources.ResourceLocation("friendsmod", "entities/lesha");
    }

    // --- NeutralMob interface (для системы агро как у Pillager/Wolf) ---
    @Override public int getRemainingPersistentAngerTime() { return remainingPersistentAngerTime; }
    @Override public void setRemainingPersistentAngerTime(int time) { this.remainingPersistentAngerTime = time; }
    @Override public void setPersistentAngerTarget(java.util.UUID uuid) {}
    @Override public java.util.UUID getPersistentAngerTarget() { return null; }
    @Override public void startPersistentAngerTimer() { setRemainingPersistentAngerTime(400); }
}
