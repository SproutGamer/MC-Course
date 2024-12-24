package sproutgamer.mods.mccourse.datagen.tag

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.item.Items
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.ItemTags
import sproutgamer.mods.mccourse.item.ModItems
import sproutgamer.mods.mccourse.tag.ModTags
import java.util.concurrent.CompletableFuture

class ModItemTagProvider(output: FabricDataOutput, completableFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) :
    FabricTagProvider.ItemTagProvider(output, completableFuture) {

    override fun configure(wrapperLookup: RegistryWrapper.WrapperLookup?) {
        getOrCreateTagBuilder(ModTags.Items.TRANSFORMABLE_ITEMS)
            .add(ModItems.FLUORITE,
                ModItems.RAW_FLUORITE,
                Items.COAL,
                Items.STICK)

        getOrCreateTagBuilder(ModTags.Items.FLUORITE_REPAIR)
            .add(ModItems.FLUORITE)

        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
            .add(ModItems.FLUORITE_HELMET)
        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
            .add(ModItems.FLUORITE_CHESTPLATE)
        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
            .add(ModItems.FLUORITE_LEGGINGS)
        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
            .add(ModItems.FLUORITE_BOOTS)

        getOrCreateTagBuilder(ItemTags.TRIM_MATERIALS)
            .add(ModItems.FLUORITE)

    }

    override fun getName(): String {
        return "MC Course Item Tag Provider"
    }

}
