package committee.zeta.clover.goss.common.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author GossChinese
 */
public class Config {
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.DoubleValue PROBABILITY_CREEPER;
    public static ForgeConfigSpec.DoubleValue PROBABILITY_CLOVER;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> BLACK_LIST;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> WHITE_LIST;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        COMMON_BUILDER.comment("General Settings").push("general");
        PROBABILITY_CREEPER = COMMON_BUILDER.comment("The Probability of laying Charged Creeper").defineInRange("probabilityCreeper", 0.01, 0.0, 1.0);
        PROBABILITY_CLOVER = COMMON_BUILDER.comment("The Probability of laying Clover").defineInRange("probabilityClover", 0.05, 0.0, 1.0);
        BLACK_LIST = COMMON_BUILDER.comment("The Items in Black List").defineList("blackList", getBlackList(), getElementValidator());
        WHITE_LIST = COMMON_BUILDER.comment("The Items in White List").defineList("whiteList", new ArrayList<String>(), getElementValidator());
        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    private static List<String> getBlackList(){
        List<String> dislikedItems = new ArrayList<>();
        dislikedItems.add("minecraft:bedrock");
        dislikedItems.add("minecraft:structure_block");
        dislikedItems.add("minecraft:jigsaw");
        dislikedItems.add("minecraft:light");
        dislikedItems.add("minecraft:command_block");
        dislikedItems.add("minecraft:repeating_command_block");
        dislikedItems.add("minecraft:chain_command_block");
        dislikedItems.add("minecraft:command_block_minecart");
        dislikedItems.add("minecraft:barrier");
        dislikedItems.add("minecraft:structure_void");
        dislikedItems.add("clover:clover");

        return dislikedItems;
    }

    private static Predicate<Object> getElementValidator(){
        return new Predicate<Object>() {
            @Override
            public boolean test(Object o) {
                return o instanceof String;
            }
        };
    }
}
