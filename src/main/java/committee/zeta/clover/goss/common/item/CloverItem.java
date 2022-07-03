package committee.zeta.clover.goss.common.item;

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
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

/**
 * @author GossChinese
 */
public class CloverItem extends Item {
    protected static final Object[] ARRAY_ITEMS = ForgeRegistries.ITEMS.getValues().toArray();
    protected static final Random RANDOM = new Random();

    public CloverItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity player, Hand handIn) {
        ItemStack stack = player.getItemInHand(handIn);
        stack.setCount(stack.getCount()-1);
        MinecraftServer mcs = level.getServer();
        if (!level.isClientSide){
            if (Math.random()*100D <= 1.0D){
                CreeperEntity creeper = new CreeperEntity(EntityType.CREEPER, level);
                LightningBoltEntity lightning = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, level);
                creeper.thunderHit(level.getServer().overworld(), lightning);
                creeper.setPos(player.getX()+0.5D, player.getY()+0.5D, player.getZ()+0.5D);
                mcs.overworld().addFreshEntity(creeper);
                return ActionResult.pass(stack);
            }else{
                ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, level);
                itemEntity.setItem(new ItemStack((Item) ARRAY_ITEMS[RANDOM.nextInt(ARRAY_ITEMS.length)]));
                itemEntity.setPos(player.getX()+0.5D, player.getY()+0.5D, player.getZ()+0.5D);
                mcs.overworld().addFreshEntity(itemEntity);
                return ActionResult.sidedSuccess(stack, level.isClientSide);
            }
        }
        return ActionResult.fail(stack);
    }
}
