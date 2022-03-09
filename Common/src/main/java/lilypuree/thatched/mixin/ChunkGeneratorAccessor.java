package lilypuree.thatched.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.StructureSettings;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ChunkGenerator.class)
public interface ChunkGeneratorAccessor {

    @Invoker("codec")
    public abstract Codec<? extends ChunkGenerator> invokeCodec();

    @Mutable
    @Final
    @Accessor("settings")
    public abstract void setSettings(StructureSettings settings);
}
