package committee.zeta.clover.goss.common.item;

import committee.zeta.clover.goss.Clover;
import committee.zeta.clover.goss.common.config.Config;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.*;

/**
 * @author GossChinese
 */
@Mod.EventBusSubscriber()
public class CloverItem extends Item {
    protected static Object[] ARRAY_KEYS;
    protected static final Random RANDOM = new Random();
    protected static boolean IS_EMPTY;

    public CloverItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World level, List<ITextComponent> components, ITooltipFlag flag) {
        super.appendHoverText(stack, level, components, flag);
        if (IS_EMPTY){
            components.add(new TranslationTextComponent("tip.clover.whiteListOff"));
        }else{
            components.add(new TranslationTextComponent("tip.clover.whiteListOn"));
        }
    }

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity player, Hand handIn) {
        ItemStack stack = player.getItemInHand(handIn);
        if (!player.isCreative()){
            stack.setCount(stack.getCount()-1);
        }
        MinecraftServer mcs = level.getServer();
        if (!level.isClientSide){
            if (Math.random() <= Config.PROBABILITY_CREEPER.get()){
                CreeperEntity creeper = new CreeperEntity(EntityType.CREEPER, level);
                LightningBoltEntity lightning = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, level);
                creeper.thunderHit(level.getServer().overworld(), lightning);
                creeper.setPos(player.getX(), player.getY()+0.5D, player.getZ());
                mcs.overworld().addFreshEntity(creeper);
                return ActionResult.pass(stack);
            }else{
                ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, level);
                itemEntity.setItem(new ItemStack(ForgeRegistries.ITEMS.getValue((ResourceLocation) ARRAY_KEYS[RANDOM.nextInt(ARRAY_KEYS.length)])));
                itemEntity.setPos(player.getX(), player.getY()+0.5D, player.getZ());
                mcs.overworld().addFreshEntity(itemEntity);
                return ActionResult.sidedSuccess(stack, level.isClientSide);
            }
        }
        return ActionResult.fail(stack);
    }

    private static Object[] getKeys(){
        Set<ResourceLocation> newSet = new HashSet<>();
        if (!IS_EMPTY){
            List<? extends String> whiteList = Config.WHITE_LIST.get();
            for (String str : whiteList){
                newSet.add(rlBuilder(str));
                Clover.LOGGER.debug(str);
                Clover.LOGGER.debug(rlBuilder(str));
            }
            return newSet.toArray();
        }

        Set<ResourceLocation> keySet = ForgeRegistries.ITEMS.getKeys();
        List<? extends String> blackList = Config.BLACK_LIST.get();
        for (ResourceLocation rl : keySet){
            for (String str : blackList){
                if (rlBuilder(str).equals(rl)){
                    break;
                }
                newSet.add(rl);
            }
        }
        return newSet.toArray();
    }

    private static ResourceLocation rlBuilder(String str){
        String[] strs = str.split(":");
        return new ResourceLocation(strs[0], strs[1]);
    }

    @SubscribeEvent
    public static void initKeys(PlayerEvent.PlayerLoggedInEvent event){
        IS_EMPTY = Config.WHITE_LIST.get().isEmpty();
        ARRAY_KEYS = getKeys();
    }
}
