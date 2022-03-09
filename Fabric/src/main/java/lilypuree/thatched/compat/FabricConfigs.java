package lilypuree.thatched.compat;

import lilypuree.thatched.CommonConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "thatched_settings")
public class FabricConfigs implements ConfigData, CommonConfig {

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""
                 How rare are Thatched Villages in Plains biomes.
                1 for spawning in most chunks and 1001 for none.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int thatchedVillageMaxChunkDistance = 70;

    @Override
    public int getThatchedVillageMaxChunkDistance() {
        return thatchedVillageMaxChunkDistance;
    }

    @Override
    public String blacklistedDimensions() {
        return "";
    }

    @Override
    public String blacklistedVillageBiomes() {
        return "";
    }
}
