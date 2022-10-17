/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.other;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventTick;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.Setting;

public class Timer
extends Module {
    private String GS = "GAMESPEED";

    public Timer(ModuleData data) {
        super(data);
        this.settings.put(this.GS, new Setting<Double>(this.GS, 0.3, "The value the mc timer will be set to.", 0.05, 0.1, 5.0));
    }

    @Override
    public void onEnable() {
        Timer.mc.timer.timerSpeed = 1.0f;
    }

    @Override
    public void onDisable() {
        Timer.mc.timer.timerSpeed = 1.0f;
    }

    @RegisterEvent(events={EventTick.class})
    public void onEvent(Event event) {
        Timer.mc.timer.timerSpeed = ((Number)((Setting)this.settings.get(this.GS)).getValue()).floatValue();
    }
}

