//package lilypuree.thatched.core.compat;
//
//import com.lilypuree.decorative_blocks.fluid.ThatchFluid;
//import com.lilypuree.decorative_blocks.fluid.ThatchFluidBlock;
//import com.lilypuree.thatched.Registration;
//import com.minecraftabnormals.environmental.core.Environmental;
//import com.minecraftabnormals.environmental.core.registry.EnvironmentalBlocks;
//import net.minecraft.client.renderer.ItemBlockRenderTypes;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.material.FlowingFluid;
//import net.minecraft.world.level.material.Fluid;
//import net.minecraftforge.fml.RegistryObject;
//import net.minecraftforge.registries.DeferredRegister;
//
//import java.util.Collection;
//
//
//public class EnvironmentalCompat {
//
//    public static ThatchFluid.FluidReferenceHolder grassThatch;
//    public static ThatchFluid.FluidReferenceHolder cattailThatch;
//    public static ThatchFluid.FluidReferenceHolder duckweedThatch;
//    public static ThatchFluid.FluidReferenceHolder yakHairFluid;
//    public static RegistryObject<FlowingFluid> GRASS_THATCH_STILL;
//    public static RegistryObject<FlowingFluid> GRASS_THATCH_FLOWING;
//    public static RegistryObject<Block> GRASS_THATCH_BLOCK;
//    public static RegistryObject<FlowingFluid> CATTAIL_THATCH_STILL;
//    public static RegistryObject<FlowingFluid> CATTAIL_THATCH_FLOWING;
//    public static RegistryObject<Block> CATTAIL_THATCH_BLOCK;
//    public static RegistryObject<FlowingFluid> DUCKWEED_THATCH_STILL;
//    public static RegistryObject<FlowingFluid> DUCKWEED_THATCH_FLOWING;
//    public static RegistryObject<Block> DUCKWEED_THATCH_BLOCK;
//    public static RegistryObject<FlowingFluid> YAK_HAIR_FLUID_STILL;
//    public static RegistryObject<FlowingFluid> YAK_HAIR_FLUID_FLOWING;
//    public static RegistryObject<Block> YAK_HAIR_FLUID_BLOCK;
//
//    public static void init(DeferredRegister<Block> blockRegistry, DeferredRegister<Fluid> fluidRegistry) {
//
//        grassThatch = Registration.createFluidReference(EnvironmentalBlocks.GRASS_THATCH, Environmental.MOD_ID, "grass_thatch", 0xA69553);
//         cattailThatch = Registration.createFluidReference(EnvironmentalBlocks.CATTAIL_THATCH, Environmental.MOD_ID, "cattail_thatch", 0x568D3B);
//         duckweedThatch = Registration.createFluidReference(EnvironmentalBlocks.DUCKWEED_THATCH, Environmental.MOD_ID, "duckweed_thatch", 0x3A3F28);
//         yakHairFluid = new ThatchFluid.FluidReferenceHolder(EnvironmentalBlocks.YAK_HAIR_BLOCK, new ResourceLocation(Environmental.MOD_ID, "block/yak_hair_block_end"), new ResourceLocation(Environmental.MOD_ID, "block/yak_hair_block_side_flow"), 0x774832);
//
////        grassThatch = Registration.createFluidReference(()->Blocks.HAY_BLOCK, null, "grass_thatch", 0xA69553);
////        cattailThatch = Registration.createFluidReference(()->Blocks.HAY_BLOCK, null, "cattail_thatch", 0x568D3B);
////        duckweedThatch = Registration.createFluidReference(()->Blocks.HAY_BLOCK, null, "duckweed_thatch", 0x3A3F28);
////        yakHairFluid = new ThatchFluid.FluidReferenceHolder(()->Blocks.HAY_BLOCK, new ResourceLocation(null, "block/yak_hair_block_end"), new ResourceLocation(null, "block/yak_hair_block_side_flow"), 0x774832);
//
//
//
//        GRASS_THATCH_BLOCK = blockRegistry.register("grass_thatch_fluid", () -> new ThatchFluidBlock(() -> GRASS_THATCH_STILL.get(), Registration.GREEN_THATCH_PROPERTY));
//        CATTAIL_THATCH_BLOCK = blockRegistry.register("cattail_thatch_fluid", () -> new ThatchFluidBlock(() -> CATTAIL_THATCH_STILL.get(), Registration.GREEN_THATCH_PROPERTY));
//        DUCKWEED_THATCH_BLOCK = blockRegistry.register("duckweed_thatch_fluid", () -> new ThatchFluidBlock(() -> DUCKWEED_THATCH_STILL.get(), Registration.GREEN_THATCH_PROPERTY));
//        YAK_HAIR_FLUID_BLOCK = blockRegistry.register("yak_hair_fluid", () -> new ThatchFluidBlock(() -> YAK_HAIR_FLUID_STILL.get(), Registration.GREEN_THATCH_PROPERTY));
//
//        GRASS_THATCH_STILL = fluidRegistry.register("grass_thatch_still", () -> new ThatchFluid.Source(grassThatch));
//        GRASS_THATCH_FLOWING = fluidRegistry.register("grass_thatch_flowing", () -> new ThatchFluid.Flowing(grassThatch));
//        grassThatch.setFluidBlock(GRASS_THATCH_BLOCK::get);
//        grassThatch.setStillFluid(GRASS_THATCH_STILL::get);
//        grassThatch.setFlowingFluid(GRASS_THATCH_FLOWING::get);
//
//        CATTAIL_THATCH_STILL = fluidRegistry.register("cattail_thatch_still", () -> new ThatchFluid.Source(cattailThatch));
//        CATTAIL_THATCH_FLOWING = fluidRegistry.register("cattail_thatch_flowing", () -> new ThatchFluid.Flowing(cattailThatch));
//        cattailThatch.setFluidBlock(CATTAIL_THATCH_BLOCK::get);
//        cattailThatch.setStillFluid(CATTAIL_THATCH_STILL::get);
//        cattailThatch.setFlowingFluid(CATTAIL_THATCH_FLOWING::get);
//
//        DUCKWEED_THATCH_STILL = fluidRegistry.register("duckweed_thatch_still", () -> new ThatchFluid.Source(duckweedThatch));
//        DUCKWEED_THATCH_FLOWING = fluidRegistry.register("duckweed_thatch_flowing", () -> new ThatchFluid.Flowing(duckweedThatch));
//        duckweedThatch.setFluidBlock(DUCKWEED_THATCH_BLOCK::get);
//        duckweedThatch.setStillFluid(DUCKWEED_THATCH_STILL::get);
//        duckweedThatch.setFlowingFluid(DUCKWEED_THATCH_FLOWING::get);
//
//        YAK_HAIR_FLUID_STILL = fluidRegistry.register("yak_hair_fluid_still", () -> new ThatchFluid.Source(yakHairFluid));
//        YAK_HAIR_FLUID_FLOWING = fluidRegistry.register("yak_hair_fluid_flowing", () -> new ThatchFluid.Flowing(yakHairFluid));
//        yakHairFluid.setFluidBlock(YAK_HAIR_FLUID_BLOCK::get);
//        yakHairFluid.setStillFluid(YAK_HAIR_FLUID_STILL::get);
//        yakHairFluid.setFlowingFluid(YAK_HAIR_FLUID_FLOWING::get);
//    }
//
//    public static void addThatchesToMap() {
//        ThatchFluid.shearMap.put(grassThatch.getSourceBlock(), grassThatch);
//        ThatchFluid.shearMap.put(cattailThatch.getSourceBlock(), cattailThatch);
//        ThatchFluid.shearMap.put(duckweedThatch.getSourceBlock(), duckweedThatch);
//        ThatchFluid.shearMap.put(yakHairFluid.getSourceBlock(), yakHairFluid);
//    }
//
//
//    public static void clientSetup() {
//        ItemBlockRenderTypes.setRenderLayer(GRASS_THATCH_BLOCK.get(), RenderType.solid());
//        ItemBlockRenderTypes.setRenderLayer(CATTAIL_THATCH_BLOCK.get(), RenderType.solid());
//        ItemBlockRenderTypes.setRenderLayer(DUCKWEED_THATCH_BLOCK.get(), RenderType.solid());
//        ItemBlockRenderTypes.setRenderLayer(YAK_HAIR_FLUID_BLOCK.get(), RenderType.solid());
//    }
//
//    public static void addProcessTextures(Collection<ResourceLocation> list) {
//        list.add(new ResourceLocation("environmental:textures/block/grass_thatch.png"));
//        list.add(new ResourceLocation("environmental:textures/block/cattail_thatch.png"));
//        list.add(new ResourceLocation("environmental:textures/block/duckweed_thatch.png"));
//        list.add(new ResourceLocation("environmental:textures/block/yak_hair_block_side.png"));
//    }
//
//}
