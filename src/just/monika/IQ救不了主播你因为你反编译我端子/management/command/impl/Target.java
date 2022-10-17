package just.monika.IQ救不了主播你因为你反编译我端子.management.command.impl;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.management.command.Command;
import just.monika.IQ救不了主播你因为你反编译我端子.util.misc.ChatUtil;
import just.monika.IQ救不了主播你因为你反编译我端子.module.impl.combat.Killaura;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;

public class Target
  extends Command
{
  protected final Minecraft mc = Minecraft.getMinecraft();
  
  public Target(String[] names, String description)
  {
    super(names, description);
  }
  
  public void fire(String[] args)
  {
    if (args == null)
    {
      printUsage();
      return;
    }
    if (args.length > 0)
    {
      String name = args[0];
      if (this.mc.theWorld.getPlayerEntityByName(name) != null)
      {
        EntityLivingBase vip = this.mc.theWorld.getPlayerEntityByName(name);
        Killaura.vip = vip;
        ChatUtil.printChat("�c[�fS�c]�7 Now targeting " + args[0]);
        
        return;
      }
      Killaura.vip = null;
      ChatUtil.printChat("�c[�fS�c]�7 No entity with the name \"" + args[0] + "\" currently exists.");
    }
    printUsage();
  }
  
  public String getUsage()
  {
    return "Target <Target>";
  }
  
  public void onEvent(Event event) {}
}
