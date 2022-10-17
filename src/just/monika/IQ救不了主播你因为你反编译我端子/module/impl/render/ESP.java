/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.render;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventMotion;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventRenderGui;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;

public class ESP
extends Module {
    public ESP(ModuleData data) {
        super(data);
    }

    @RegisterEvent(events={EventRenderGui.class, EventMotion.class})
    public void onEvent(Event event) {
        if (event instanceof EventRenderGui) {
            EventRenderGui eventRenderGui = (EventRenderGui)event;
        }
    }
}

