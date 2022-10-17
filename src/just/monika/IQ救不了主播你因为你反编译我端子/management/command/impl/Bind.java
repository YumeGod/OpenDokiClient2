package just.monika.IQ救不了主播你因为你反编译我端子.management.command.impl;

import just.monika.IQ救不了主播你因为你反编译我端子.DokiDokiLegitClient;
import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.management.command.Command;
import just.monika.IQ救不了主播你因为你反编译我端子.management.keybinding.KeyMask;
import just.monika.IQ救不了主播你因为你反编译我端子.management.keybinding.Keybind;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.util.misc.ChatUtil;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;

public class Bind extends Command
{
    public Bind(final String[] names, final String description) {
        super(names, description);
    }
    
    @Override
    public void fire(final String[] args) {
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
            final Keybind key = module.getKeybind();
            ChatUtil.printChat("�c[�fS�c]�7 " + module.getName() + ": " + ((key.getMask() == KeyMask.None) ? "" : (key.getMask().name() + " + ")) + key.getKeyStr());
        }
        else if (args.length == 2) {
            final int keyIndex = Keyboard.getKeyIndex(args[1].toUpperCase());
            final Keybind keybind = new Keybind(module, keyIndex);
            module.setKeybind(keybind);
            final Keybind key2 = module.getKeybind();
            ChatUtil.printChat("�c[�fS�c]�7 Set " + module.getName() + " to " + ((key2.getMask() == KeyMask.None) ? "" : (key2.getMask().name() + " + ")) + key2.getKeyStr());
        }
        else if (args.length == 3) {
            final int keyIndex = Keyboard.getKeyIndex(args[1].toUpperCase());
            final KeyMask mask = KeyMask.getMask(args[2]);
            final Keybind keybind2 = new Keybind(module, keyIndex, mask);
            module.setKeybind(keybind2);
            final Keybind key3 = module.getKeybind();
            ChatUtil.printChat("�c[�fS�c]�7 Set " + module.getName() + " to " + ((key3.getMask() == KeyMask.None) ? "" : (key3.getMask().name() + " + ")) + key3.getKeyStr());
        }
        Module.saveStatus();
    }
    
    @Override
    public String getUsage() {
        return "bind <Module> " + EnumChatFormatting.ITALIC + "[optional]" + EnumChatFormatting.RESET + "<Key> " + EnumChatFormatting.ITALIC + "[optional]" + EnumChatFormatting.RESET + "<Mask>";
    }
    
    @Override
    public void onEvent(final Event event) {
    }
}
