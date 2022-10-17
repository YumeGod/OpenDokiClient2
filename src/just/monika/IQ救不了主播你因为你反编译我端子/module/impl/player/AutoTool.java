/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.player;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventTick;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.util.misc.BlockUtil;
import net.minecraft.util.BlockPos;

public class AutoTool
extends Module {
    public AutoTool(ModuleData data) {
        super(data);
    }

    @RegisterEvent(events={EventTick.class})
    public void onEvent(Event event) {
        if (!AutoTool.mc.gameSettings.keyBindAttack.getIsKeyPressed()) {
            return;
        }
        if (AutoTool.mc.objectMouseOver == null) {
            return;
        }
        BlockPos pos = AutoTool.mc.objectMouseOver.getBlockPos();
        if (pos == null) {
            return;
        }
        BlockUtil.updateTool(pos);
    }
}

