//package lilypuree.thatched.core.compat;
//
//import com.lilypuree.decorative_blocks.fluid.ThatchFluid;
//import com.lilypuree.decorative_blocks.fluid.ThatchFluidBlock;
//import com.lilypuree.thatched.Registration;
//import com.minecraftabnormals.neapolitan.core.Neapolitan;
//import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
//import lilypuree.decorative_blocks.fluid.ThatchFluid;
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
//public class NeapolitanCompat {
//
//    public static ThatchFluid.FluidReferenceHolder frondThatch;
//    public static FlowingFluid FROND_THATCH_STILL;
//    public static FlowingFluid FROND_THATCH_FLOWING;
//    public static Block FROND_THATCH_BLOCK;
//
//    public void initBlocks() {
//        FROND_THATCH_STILL = new ThatchFluid.Source(frondThatch);
//        FROND_THATCH_FLOWING = new ThatchFluid.Flowing(frondThatch);
//    }
//
//    public static void init(DeferredRegister<Block> blockRegistry, DeferredRegister<Fluid> fluidRegistry) {
//        frondThatch = Registration.createFluidReference(NeapolitanBlocks.FROND_THATCH, Neapolitan.MOD_ID, "frond_thatch", 0x494024);
//
//        FROND_THATCH_BLOCK = blockRegistry.register("frond_thatch_fluid", () -> new ThatchFluidBlock(() -> FROND_THATCH_STILL.get(), Registration.GREEN_THATCH_PROPERTY));
//
//        FROND_THATCH_STILL = fluidRegistry.register("frond_thatch_still", () -> new ThatchFluid.Source(frondThatch));
//        FROND_THATCH_FLOWING = fluidRegistry.register("frond_thatch_flowing", () -> new ThatchFluid.Flowing(frondThatch));
//        frondThatch.setFluidBlock(FROND_THATCH_BLOCK::get);
//        frondThatch.setStillFluid(FROND_THATCH_STILL::get);
//        frondThatch.setFlowingFluid(FROND_THATCH_FLOWING::get);
//    }
//
//    public static void addThatchesToMap() {
//        ThatchFluid.shearMap.put(frondThatch.getSourceBlock(), frondThatch);
//    }
//
//    public static void clientSetup() {
//        ItemBlockRenderTypes.setRenderLayer(FROND_THATCH_BLOCK.get(), RenderType.solid());
//    }
//
//    public static void addProcessTextures(Collection<ResourceLocation> list) {
//        list.add(new ResourceLocation("neapolitan:textures/block/frond_thatch.png"));
//    }
//}
