package lilypuree.thatched.events;

import lilypuree.thatched.ThatchedConstants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = ThatchedConstants.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEventHandler {

    public static void initRenderLayers(FMLClientSetupEvent e) {

    }
}
