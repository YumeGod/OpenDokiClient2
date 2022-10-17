/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.movement;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventLiquidCollide;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventMotion;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventPacket;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.util.PlayerUtil;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.util.AxisAlignedBB;

public class Jesus
extends Module {
    int ticks;

    public Jesus(ModuleData data) {
        super(data);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.ticks = 0;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.ticks = 0;
    }

    @RegisterEvent(events={EventMotion.class, EventPacket.class, EventLiquidCollide.class})
    public void onEvent(Event event) {
        EventLiquidCollide ebb;
        if (event instanceof EventMotion) {
            EventMotion em = (EventMotion)event;
            if (em.isPre() && PlayerUtil.isOnLiquid() && !PlayerUtil.isInLiquid() && !Jesus.mc.thePlayer.isSneaking() && !Jesus.mc.gameSettings.keyBindJump.isPressed()) {
                if (this.ticks == 0 && PlayerUtil.isOnLiquid() && PlayerUtil.isMoving()) {
                    Jesus.mc.thePlayer.setSprinting(false);
                    Jesus.mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(Jesus.mc.thePlayer, C0BPacketEntityAction.Action.STOP_SPRINTING));
                }
                this.ticks = 1;
                if (Jesus.mc.thePlayer.ticksExisted % 2 == 0) {
                    em.setY(em.getY() - 1.0E-4);
                } else {
                    em.setY(em.getY() + 1.0E-4);
                }
            } else if (!this.shouldSetBoundingBox() && PlayerUtil.isInLiquid()) {
                Jesus.mc.thePlayer.fallDistance = 0.0f;
                double d = Jesus.mc.thePlayer.motionY = Jesus.mc.thePlayer.isSneaking() ? -0.13 : 0.1;
            }
            if (this.ticks == 1 && !PlayerUtil.isOnLiquid()) {
                this.ticks = 0;
            }
        }
        if (event instanceof EventLiquidCollide && (double)(ebb = (EventLiquidCollide)event).getPos().getY() + 0.9 < Jesus.mc.thePlayer.boundingBox.minY) {
            ebb.setBounds(new AxisAlignedBB(ebb.getPos().getX(), ebb.getPos().getY(), ebb.getPos().getZ(), ebb.getPos().getX() + 1, ebb.getPos().getY() + 1, ebb.getPos().getZ() + 1));
            ebb.setCancelled(this.shouldSetBoundingBox());
        }
    }

    private boolean shouldSetBoundingBox() {
        return !Jesus.mc.thePlayer.isSneaking() && Jesus.mc.thePlayer.fallDistance < 4.0f && !PlayerUtil.isInLiquid();
    }
}

