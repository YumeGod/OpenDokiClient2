/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.management.command.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.management.command.Command;
import just.monika.IQ救不了主播你因为你反编译我端子.util.FileUtils;
import just.monika.IQ救不了主播你因为你反编译我端子.util.StringConversions;
import just.monika.IQ救不了主播你因为你反编译我端子.util.misc.ChatUtil;
import net.minecraft.item.Item;

public class Janitor
extends Command {
    private static final File CLEAN_DIR = FileUtils.getConfigFile("Janitor");

    public Janitor(String[] names, String description) {
        super(names, description);
    }

    @Override
    public void fire(String[] args) {
        if (args == null) {
            this.printUsage();
            return;
        }
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("a")) {
                if (args.length == 2) {
                    if (StringConversions.isNumeric(args[1])) {
                        just.monika.IQ救不了主播你因为你反编译我端子.module.impl.player.Janitor.blacklistedItems.add(Integer.parseInt(args[1]));
                        ChatUtil.printChat("\u00a7c[\u00a7fS\u00a7c]\u00a77 Added p" + args[1] + "] to blacklist.");
                    }
                } else if (this.mc.thePlayer.inventory.getCurrentItem() != null) {
                    Item item = this.mc.thePlayer.inventory.getCurrentItem().getItem();
                    int i = Item.getIdFromItem(item);
                    just.monika.IQ救不了主播你因为你反编译我端子.module.impl.player.Janitor.blacklistedItems.add(i);
                    ChatUtil.printChat("\u00a7c[\u00a7fS\u00a7c]\u00a77 Added [" + Item.getIdFromItem(item) + "] to blacklist.");
                }
                Janitor.saveIDs();
                return;
            }
            if (args[0].equalsIgnoreCase("del") || args[0].equalsIgnoreCase("d")) {
                if (args.length == 2) {
                    if (StringConversions.isNumeric(args[1]) && just.monika.IQ救不了主播你因为你反编译我端子.module.impl.player.Janitor.blacklistedItems.contains(Integer.parseInt(args[1]))) {
                        just.monika.IQ救不了主播你因为你反编译我端子.module.impl.player.Janitor.blacklistedItems.remove(new Integer(Integer.parseInt(args[1])));
                        ChatUtil.printChat("\u00a7c[\u00a7fS\u00a7c]\u00a77 Removed [" + args[1] + "] from blacklist.");
                    }
                } else if (this.mc.thePlayer.inventory.getCurrentItem() != null && just.monika.IQ救不了主播你因为你反编译我端子.module.impl.player.Janitor.blacklistedItems.contains(Item.getIdFromItem(this.mc.thePlayer.inventory.getCurrentItem().getItem()))) {
                    Item item = this.mc.thePlayer.inventory.getCurrentItem().getItem();
                    int i = Item.getIdFromItem(item);
                    just.monika.IQ救不了主播你因为你反编译我端子.module.impl.player.Janitor.blacklistedItems.remove(new Integer(i));
                    ChatUtil.printChat("\u00a7c[\u00a7fS\u00a7c]\u00a77 Removed [" + Item.getIdFromItem(item) + "] from blacklist.");
                    return;
                }
                Janitor.saveIDs();
                return;
            }
            if (args[0].equalsIgnoreCase("clear") && !just.monika.IQ救不了主播你因为你反编译我端子.module.impl.player.Janitor.blacklistedItems.isEmpty()) {
                just.monika.IQ救不了主播你因为你反编译我端子.module.impl.player.Janitor.blacklistedItems.clear();
                ChatUtil.printChat("\u00a7c[\u00a7fS\u00a7c]\u00a77 Cleared blacklist!");
                Janitor.saveIDs();
            }
        }
        this.printUsage();
    }

    public static void saveIDs() {
        ArrayList<String> fileContent = new ArrayList<String>();
        for (Integer integ : just.monika.IQ救不了主播你因为你反编译我端子.module.impl.player.Janitor.blacklistedItems) {
            fileContent.add(integ.toString());
        }
        FileUtils.write(CLEAN_DIR, fileContent, true);
    }

    public static void loadIDs() {
        just.monika.IQ救不了主播你因为你反编译我端子.module.impl.player.Janitor.blacklistedItems.clear();
        try {
            List<String> fileContent = FileUtils.read(CLEAN_DIR);
            for (String line : fileContent) {
                if (!StringConversions.isNumeric(line)) continue;
                just.monika.IQ救不了主播你因为你反编译我端子.module.impl.player.Janitor.blacklistedItems.add(Integer.parseInt(line));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUsage() {
        return "add/del [Optional] <itemID>";
    }

    public void onEvent(Event event) {
    }
}

