package committee.zeta.clover.goss.event;

import committee.zeta.clover.goss.common.config.Config;
import committee.zeta.clover.goss.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
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
        Player player= event.getPlayer();
        BlockPos position = event.getPos();
        BlockState grass = event.getState();
        Level level = player.getCommandSenderWorld();
        if ((!grass.is(Blocks.GRASS)) || (Math.random() >= Config.PROBABILITY_CLOVER.get()) || (player.isCreative())){ return; }
        ItemEntity clover = new ItemEntity(EntityType.ITEM, level);
        clover.setItem(new ItemStack(ItemInit.CLOVER.get()));
        clover.setPos(position.getX(), position.getY() + 0.5D, position.getZ());
        MinecraftServer mcs = level.getServer();
        mcs.overworld().addFreshEntity(clover);
    }
}
