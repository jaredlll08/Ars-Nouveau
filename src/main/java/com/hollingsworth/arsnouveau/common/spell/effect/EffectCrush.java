package com.hollingsworth.arsnouveau.common.spell.effect;

import com.hollingsworth.arsnouveau.ModConfig;
import com.hollingsworth.arsnouveau.api.spell.AbstractAugment;
import com.hollingsworth.arsnouveau.api.spell.AbstractEffect;
import com.hollingsworth.arsnouveau.api.spell.SpellContext;
import com.hollingsworth.arsnouveau.api.util.SpellUtil;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAOE;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.List;

public class EffectCrush extends AbstractEffect {
    public EffectCrush() {
        super(ModConfig.EffectCrushID, "Crush");
    }

    @Override
    public void onResolveBlock(BlockRayTraceResult rayTraceResult, World world, @Nullable LivingEntity shooter, List<AbstractAugment> augments, SpellContext spellContext) {
        for(BlockPos p : SpellUtil.calcAOEBlocks(shooter, rayTraceResult.getPos(), rayTraceResult, getBuffCount(augments, AugmentAOE.class))){
            BlockState state = world.getBlockState(p);
            if(state.getBlock().isIn(Tags.Blocks.COBBLESTONE) || state.getBlock().isIn(Tags.Blocks.STONE)){
                world.setBlockState(p, Blocks.GRAVEL.getDefaultState());
            }else if(state.getBlock().isIn(Tags.Blocks.GRAVEL)){
                world.setBlockState(p, Blocks.SAND.getDefaultState());
            }
        }

    }

    @Override
    public void onResolveEntity(EntityRayTraceResult rayTraceResult, World world, @Nullable LivingEntity shooter, List<AbstractAugment> augments, SpellContext spellContext) {
        if(!(rayTraceResult.getEntity() instanceof LivingEntity))
            return;
        LivingEntity livingEntity = (LivingEntity) rayTraceResult.getEntity();
        dealDamage(world, shooter, (livingEntity.isSwimming() ? 8.0f : 3.0f) + getAmplificationBonus(augments),augments, livingEntity, DamageSource.DROWN);
    }

    @Override
    protected String getBookDescription() {
        return "Turns stone into gravel, and gravel into sand. Will also harm entities and deals bonus damage to entities that are swimming.";
    }


    @Override
    public Item getCraftingReagent() {
        return Items.GRINDSTONE;
    }

    @Override
    public int getManaCost() {
        return 30;
    }

    @Override
    public Tier getTier() {
        return Tier.TWO;
    }
}
