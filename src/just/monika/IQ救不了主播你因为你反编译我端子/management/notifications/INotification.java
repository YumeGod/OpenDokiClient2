/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.management.notifications;

public interface INotification {
    public String getHeader();

    public String getSubtext();

    public long getStart();

    public long getDisplayTime();

    public Notifications.Type getType();

    public float getX();

    public float getTarX();

    public float getTarY();

    public void setX(int var1);

    public void setTarX(int var1);

    public void setY(int var1);

    public long checkTime();

    public float getY();

    public long getLast();

    public void setLast(long var1);
}

