package lilypuree.thatched.core.setup;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lilypuree.thatched.CommonConfig;
import lilypuree.thatched.ThatchedConstants;
import lilypuree.thatched.core.world.features.VillageThatchedStructure;
import lilypuree.thatched.mixin.StructureFeatureAccessor;
import lilypuree.thatched.mixin.StructureSettingsAccessor;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * Structures need to be registered separately since forge adds a ForgeRegistry for StructureFeatures
 */
public class ThatchedStructures {
    //Villages
    public static StructureFeature<JigsawConfiguration> THATCHED_VILLAGE;
    public static StructureFeatureConfiguration THATCHED_CONFIGURATION;

    public static void createStructuresAndConfigs() {
        THATCHED_VILLAGE = new VillageThatchedStructure(JigsawConfiguration.CODEC);
        int i = ThatchedConstants.config.getThatchedVillageMaxChunkDistance();
        THATCHED_CONFIGURATION = new StructureFeatureConfiguration(i, (int) (i * 0.5f), 1102167365);
    }

    /**
     * This is where we set the rarity of your structures and determine if land conforms to it.
     * See the comments in below for more details.
     * Structures need to be registered into the STRUCTURES_REGISTRY separately.
     */
    public static void setupStructures() {
        setupMapSpacingAndLand(THATCHED_VILLAGE, THATCHED_CONFIGURATION, true);
    }

    /**
     * Adds the separation settings.
     * The rarity of the structure is determined based on the values passed into
     * this method in the StructureFeatureConfiguration argument.
     */
    public static <F extends StructureFeature<?>> void setupMapSpacingAndLand(
            F structure,
            StructureFeatureConfiguration structureFeatureConfiguration,
            boolean transformSurroundingLand) {
        /*
         * We need to add our structures into the map in StructureFeature class
         * alongside vanilla structures or else it will cause errors.
         *
         * If the registration is setup properly for the structure,
         * getRegistryName() should never return null.
         */
//        StructureFeature.STRUCTURES_REGISTRY.put(structure.toString().toString(), structure);

        if (transformSurroundingLand) {
            StructureFeatureAccessor.setNoiseAffectingFeatures(
                    ImmutableList.<StructureFeature<?>>builder()
                            .addAll(StructureFeature.NOISE_AFFECTING_FEATURES)
                            .add(structure)
                            .build());
        }

        StructureSettingsAccessor.setDefaults(ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder()
                .putAll(StructureSettings.DEFAULTS)
                .put(structure, structureFeatureConfiguration)
                .build());


        BuiltinRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
            Map<StructureFeature<?>, StructureFeatureConfiguration> structureMap = settings.getValue().structureSettings().structureConfig();

            if (structureMap instanceof ImmutableMap) {
                Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, structureFeatureConfiguration);

                ((StructureSettingsAccessor) settings.getValue().structureSettings()).setStructureConfigs(tempMap);
            } else {
                structureMap.put(structure, structureFeatureConfiguration);
            }
        });
    }
}
