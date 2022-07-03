package committee.zeta.clover.goss.init;

import committee.zeta.clover.goss.Clover;
import committee.zeta.clover.goss.common.item.CloverItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author GossChinese
 */
public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Clover.MOD_ID);

    public static final RegistryObject<Item> CLOVER = ITEMS.register("clover", ()-> new CloverItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
}
