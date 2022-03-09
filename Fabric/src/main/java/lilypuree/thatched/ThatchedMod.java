package lilypuree.thatched;


import lilypuree.thatched.compat.FabricConfigs;
import lilypuree.thatched.core.DimensionalSpacingHelper;
import lilypuree.thatched.core.setup.ThatchedConfiguredFeatures;
import lilypuree.thatched.core.setup.ThatchedConfiguredStructures;
import lilypuree.thatched.setup.ThatchedStructuresFabric;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.StructureSettings;

public class ThatchedMod implements ModInitializer, DimensionalSpacingHelper {

    @Override
    public void onInitialize() {
        AutoConfig.register(FabricConfigs.class, JanksonConfigSerializer::new);
        ThatchedConstants.config = AutoConfig.getConfigHolder(FabricConfigs.class).getConfig();

        ThatchedStructuresFabric.setupAndRegisterStructuresFabric();
        ThatchedConfiguredFeatures.registerConfiguredFeatures();
        ThatchedConfiguredStructures.registerConfiguredStructures();


        BiomeInjection.addStructure(ThatchedConfiguredStructures.CONFIGURED_THATCHED_VILLAGE, event ->
                ThatchedConstants.config.getThatchedVillageMaxChunkDistance() != 1001 &&
                        event.getBiome().getBiomeCategory() == Biome.BiomeCategory.PLAINS);

        // This is for making sure our ServerWorldEvents.LOAD event always fires after Fabric API's usage of the same event.
        // This is done so our changes don't get overwritten by Fabric API adding structure spacings to all dimensions.
        // Requires Fabric API v0.42.0  or newer.
        ResourceLocation runAfterFabricAPIPhase = new ResourceLocation(ThatchedConstants.MODID, "run_after_fabric_api");
        ServerWorldEvents.LOAD.addPhaseOrdering(Event.DEFAULT_PHASE, runAfterFabricAPIPhase);
        ServerWorldEvents.LOAD.register(runAfterFabricAPIPhase, (MinecraftServer server, ServerLevel serverLevel) -> {
            StructureSettings structureSettings = serverLevel.getChunkSource().getGenerator().getSettings();
            setStructureConfigs(structureSettings, serverLevel);
        });
    }

    @Override
    public boolean isBlackListed(ServerLevel serverLevel) {
        return !serverLevel.dimension().equals(Level.OVERWORLD);
    }

    @Override
    public boolean isBiomeAssociated(Biome biome) {
        return false;
    }
}
