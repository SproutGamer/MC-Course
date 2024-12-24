package sproutgamer.mods.mccourse.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLootTableProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class ModRegistryDataProvider(output: FabricDataOutput?,
                              registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>?
) : FabricDynamicRegistryProvider(output, registriesFuture) {

    override fun getName(): String {
        return "MC Course Registry Data Provider"
    }

    override fun configure(registries: RegistryWrapper.WrapperLookup?, entries: Entries?) {
        if (registries != null && entries != null) {
            entries.addAll(registries.getOrThrow(RegistryKeys.TRIM_MATERIAL))
            entries.addAll(registries.getOrThrow(RegistryKeys.TRIM_PATTERN))
        }
    }
}