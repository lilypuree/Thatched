package lilypuree.thatched.core.setup;

import lilypuree.thatched.ThatchedConstants;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ThatchedConfiguredFeatures {

    public static ConfiguredFeature<?, ?> LILY_OF_THE_VALLEY_FEATURE = Feature.NO_BONEMEAL_FLOWER.configured(FeatureUtils.simpleRandomPatchConfiguration(64, Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.LILY_OF_THE_VALLEY))).onlyWhenEmpty()));
    public static final PlacedFeature LILY_OF_THE_VALLEY_PLACED = PlacementUtils.register(ThatchedConstants.MODID+ ":lily_of_the_valley.json", LILY_OF_THE_VALLEY_FEATURE.placed());


    public static void registerConfiguredFeatures() {
        Registry<ConfiguredFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_FEATURE;
        Registry.register(registry, new ResourceLocation(ThatchedConstants.MODID, "lily_of_the_valley.json"), LILY_OF_THE_VALLEY_FEATURE);
    }
}
