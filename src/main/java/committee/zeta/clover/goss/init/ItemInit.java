package committee.zeta.clover.goss.init;

import committee.zeta.clover.goss.Clover;
import committee.zeta.clover.goss.common.item.CloverItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author GossChinese
 */
public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Clover.MOD_ID);

    public static final RegistryObject<Item> CLOVER = ITEMS.register("clover", ()-> new CloverItem(new Item.Properties().tab(ItemGroup.TAB_MISC)));
}
