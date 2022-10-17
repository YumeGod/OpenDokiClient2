/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.util.misc;

public class Timer {
    private long previousTime = -1;

    public boolean check(float milliseconds) {
        return (float)this.getTime() >= milliseconds;
    }

    public long getTime() {
        return this.getCurrentTime() - this.previousTime;
    }

    public void reset() {
        this.previousTime = this.getCurrentTime();
    }

    public long getCurrentTime() {
        return System.currentTimeMillis();
    }
}

