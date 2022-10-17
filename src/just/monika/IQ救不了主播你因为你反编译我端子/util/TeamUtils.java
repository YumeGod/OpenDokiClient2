/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.entity.player.EntityPlayer;

public class TeamUtils {
    public static boolean isTeam(EntityPlayer e, EntityPlayer e2) {
        return e.getDisplayName().getFormattedText().contains("\u00a7" + TeamUtils.isTeam(e)) && e2.getDisplayName().getFormattedText().contains("\u00a7" + TeamUtils.isTeam(e));
    }

    private static String isTeam(EntityPlayer player) {
        Matcher m = Pattern.compile("\u00a7(.).*\u00a7r").matcher(player.getDisplayName().getFormattedText());
        if (m.find()) {
            return m.group(1);
        }
        return "f";
    }
}

