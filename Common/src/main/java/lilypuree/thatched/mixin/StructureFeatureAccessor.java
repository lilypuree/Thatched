package lilypuree.thatched.mixin;

import net.minecraft.world.level.levelgen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(StructureFeature.class)
public interface StructureFeatureAccessor {


    @Mutable
    @Accessor(value = "NOISE_AFFECTING_FEATURES")
    public static void setNoiseAffectingFeatures(List<StructureFeature<?>> list) {
    }
}
