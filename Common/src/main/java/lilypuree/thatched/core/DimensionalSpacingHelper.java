package lilypuree.thatched.core;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import lilypuree.thatched.core.setup.ThatchedConfiguredStructures;
import lilypuree.thatched.core.setup.ThatchedStructures;
import lilypuree.thatched.mixin.StructureSettingsAccessor;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;

import java.util.*;
import java.util.stream.Collectors;

public interface DimensionalSpacingHelper {

    boolean isBlackListed(ServerLevel serverLevel);

    boolean isBiomeAssociated(Biome biome);

    default HashMap<StructureFeature<?>, Multimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> getStructureBiomeMaps(ServerLevel serverLevel) {
        HashMap<StructureFeature<?>, Multimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> tempStructToBiomeMap = new HashMap<>();

        // Add the resourcekey of all biomes that this Configured Structure can spawn in.
        for (Map.Entry<ResourceKey<Biome>, Biome> biomeEntry : serverLevel.registryAccess().ownedRegistryOrThrow(Registry.BIOME_REGISTRY).entrySet()) {
            // Skip all ocean, end, nether, and none category biomes.
            // You can do checks for other traits that the biome has.

            if (isBiomeAssociated(biomeEntry.getValue())) {
                associateBiomeToConfiguredStructure(tempStructToBiomeMap, ThatchedConfiguredStructures.CONFIGURED_THATCHED_VILLAGE, biomeEntry.getKey());
            }
        }
        return tempStructToBiomeMap;
    }


    /**
     * Helper method that handles setting up the map to multimap relationship to help prevent issues.
     */
    default void associateBiomeToConfiguredStructure(Map<StructureFeature<?>, Multimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> structToMultMap, ConfiguredStructureFeature<?, ?> configuredStructureFeature, ResourceKey<Biome> key) {
        structToMultMap.putIfAbsent(configuredStructureFeature.feature, HashMultimap.create());
        Multimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> biomeMap = structToMultMap.get(configuredStructureFeature.feature);
        if (!biomeMap.containsValue(key)) {
            biomeMap.put(configuredStructureFeature, key);
        }
    }

    /**
     * Makes sure this chunkgenerator and datapack dimensions can spawn our structure.
     * putIfAbsent so people can override the spacing with dimension datapacks themselves if they wish to customize spacing more precisely per dimension.
     */
    default void setStructureConfigs(StructureSettings structureSettings, ServerLevel serverLevel) {
        Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(structureSettings.structureConfig());

        Set<Biome> possibleBiomes = serverLevel.getChunkSource().getGenerator().getBiomeSource().possibleBiomes();
        var validBiomes = validBiomesForStructure(serverLevel);

        if (isBlackListed(serverLevel) || validBiomes.stream().noneMatch(possibleBiomes::contains)) {
            tempMap.remove(ThatchedStructures.THATCHED_VILLAGE);
        } else {
            tempMap.putIfAbsent(ThatchedStructures.THATCHED_VILLAGE, ThatchedStructures.THATCHED_CONFIGURATION);
        }
        ((StructureSettingsAccessor) structureSettings).setStructureConfigs(tempMap);
    }

    default Set<Biome> validBiomesForStructure(ServerLevel serverLevel) {
        Registry<Biome> biomeRegistry = serverLevel.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY);

        // Remove structure spacing config if biome source cannot spawn biome.
        // Should help optimize the game to skip checking these structures as MC doesn't seem to do it by default? Weird.
        return serverLevel.getChunkSource().getGenerator().getSettings()
                .structures(ThatchedStructures.THATCHED_VILLAGE).values().stream().map(biomeRegistry::get).
                collect(Collectors.toSet());
    }

    default void setConfiguredStructures(StructureSettings structureSettings, HashMap<StructureFeature<?>, Multimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> map) {
        // Grab the map that holds what ConfigureStructures a structure has and what biomes it can spawn in.
        ImmutableMap.Builder<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> immutableMapBuilder = ImmutableMap.builder();
        ((StructureSettingsAccessor) structureSettings).getConfiguredStructures().entrySet().stream()
                .filter(entry -> !map.containsKey(entry.getKey()))
                .forEach(immutableMapBuilder::put);

        // Add our structures to the structure map/multimap and set the world to use this combined map/multimap.
        map.forEach((key, value) -> immutableMapBuilder.put(key, ImmutableMultimap.copyOf(value)));

        ((StructureSettingsAccessor) structureSettings).setConfiguredStructures(immutableMapBuilder.build());
    }

    default boolean isFlatOverworld(ServerLevel serverLevel) {
        return (serverLevel.getChunkSource().getGenerator() instanceof FlatLevelSource && serverLevel.dimension().equals(Level.OVERWORLD));
    }
}
