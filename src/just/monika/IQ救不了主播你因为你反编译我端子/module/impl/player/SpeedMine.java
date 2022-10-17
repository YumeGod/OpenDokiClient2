/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.player;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventTick;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class SpeedMine
extends Module {
    public SpeedMine(ModuleData data) {
        super(data);
    }

    @RegisterEvent(events={EventTick.class})
    public void onEvent(Event event) {
        SpeedMine.mc.playerController.blockHitDelay = 0;
        boolean item = SpeedMine.mc.thePlayer.getCurrentEquippedItem() == null;
        SpeedMine.mc.thePlayer.addPotionEffect(new PotionEffect(Potion.digSpeed.getId(), 100, item ? 1 : 0));
    }

    @Override
    public void onDisable() {
        super.onDisable();
        SpeedMine.mc.thePlayer.removePotionEffect(Potion.digSpeed.getId());
    }
}

