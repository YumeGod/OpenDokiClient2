package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.movement;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventMotion;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class NoSlowdown
  extends Module
{
  public NoSlowdown(ModuleData data)
  {
    super(data);
  }
  
  @RegisterEvent(events={EventMotion.class})
  public void onEvent(Event event)
  {
    EventMotion em = (EventMotion)event;
    if ((em.isPre()) && 
      (mc.thePlayer.isBlocking())) {
      mc.getNetHandler().addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
    }
    if ((em.isPost()) && 
      (mc.thePlayer.isBlocking())) {
      mc.getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
    }
  }
}
