package io.github.teamdevintia.round3.helper;

import net.minecraft.server.v1_10_R1.IChatBaseComponent;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author Shad0wCore
 */
public final class FormatterHelper {

    public static int uptimeDuration() {
        return (int) TimeUnit.MILLISECONDS.toSeconds(ManagementFactory.getRuntimeMXBean().getUptime());
    }

    public static IChatBaseComponent formattedTextMojangSON(String text) {
        return IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + text + "\"}");
    }

    public static String formattedTimePattern(int duration) {
        int seconds = (int) (TimeUnit.SECONDS.toMillis(duration) / 1000) % 60;
        int minutes = (int) ((TimeUnit.SECONDS.toMillis(duration) / (1000 * 60)) % 60);

        if (duration < 60) {
            return seconds + " " + (seconds == 1 ? "Sekunde" : "Sekunden");
        }

        return minutes + " " + (minutes == 1 ? "Minute" : "Minuten") + ", " + seconds + " " + (seconds == 1 ? "Sekunde" : "Sekunden");
    }

}
