package lilypuree.thatched;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = ThatchedConstants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ThatchedConfigs implements CommonConfig {

    public static final String CATEGORY_GENERAL = "general";

    public static ForgeConfigSpec COMMON_CONFIG;

    private ForgeConfigSpec.ConfigValue<Boolean> addVillagesToModdedBiomes;
    private ForgeConfigSpec.ConfigValue<Integer> thatchedVillageMaxChunkDistance;
    private ForgeConfigSpec.ConfigValue<String> blacklistedVillageBiomes;
    private ForgeConfigSpec.ConfigValue<String> blacklistedDimensions;

    public ThatchedConfigs() {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();


        COMMON_BUILDER.comment("Thatched Configs");
        COMMON_BUILDER.push("Mod Options");
        blacklistedDimensions = COMMON_BUILDER
                .comment("\r\n Add the identifier for the dimension that you want"
                        + "\r\n no Thatched structure to spawn in."
                        + "\r\n Separate multiple entries with a comma."
                        + "\r\n"
                        + "\r\nExample: \"minecraft:the_end,awesome_mod:awesome_dimension\"")
                .translation("thatched.config.all.blacklisteddimensions")
                .define("blacklistedDimensions", "");
        COMMON_BUILDER.pop();
        COMMON_BUILDER.push("Villages");

        addVillagesToModdedBiomes = COMMON_BUILDER.comment("\\r\\n Add the custom villages to modded biomes of the same categories/type.")
                .translation("thatched.config.villages.addVillagesToModdedBiomes")
                .define("addVillagesToModdedBiomes", true);

        COMMON_BUILDER.push("Spawnrate");

        thatchedVillageMaxChunkDistance = COMMON_BUILDER.comment("\r\n How rare are Thatched Villages in Plains biomes.\r\n"
                        + "\n "
                        + "1 for spawning in most chunks and 1001 for no spawn.")
                .translation("thatched.config.villages.thatchedvillagespawnrate")
                .defineInRange("thatchedVillageSpawnRate", 70, 1, 1001);

        blacklistedVillageBiomes = COMMON_BUILDER.comment("\r\n Add the ID/resource location of the biome you don't want"
                        + "\r\n villages to spawn in. Separate each ID with a comma ,"
                        + "\r\n"
                        + "\r\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
                .translation("thatched.config.villages.blacklistedvillagebiomes")
                .define("blacklistedVillageBiomes", "");

        COMMON_BUILDER.pop();


        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    @Override
    public int getThatchedVillageMaxChunkDistance() {
        return thatchedVillageMaxChunkDistance.get();
    }

    @Override
    public String blacklistedDimensions() {
        return blacklistedDimensions.get();
    }

    @Override
    public String blacklistedVillageBiomes() {
        return blacklistedDimensions.get();
    }
}
