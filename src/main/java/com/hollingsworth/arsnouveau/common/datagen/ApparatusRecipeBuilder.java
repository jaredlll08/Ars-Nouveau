package com.hollingsworth.arsnouveau.common.datagen;

import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.api.enchanting_apparatus.EnchantingApparatusRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;

public class ApparatusRecipeBuilder {
    EnchantingApparatusRecipe recipe;
    public ApparatusRecipeBuilder(){
        this.recipe = new EnchantingApparatusRecipe();
    }

    public static ApparatusRecipeBuilder builder(){
        return new ApparatusRecipeBuilder();
    }
    public ApparatusRecipeBuilder withResult(IItemProvider result){
        this.recipe.result = new ItemStack(result);
        return this;
    }
    public ApparatusRecipeBuilder withResult(ItemStack result){
        this.recipe.result = result;
        return this;
    }

    public ApparatusRecipeBuilder withCategory(ArsNouveauAPI.PatchouliCategories category){
        this.recipe.category = category.name();
        return this;
    }
    public ApparatusRecipeBuilder withReagent(IItemProvider provider){
        this.recipe.reagent = Ingredient.fromItems(provider);
        return this;
    }

    public ApparatusRecipeBuilder withReagent(Ingredient ingredient){
        this.recipe.reagent = ingredient;
        return this;
    }

    public ApparatusRecipeBuilder withPedestalItem(Ingredient i){
        this.recipe.pedestalItems.add(i);
        return this;
    }

    public ApparatusRecipeBuilder withPedestalItem(Item i){
        return this.withPedestalItem(Ingredient.fromItems(i));
    }

    public ApparatusRecipeBuilder withPedestalItem(int count, Item item){
        for(int i = 0; i < count; i++)
            this.withPedestalItem(item);
        return this;
    }

    public ApparatusRecipeBuilder withPedestalItem(int count, Ingredient ingred){
        for(int i = 0; i < count; i++)
            this.withPedestalItem(ingred);
        return this;
    }

    public EnchantingApparatusRecipe build(){
        return recipe;
    }

}
