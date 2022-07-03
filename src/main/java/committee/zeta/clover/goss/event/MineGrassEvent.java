package committee.zeta.clover.goss.event;

import committee.zeta.clover.goss.init.ItemInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author GossChinese
 */
@Mod.EventBusSubscriber()
public class MineGrassEvent {
    @SubscribeEvent
    public static void onMine(BlockEvent.BreakEvent event){
        PlayerEntity player= event.getPlayer();
        BlockPos position = event.getPos();
        BlockState grass = event.getState();
        World level = player.getCommandSenderWorld();
        if ((!grass.is(Blocks.GRASS)) && (Math.random()*100 >= 5) && (player.isCreative())){ return; }
        ItemEntity clover = new ItemEntity(EntityType.ITEM, level);
        clover.setItem(new ItemStack(ItemInit.CLOVER.get()));
        clover.setPos(position.getX(), position.getY(), position.getZ());
        MinecraftServer mcs = level.getServer();
        mcs.overworld().addFreshEntity(clover);
    }
}
