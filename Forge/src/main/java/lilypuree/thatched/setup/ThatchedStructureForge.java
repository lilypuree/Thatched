package lilypuree.thatched.setup;

import lilypuree.thatched.core.setup.ThatchedStructures;
import net.minecraft.world.level.levelgen.feature.StructureFeature;

public class ThatchedStructureForge {

    public static void setupStructuresForge() {
        ThatchedStructures.setupStructures();
        StructureFeature.STRUCTURES_REGISTRY.put(ThatchedStructures.THATCHED_VILLAGE.getRegistryName().toString(), ThatchedStructures.THATCHED_VILLAGE);
    }
}
