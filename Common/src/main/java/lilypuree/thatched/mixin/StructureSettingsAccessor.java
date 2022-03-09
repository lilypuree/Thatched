package lilypuree.thatched.mixin;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(StructureSettings.class)
public interface StructureSettingsAccessor {

    @Mutable
    @Accessor(value = "DEFAULTS")
    public static void setDefaults(ImmutableMap<StructureFeature<?>, StructureFeatureConfiguration> value) {
    }

    @Mutable
    @Accessor(value = "structureConfig")
    public void setStructureConfigs(Map<StructureFeature<?>, StructureFeatureConfiguration> configs);

    @Mutable
    @Accessor(value = "configuredStructures")
    public void setConfiguredStructures(ImmutableMap<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> configuredStructures);

    @Accessor(value = "configuredStructures")
    public ImmutableMap<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> getConfiguredStructures();
}
