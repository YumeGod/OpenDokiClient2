/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.management.animate;

public class Opacity {
    private float opacity;
    private long lastMS;

    public Opacity(int opacity) {
        this.opacity = opacity;
        this.lastMS = System.currentTimeMillis();
    }

    public void interpolate(int targetOpacity) {
        this.opacity = (int)AnimationUtil.calculateCompensation(targetOpacity, this.opacity, 16, 5);
    }

    public void interp(int targetOpacity, int speed) {
        long currentMS = System.currentTimeMillis();
        long delta = currentMS - this.lastMS;
        this.lastMS = currentMS;
        if (delta < 16) {
            delta = 16;
        }
        this.opacity = (double)AnimationUtil.calculateCompensation(targetOpacity, this.opacity, delta, speed) < 0.5 ? 0.5f : AnimationUtil.calculateCompensation(targetOpacity, this.opacity, delta, speed);
    }

    public float getOpacity() {
        return this.opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }
}

