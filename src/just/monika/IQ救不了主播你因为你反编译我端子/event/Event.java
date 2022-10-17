/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.event;

public abstract class Event {
    protected boolean cancelled;

    public void fire() {
        this.cancelled = false;
        EventSystem.fire(this);
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }
}

