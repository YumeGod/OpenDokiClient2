/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.other;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventTick;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.Setting;
import just.monika.IQ救不了主播你因为你反编译我端子.util.Timer;
import just.monika.IQ救不了主播你因为你反编译我端子.util.misc.ChatUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;

public class Murder
extends Module {
    private String MESSAGE = "MESSAGE";
    private just.monika.IQ救不了主播你因为你反编译我端子.util.Timer timer = new Timer();

    public Murder(ModuleData data) {
        super(data);
        this.settings.put(this.MESSAGE, new Setting<String>(this.MESSAGE, "{P} is trying to kill me!", "Sends a mesage in chat when murderer is found. {P} = Name"));
    }

    @RegisterEvent(events={EventTick.class})
    public void onEvent(Event event) {
        for (Object o : Murder.mc.theWorld.loadedEntityList) {
            EntityPlayer ent;
            if (!(o instanceof EntityPlayer) || !this.timer.delay(15000.0f) || (ent = (EntityPlayer)o) == Murder.mc.thePlayer || ent.getCurrentEquippedItem() == null || !(ent.getCurrentEquippedItem().getItem() instanceof ItemSword) || ent.isMurderer) continue;
            ent.isMurderer = true;
            String customChat = (String)((Setting)this.settings.get(this.MESSAGE)).getValue();
            customChat = customChat.replace("{P}", "%s");
            ChatUtil.sendChat(String.format(customChat, ent.getName()));
            break;
        }
    }
}

