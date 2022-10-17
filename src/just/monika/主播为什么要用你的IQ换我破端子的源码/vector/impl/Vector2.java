/*
 * Decompiled with CFR 0_122.
 */
package just.monika.主播为什么要用你的IQ换我破端子的源码.vector.impl;

import just.monika.主播为什么要用你的IQ换我破端子的源码.vector.Vector;

public class Vector2<T extends Number>
extends Vector<Number> {
    public Vector2(T x, T y) {
        super(x, y, 0);
    }

    public Vector3<T> toVector3() {
        return new Vector3(this.getX(), this.getY(), this.getZ());
    }
}

