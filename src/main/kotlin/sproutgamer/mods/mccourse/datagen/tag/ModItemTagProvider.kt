package sproutgamer.mods.mccourse.datagen.tag

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.item.Items
import net.minecraft.registry.RegistryWrapper
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
    }

}
