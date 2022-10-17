/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.management.notifications;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class Notifications {
    private static Notifications instance = new Notifications();
    private List<INotification> notifications = new CopyOnWriteArrayList<INotification>();
    private NotificationRenderer renderer = new NotificationRenderer();

    private Notifications() {
        instance = this;
    }

    public static Notifications getManager() {
        return instance;
    }

    public void post(String header, String subtext) {
        this.post(header, subtext, 2500);
    }

    public void post(String header, String subtext, Type type) {
        this.post(header, subtext, 2500, type);
    }

    public void post(String header, String subtext, long displayTime) {
        this.post(header, subtext, displayTime, Type.INFO);
    }

    public void post(String header, String subtext, long displayTime, Type type) {
        this.notifications.add(new Notification(header, subtext, displayTime, type));
    }

    public void updateAndRender() {
        if (this.notifications.isEmpty()) {
            return;
        }
        this.renderer.draw(this.notifications);
    }

    public static enum Type {
        NOTIFY,
        WARNING,
        INFO;
        

        private Type() {
        }
    }

}

