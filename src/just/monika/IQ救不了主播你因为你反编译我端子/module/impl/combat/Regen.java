package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.combat;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventTick;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Regen extends Module {
    public Regen(){
        super(new ModuleData(ModuleData.Type.Combat,"Regen","Speed Up Regen"));
    }
    @Override
    @RegisterEvent(events = {EventTick.class})
    public void onEvent(Event var1) {
        mc.getNetHandler().addToSendQueue(new C03PacketPlayer(Minecraft.thePlayer.onGround));
    }
}
