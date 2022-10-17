/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.management.command.impl;

import just.monika.IQ救不了主播你因为你反编译我端子.DokiDokiLegitClient;
import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.management.command.Command;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.util.misc.ChatUtil;

public class Toggle
extends Command {
    public Toggle(String[] names, String description) {
        super(names, description);
    }

    @Override
    public void fire(String[] args) {
        if (args == null) {
            this.printUsage();
            return;
        }
        Module module = null;
        if (args.length > 0) {
            module = DokiDokiLegitClient.getModuleManager().get(args[0]);
        }
        if (module == null) {
            this.printUsage();
            return;
        }
        if (args.length == 1) {
            module.toggle();
            ChatUtil.printChat("\u00a7c[\u00a7fS\u00a7c]\u00a77 " + module.getName() + " has been" + (module.isEnabled() ? "\u00a7a Enabled." : "\u00a7c Disabled."));
        }
    }

    @Override
    public String getUsage() {
        return "toggle <module name>";
    }

    public void onEvent(Event event) {
    }
}

