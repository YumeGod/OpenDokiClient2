/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.movement;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventMove;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;

public class DepthStrider
extends Module {
    int waitTicks;

    public DepthStrider(ModuleData data) {
        super(data);
    }

    @RegisterEvent(events={EventMove.class})
    public void onEvent(Event event) {
        if (event instanceof EventMove) {
            EventMove em = (EventMove)event;
            if (DepthStrider.mc.thePlayer.isInWater()) {
                float yaw;
                double strafe;
                double forward;
                ++this.waitTicks;
                if (this.waitTicks == 4) {
                    forward = DepthStrider.mc.thePlayer.movementInput.moveForward;
                    strafe = DepthStrider.mc.thePlayer.movementInput.moveStrafe;
                    yaw = DepthStrider.mc.thePlayer.rotationYaw;
                    if (forward == 0.0 && strafe == 0.0) {
                        em.setX(0.0);
                        em.setZ(0.0);
                    } else {
                        if (forward != 0.0) {
                            if (strafe > 0.0) {
                                strafe = 1.0;
                                yaw += (float)(forward > 0.0 ? -45 : 45);
                            } else if (strafe < 0.0) {
                                yaw += (float)(forward > 0.0 ? 45 : -45);
                            }
                            strafe = 0.0;
                            if (forward > 0.0) {
                                forward = 1.0;
                            } else if (forward < 0.0) {
                                forward = -1.0;
                            }
                        }
                        em.setX(forward * 0.4000000059604645 * Math.cos(Math.toRadians(yaw + 90.0f)) + strafe * 0.4000000059604645 * Math.sin(Math.toRadians(yaw + 90.0f)));
                        em.setZ(forward * 0.4000000059604645 * Math.sin(Math.toRadians(yaw + 90.0f)) - strafe * 0.4000000059604645 * Math.cos(Math.toRadians(yaw + 90.0f)));
                    }
                }
                if (this.waitTicks >= 5) {
                    forward = DepthStrider.mc.thePlayer.movementInput.moveForward;
                    strafe = DepthStrider.mc.thePlayer.movementInput.moveStrafe;
                    yaw = DepthStrider.mc.thePlayer.rotationYaw;
                    if (forward == 0.0 && strafe == 0.0) {
                        em.setX(0.0);
                        em.setZ(0.0);
                    } else {
                        if (forward != 0.0) {
                            if (strafe > 0.0) {
                                strafe = 1.0;
                                yaw += (float)(forward > 0.0 ? -45 : 45);
                            } else if (strafe < 0.0) {
                                yaw += (float)(forward > 0.0 ? 45 : -45);
                            }
                            strafe = 0.0;
                            if (forward > 0.0) {
                                forward = 1.0;
                            } else if (forward < 0.0) {
                                forward = -1.0;
                            }
                        }
                        em.setX(forward * 0.30000001192092896 * Math.cos(Math.toRadians(yaw + 90.0f)) + strafe * 0.30000001192092896 * Math.sin(Math.toRadians(yaw + 90.0f)));
                        em.setZ(forward * 0.30000001192092896 * Math.sin(Math.toRadians(yaw + 90.0f)) - strafe * 0.30000001192092896 * Math.cos(Math.toRadians(yaw + 90.0f)));
                    }
                    this.waitTicks = 0;
                }
            }
        }
    }
}

