/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.render;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventTick;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Brightness
extends Module {
    public Brightness(ModuleData data) {
        super(data);
    }

    @RegisterEvent(events={EventTick.class})
    public void onEvent(Event event) {
        Brightness.mc.playerController.blockHitDelay = 0;
        boolean item = Brightness.mc.thePlayer.getCurrentEquippedItem() == null;
        Brightness.mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 5200, 1));
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Brightness.mc.thePlayer.removePotionEffect(Potion.nightVision.getId());
    }
}

