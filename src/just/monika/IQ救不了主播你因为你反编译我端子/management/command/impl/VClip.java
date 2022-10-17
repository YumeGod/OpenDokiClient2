/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.management.command.impl;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.management.command.Command;
import just.monika.IQ救不了主播你因为你反编译我端子.util.StringConversions;

public class VClip
extends Command {
    public VClip(String[] names, String description) {
        super(names, description);
    }

    @Override
    public void fire(String[] args) {
        if (args == null) {
            this.printUsage();
            return;
        }
        if (args.length == 1 && StringConversions.isNumeric(args[0])) {
            this.mc.thePlayer.setPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + Double.parseDouble(args[0]), this.mc.thePlayer.posZ);
            return;
        }
        this.printUsage();
    }

    @Override
    public String getUsage() {
        return "<Distance>";
    }

    public void onEvent(Event event) {
    }
}

