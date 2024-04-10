package Controles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

public class Cooldown {
    public long lastTriggerTime;
    private long cooldownDurationMillis;

    public Cooldown(long cooldownDurationMillis) {
        this.cooldownDurationMillis = cooldownDurationMillis;
        this.lastTriggerTime = 0; // Initialize last trigger time to 0
    }

    public boolean isOnCooldown() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - lastTriggerTime) < cooldownDurationMillis;
    }


    public void trigger() {
        lastTriggerTime = System.currentTimeMillis();
    }

    public long timeRemaining() {
        long currentTime = System.currentTimeMillis();
        long timeElapsed = currentTime - lastTriggerTime;
        long remainingTime = cooldownDurationMillis - timeElapsed;
        return Math.max(0, remainingTime);
    }
}
