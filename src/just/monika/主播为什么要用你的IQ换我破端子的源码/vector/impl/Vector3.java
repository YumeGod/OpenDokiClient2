/*
 * Decompiled with CFR 0_122.
 */
package just.monika.主播为什么要用你的IQ换我破端子的源码.vector.impl;

import just.monika.主播为什么要用你的IQ换我破端子的源码.vector.Vector;

public class Vector3<T extends Number>
extends Vector<Number> {
    public Vector3(T x, T y, T z) {
        super(x, y, z);
    }

    public Vector2<T> toVector2() {
        return new Vector2(this.getX(), this.getY());
    }
}

