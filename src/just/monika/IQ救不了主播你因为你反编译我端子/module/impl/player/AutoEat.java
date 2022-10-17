/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.player;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventTick;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.util.Timer;

public class AutoEat
extends Module {
    Timer timer = new Timer();

    public AutoEat(ModuleData data) {
        super(data);
    }

    @RegisterEvent(events={EventTick.class})
    public void onEvent(Event event) {
        if (AutoEat.mc.thePlayer.getFoodStats().getFoodLevel() < 20 && this.timer.delay(2000.0f)) {
            AutoEat.mc.thePlayer.sendChatMessage("/eat");
            this.timer.reset();
        }
        if (this.timer.delay(60000.0f)) {
            AutoEat.mc.thePlayer.sendChatMessage("/eat");
            this.timer.reset();
        }
    }
}

