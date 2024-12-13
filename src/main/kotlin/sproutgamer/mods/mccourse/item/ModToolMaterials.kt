package sproutgamer.mods.mccourse.item

import com.google.common.base.Suppliers
import net.minecraft.block.Block
import net.minecraft.item.ToolMaterial
import net.minecraft.item.ToolMaterials
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.tag.TagKey
import com.google.common.base.Supplier
import sproutgamer.mods.mccourse.tag.ModTags

enum class ModToolMaterials(
    private val inverseTag: TagKey<Block>, private val itemDurability: Int, private val miningSpeed: Float,
    private val attackDamage: Float,
    private val enchantability: Int, repairIngredient: Supplier<Ingredient>) : ToolMaterial {

        FLUORITE(ModTags.Blocks.INCORRECT_FOR_FLUORITE_TOOL, 2031, 9f, 4f, 15, { Ingredient.ofItems(ModItems.FLUORITE) });

        private val repairIngredient = Suppliers.memoize(repairIngredient)

        override fun getDurability(): Int {
            return itemDurability
        }

        override fun getMiningSpeedMultiplier(): Float {
            return miningSpeed
        }

        override fun getAttackDamage(): Float {
            return attackDamage
        }

        override fun getInverseTag(): TagKey<Block>? {
            return inverseTag
        }

        override fun getEnchantability(): Int {
            return enchantability
        }

        override fun getRepairIngredient(): Ingredient {
            return repairIngredient.get()
        }
}
