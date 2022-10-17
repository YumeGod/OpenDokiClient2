/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.movement;

import just.monika.IQ救不了主播你因为你反编译我端子.DokiDokiLegitClient;
import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventMotion;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.Setting;
import just.monika.IQ救不了主播你因为你反编译我端子.util.Timer;
import net.minecraft.block.BlockAir;
import net.minecraft.util.BlockPos;

public class Catch
extends Module {
    private Timer timer = new Timer();
    private boolean saveMe;
    private String VOID = "VOID";

    public Catch(ModuleData data) {
        super(data);
        this.settings.put(this.VOID, new Setting<Boolean>(this.VOID, true, "Only catch when falling into void."));
    }

    @RegisterEvent(events={EventMotion.class})
    public void onEvent(Event event) {
        EventMotion em = (EventMotion)event;
        if (em.isPre() && this.timer.delay(100.0f) && Catch.mc.thePlayer.fallDistance > 4.0f && !Catch.mc.thePlayer.isJumping && !DokiDokiLegitClient.getModuleManager().isEnabled(Fly.class)) {
            if (!((Boolean)((Setting)this.settings.get(this.VOID)).getValue()).booleanValue() || !this.isBlockUnder()) {
                this.saveMe = true;
            }
        } else if (this.saveMe) {
            Catch.mc.thePlayer.motionY = 0.0;
            this.timer.reset();
            this.saveMe = false;
        }
        this.setSuffix((Catch.mc.thePlayer.fallDistance > 4.0f ? "\u00a7c" : "\u00a78") + Integer.toString((int)Catch.mc.thePlayer.fallDistance));
    }

    private boolean isBlockUnder() {
        for (int i = (int)(Catch.mc.thePlayer.posY - 1.0); i > 0; --i) {
            BlockPos pos = new BlockPos(Catch.mc.thePlayer.posX, (double)i, Catch.mc.thePlayer.posZ);
            if (Catch.mc.theWorld.getBlockState(pos).getBlock() instanceof BlockAir) continue;
            return true;
        }
        return false;
    }
}

