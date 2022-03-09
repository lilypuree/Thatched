//package lilypuree.thatched.core.compat;
//
//import com.lilypuree.decorative_blocks.fluid.ThatchFluid;
//import com.lilypuree.decorative_blocks.fluid.ThatchFluidBlock;
//import com.lilypuree.thatched.Registration;
//import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
//import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
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
//public class UAComapt {
//    public static ThatchFluid.FluidReferenceHolder beachgrassThatch;
//    public static RegistryObject<FlowingFluid> BEACHGRASS_THATCH_STILL;
//    public static RegistryObject<FlowingFluid> BEACHGRASS_THATCH_FLOWING;
//    public static RegistryObject<Block> BEACHGRASS_THATCH_BLOCK;
//
//    public static void init(DeferredRegister<Block> blockRegistry, DeferredRegister<Fluid> fluidRegistry) {
//        beachgrassThatch = Registration.createFluidReference(UABlocks.BEACHGRASS_THATCH, UpgradeAquatic.MOD_ID, "beachgrass_thatch", 0x96A051);
//
//        BEACHGRASS_THATCH_BLOCK = blockRegistry.register("beachgrass_thatch_fluid", () -> new ThatchFluidBlock(() -> BEACHGRASS_THATCH_STILL.get(), Registration.GREEN_THATCH_PROPERTY));
//
//        BEACHGRASS_THATCH_STILL = fluidRegistry.register("beachgrass_thatch_still", () -> new ThatchFluid.Source(beachgrassThatch));
//        BEACHGRASS_THATCH_FLOWING = fluidRegistry.register("beachgrass_thatch_flowing", () -> new ThatchFluid.Flowing(beachgrassThatch));
//        beachgrassThatch.setFluidBlock(BEACHGRASS_THATCH_BLOCK::get);
//        beachgrassThatch.setStillFluid(BEACHGRASS_THATCH_STILL::get);
//        beachgrassThatch.setFlowingFluid(BEACHGRASS_THATCH_FLOWING::get);
//    }
//
//    public static void addThatchesToMap() {
//        ThatchFluid.shearMap.put(beachgrassThatch.getSourceBlock(), beachgrassThatch);
//    }
//
//    public static void clientSetup() {
//        ItemBlockRenderTypes.setRenderLayer(BEACHGRASS_THATCH_BLOCK.get(), RenderType.solid());
//    }
//
//    public static void addProcessTextures(Collection<ResourceLocation> list) {
//        list.add(new ResourceLocation("upgrade_aquatic:textures/block/beachgrass_thatch.png"));
//    }
//}
