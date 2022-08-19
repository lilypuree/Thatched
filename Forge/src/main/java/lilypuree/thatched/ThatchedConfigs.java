package lilypuree.thatched;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = ThatchedConstants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ThatchedConfigs implements CommonConfig {

    public static final String CATEGORY_GENERAL = "general";

    public static ForgeConfigSpec COMMON_CONFIG;

    public ThatchedConfigs() {
    }
}
