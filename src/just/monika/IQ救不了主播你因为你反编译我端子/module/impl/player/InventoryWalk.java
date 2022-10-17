/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.player;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventPacket;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventTick;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.Setting;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import org.lwjgl.input.Keyboard;

public class InventoryWalk
extends Module {
    private String CARRY = "CARRY";

    public InventoryWalk(ModuleData data) {
        super(data);
        this.settings.put(this.CARRY, new Setting<Boolean>(this.CARRY, false, "Carry items in crafting slots."));
    }

    @RegisterEvent(events={EventPacket.class, EventTick.class})
    public void onEvent(Event event) {
        EventPacket ep;
        if (InventoryWalk.mc.currentScreen instanceof GuiChat) {
            return;
        }
        if (event instanceof EventTick && InventoryWalk.mc.currentScreen != null) {
            if (Keyboard.isKeyDown((int)200)) {
                InventoryWalk.mc.thePlayer.rotationPitch -= 1.0f;
            }
            if (Keyboard.isKeyDown((int)208)) {
                InventoryWalk.mc.thePlayer.rotationPitch += 1.0f;
            }
            if (Keyboard.isKeyDown((int)203)) {
                InventoryWalk.mc.thePlayer.rotationYaw -= 3.0f;
            }
            if (Keyboard.isKeyDown((int)205)) {
                InventoryWalk.mc.thePlayer.rotationYaw += 3.0f;
            }
        }
        if (event instanceof EventPacket && ((Boolean)((Setting)this.settings.get(this.CARRY)).getValue()).booleanValue() && (ep = (EventPacket)event).isOutgoing() && ep.getPacket() instanceof C0DPacketCloseWindow) {
            ep.setCancelled(true);
        }
    }
}

