package lilypuree.thatched;

import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThatchedConstants {
    public static final String MODID = "thatched";
    public static final ResourceLocation STRUCTURE_NAME = new ResourceLocation(MODID, "thatched_village");
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static CommonConfig config;
}
