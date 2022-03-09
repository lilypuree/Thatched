package lilypuree.thatched;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;

import java.util.function.Predicate;

public class BiomeInjection {

    public static void addStructure(ConfiguredStructureFeature<?, ?> configuredStructureFeature, Predicate<BiomeSelectionContext> check) {
        BiomeModifications.create(Registry.STRUCTURE_FEATURE.getKey(configuredStructureFeature.feature)).add(
                ModificationPhase.ADDITIONS,
                check,
                ctx -> ctx.getGenerationSettings().addStructure(
                        ResourceKey.create(
                                Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY,
                                Registry.STRUCTURE_FEATURE.getKey(configuredStructureFeature.feature)
                        )
                )
        );
    }
}
