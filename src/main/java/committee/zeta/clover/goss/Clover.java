package committee.zeta.clover.goss;

import committee.zeta.clover.goss.common.config.Config;
import committee.zeta.clover.goss.init.ItemInit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author GossChinese
 */
@Mod(Clover.MOD_ID)
public class Clover {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "clover";

    public Clover() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        MinecraftForge.EVENT_BUS.register(this);
        registry(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void registry(IEventBus modBus){
        ItemInit.ITEMS.register(modBus);
    }
}
