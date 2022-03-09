package lilypuree.thatched;

import lilypuree.thatched.core.DimensionalSpacingHelper;
import lilypuree.thatched.core.setup.ThatchedConfiguredFeatures;
import lilypuree.thatched.core.setup.ThatchedConfiguredStructures;
import lilypuree.thatched.core.setup.ThatchedStructures;
import lilypuree.thatched.core.world.features.VillageThatchedStructure;
import lilypuree.thatched.mixin.ChunkGeneratorAccessor;
import lilypuree.thatched.setup.ThatchedStructureForge;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Arrays;
import java.util.List;

@Mod(ThatchedConstants.MODID)
public class ThatchedMod implements DimensionalSpacingHelper {

    public ThatchedMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addGenericListener(StructureFeature.class, this::registerStructure);
        modEventBus.addListener(this::setup);

        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        forgeEventBus.addListener(this::addDimensionalSpacing);

        ThatchedConstants.config = new ThatchedConfigs();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ThatchedConfigs.COMMON_CONFIG);
    }

    public void registerStructure(RegistryEvent.Register<StructureFeature<?>> event) {
        ThatchedStructures.createStructuresAndConfigs();
        event.getRegistry().register(ThatchedStructures.THATCHED_VILLAGE.setRegistryName("thatched_village"));
    }

    /**
     * Run setupStructures after all structures are registered
     * <p>
     * Once after that structure instance is made, we then can now do the rest of the setup
     * that requires a structure instance such as setting the structure spacing, creating the
     * configured structure instance, and more.
     */
    public void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ThatchedStructureForge.setupStructuresForge();
            ThatchedConfiguredStructures.registerConfiguredStructures();
            ThatchedConfiguredFeatures.registerConfiguredFeatures();
        });
    }

    public void addDimensionalSpacing(final WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerLevel serverLevel) {
            ChunkGenerator chunkGenerator = serverLevel.getChunkSource().getGenerator();
            StructureSettings worldStructureConfig = chunkGenerator.getSettings();

            if (isFlatOverworld(serverLevel)) {
                return; //skip superflat worlds
            }

            //////////// BIOME BASED STRUCTURE SPAWNING ////////////
            /*
             * NOTE: BiomeLoadingEvent from Forge API does not work with structures anymore.
             * Instead, we will use the below to add our structure to overworld biomes.
             * Remember, this is temporary until Forge API finds a better solution for adding structures to biomes.
             */
            setConfiguredStructures(worldStructureConfig, getStructureBiomeMaps(serverLevel));

            //////////// DIMENSION BASED STRUCTURE SPAWNING ////////////
            if (isTerraforged(chunkGenerator)) return;

            setStructureConfigs(worldStructureConfig, serverLevel);
        }
    }

    @Override
    public boolean isBiomeAssociated(Biome biome) {
        Biome.BiomeCategory category = biome.getBiomeCategory();
        return category == Biome.BiomeCategory.PLAINS;
    }

    @Override
    public boolean isBlackListed(ServerLevel serverLevel) {
        List<String> dimensionBlacklist = Arrays.stream(ThatchedConstants.config.blacklistedDimensions().split(",")).map(String::trim).toList();
        String levelName = serverLevel.dimension().getRegistryName().toString();
        return dimensionBlacklist.stream().anyMatch(blackList -> blackList.equals(levelName)) ||
                serverLevel.dimension().equals(Level.OVERWORLD) && serverLevel.getChunkSource().getGenerator() instanceof FlatLevelSource;
    }

    /**
     * Skip Terraforged's chunk generator as they are a special case of a mod locking down their chunkgenerator.
     * They will handle your structure spacing for your if you add to Registry.NOISE_GENERATOR_SETTINGS_REGISTRY in your structure's registration.
     *
     * @param chunkGenerator
     * @return
     */
    private static boolean isTerraforged(ChunkGenerator chunkGenerator) {
        ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey(((ChunkGeneratorAccessor) chunkGenerator).invokeCodec());
        return (cgRL != null && cgRL.getNamespace().equals("terraforged"));
    }
}
