//package lilypuree.thatched.mixin;
//
//import lilypuree.thatched.NoiseSettingsDeepCopier;
//import net.minecraft.resources.ResourceKey;
//import net.minecraft.server.MinecraftServer;
//import net.minecraft.server.level.ServerChunkCache;
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.server.level.progress.ChunkProgressListener;
//import net.minecraft.world.level.CustomSpawner;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.chunk.ChunkGenerator;
//import net.minecraft.world.level.chunk.ChunkSource;
//import net.minecraft.world.level.dimension.DimensionType;
//import net.minecraft.world.level.levelgen.StructureSettings;
//import net.minecraft.world.level.storage.LevelStorageSource;
//import net.minecraft.world.level.storage.ServerLevelData;
//import org.lwjgl.system.CallbackI;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import java.util.List;
//import java.util.concurrent.Executor;
//
//@Mixin(ServerLevel.class)
//public abstract class ServerLevelMixin {
//
//
//    @Shadow
//    public abstract ServerChunkCache getChunkSource();
//
//    @Inject(method = "<init>", at = @At("RETURN"))
//    private void thatched_deepCopyNoiseSettings(MinecraftServer minecraftServer, Executor executor, LevelStorageSource.LevelStorageAccess levelStorageAccess, ServerLevelData serverLevelData, ResourceKey<Level> resourceKey, DimensionType dimensionType, ChunkProgressListener chunkProgressListener, ChunkGenerator chunkGenerator, boolean bl, long l, List<CustomSpawner> list, boolean bl2, CallbackInfo ci) {
//        StructureSettings structureSettings = NoiseSettingsDeepCopier.deepCopyDimensionStructuresSettings(this.getChunkSource().getGenerator().getSettings());
//        ((ChunkGeneratorAccessor) getChunkSource().getGenerator()).setSettings(structureSettings);
//    }
//}
