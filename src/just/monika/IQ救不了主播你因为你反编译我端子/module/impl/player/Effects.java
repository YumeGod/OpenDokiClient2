/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.player;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventMotion;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.util.PlayerUtil;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Effects
extends Module {
    public Effects(ModuleData data) {
        super(data);
    }

    @RegisterEvent(events={EventMotion.class})
    public void onEvent(Event event) {
        EventMotion em = (EventMotion)event;
        if (em.isPre()) {
            if (PlayerUtil.isOnLiquid() || PlayerUtil.isInLiquid()) {
                return;
            }
            if (Effects.mc.thePlayer.isPotionActive(Potion.blindness.getId())) {
                Effects.mc.thePlayer.removePotionEffect(Potion.blindness.getId());
            }
            if (Effects.mc.thePlayer.isPotionActive(Potion.confusion.getId())) {
                Effects.mc.thePlayer.removePotionEffect(Potion.confusion.getId());
            }
            if (Effects.mc.thePlayer.isPotionActive(Potion.digSlowdown.getId())) {
                Effects.mc.thePlayer.removePotionEffect(Potion.digSlowdown.getId());
            }
            if (Effects.mc.thePlayer.isBurning() && Effects.mc.thePlayer.isCollidedVertically) {
                for (int i = 0; i < 12; ++i) {
                    mc.getNetHandler().addToSendQueue(new C03PacketPlayer(true));
                }
            }
            for (Potion potion : Potion.potionTypes) {
                if (potion == null || !potion.isBadEffect() || !Effects.mc.thePlayer.isPotionActive(potion)) continue;
                PotionEffect activePotionEffect = Effects.mc.thePlayer.getActivePotionEffect(potion);
                for (int k = 0; k < activePotionEffect.getDuration() / 20; ++k) {
                    mc.getNetHandler().addToSendQueue(new C03PacketPlayer(true));
                }
            }
        }
    }
}

