/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.management.animate;

public class Rotate {
    private float angle;
    private long lastMS;

    public Rotate(float angle) {
        this.angle = angle;
    }

    public void interpolate(float targetAngle, int speed) {
        long currentMS = System.currentTimeMillis();
        long delta = currentMS - this.lastMS;
        this.lastMS = currentMS;
        this.angle = AnimationUtil.calculateCompensation(targetAngle, this.angle, delta, speed);
    }

    public float getAngle() {
        return this.angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}

