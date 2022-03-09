package lilypuree.thatched.setup;

import lilypuree.thatched.ThatchedConstants;
import lilypuree.thatched.core.setup.ThatchedStructures;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ThatchedStructuresFabric {
    public static void setupAndRegisterStructuresFabric() {
        ThatchedStructures.createStructuresAndConfigs();
        FabricStructureBuilder.create(ThatchedConstants.STRUCTURE_NAME, ThatchedStructures.THATCHED_VILLAGE)
                .step(GenerationStep.Decoration.SURFACE_STRUCTURES)
                .defaultConfig(ThatchedStructures.THATCHED_CONFIGURATION)
                .adjustsSurface()
                .register();
    }
}
