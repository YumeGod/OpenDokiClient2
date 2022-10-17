/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.player;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventTick;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.Setting;

public class FastPlace
extends Module {
    private static final String KEY_TIMES = "CLICKSPEED";

    public FastPlace(ModuleData data) {
        super(data);
        this.settings.put("CLICKSPEED", new Setting<Integer>("CLICKSPEED", 4, "Tick delay between clicks.", 1.0, 0.0, 20.0));
    }

    @RegisterEvent(events={EventTick.class})
    public void onEvent(Event event) {
        FastPlace.mc.rightClickDelayTimer = Math.min(FastPlace.mc.rightClickDelayTimer, 1);
    }
}

