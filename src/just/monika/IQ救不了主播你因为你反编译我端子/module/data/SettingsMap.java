/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.data;

import java.util.HashMap;

public class SettingsMap
extends HashMap<String, Setting> {
    public void update(HashMap<String, Setting> newMap) {
        for (String key : newMap.keySet()) {
            if (!this.containsKey(key)) continue;
            ((Setting)this.get(key)).update(newMap.get(key));
        }
    }
}

