package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.other;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventMotion;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventPacket;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.Setting;
import just.monika.IQ救不了主播你因为你反编译我端子.util.misc.ChatUtil;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S38PacketPlayerListItem;

public class StreamerMode
  extends Module
{
  private String NAMEPROTECT = "PROTECT";
  
  public StreamerMode(ModuleData data)
  {
    super(data);
    this.settings.put(this.NAMEPROTECT, new Setting(this.NAMEPROTECT, Boolean.valueOf(true), "Protects your name."));
  }
  
  @RegisterEvent(events={EventMotion.class, EventPacket.class})
  public void onEvent(Event event)
  {
    if ((event instanceof EventMotion))
    {
      EventMotion em = (EventMotion)event;
      if (!em.isPre()) {}
    }
    if ((event instanceof EventPacket))
    {
      EventPacket ep = (EventPacket)event;
      if (ep.isIncoming())
      {
        String temp;
        if ((ep.getPacket() instanceof S02PacketChat))
        {
          S02PacketChat packet = (S02PacketChat)ep.getPacket();
          if ((packet.func_148915_c().getUnformattedText().contains(mc.thePlayer.getName())) && (((Boolean)((Setting)this.settings.get(this.NAMEPROTECT)).getValue()).booleanValue()))
          {
            temp = packet.func_148915_c().getFormattedText();
            ChatUtil.printChat(temp.replaceAll(mc.thePlayer.getName(), "�k" + mc.thePlayer.getName() + "kIlOkIlOkIlO"));
            ep.setCancelled(true);
          }
        }
        if ((ep.getPacket() instanceof S38PacketPlayerListItem))
        {
          S38PacketPlayerListItem packet = (S38PacketPlayerListItem)ep.getPacket();
          Object localObject;
        }
      }
    }
  }
}
