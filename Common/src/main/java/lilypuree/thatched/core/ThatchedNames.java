package lilypuree.thatched.core;

import lilypuree.thatched.ThatchedConstants;
import net.minecraft.resources.ResourceLocation;

public class ThatchedNames {


    protected static ResourceLocation create(String name){
        return new ResourceLocation(ThatchedConstants.MODID, name);
    }

}
