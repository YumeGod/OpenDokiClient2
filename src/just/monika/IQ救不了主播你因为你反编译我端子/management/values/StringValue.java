/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.management.values;

import java.lang.reflect.Field;

import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;

public class StringValue
extends Value<String> {
    public StringValue(String name, String value, Module module) {
        super(name, value, module);
    }

    @Override
    public void setValue(String value) {
        super.setValue(value);
        for (Field field : this.getModule().getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (!field.isAnnotationPresent(Value.Val.class) || !field.getName().equalsIgnoreCase(this.getValueName())) continue;
            try {
                if (!field.getType().isAssignableFrom(String.class)) continue;
                field.set(this.getModule(), value);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

