package io.github.teamdevintia.round3.helper;

import io.github.teamdevintia.round3.enums.FontInfo;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author Shad0wCore
 */
public final class ChatHelper {

    private int CENTER_PX = 154, MAX_PX = 275, CLEAR_LINES = 300;
    private String CLEAR_SPACE = " ";

    public void appendCentered(Player player, String text) {
        text = ChatColor.translateAlternateColorCodes('&', text);
        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;
        int charIndex = 0;
        int lastSpaceIndex = 0;
        String toSendAfter = null;
        String recentColorCode = "";

        for (char c : text.toCharArray()) {
            if (c == '§') {
                previousCode = true;
                continue;
            } else if (previousCode == true) {
                previousCode = false;
                recentColorCode = "§" + c;
                if (c == 'l' || c == 'L') {
                    isBold = true;
                    continue;
                } else isBold = false;
            } else if (c == ' ') lastSpaceIndex = charIndex;
            else {
                FontInfo dFI = FontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }

            if (messagePxSize >= MAX_PX) {
                toSendAfter = recentColorCode + text.substring(lastSpaceIndex + 1, text.length());
                text = text.substring(0, lastSpaceIndex + 1);
                break;
            }

            charIndex++;
        }

        StringBuilder sb = new StringBuilder();
        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = FontInfo.SPACE.getLength() + 1;
        int compensated = 0;

        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }

        player.sendMessage(sb.toString() + text);

        if (toSendAfter != null) {
            appendCentered(player, toSendAfter);
        }
    }

    public void appendCentered(String text) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            this.appendCentered(player, text);
        }
    }

    public void append(TextComponent textComponent) {
        for (Player player : Bukkit.getOnlinePlayers())
            player.spigot().sendMessage(textComponent);
    }

    public void append(String text) {
        Bukkit.broadcastMessage(text);
    }

    public void space(int lines) {
        for (int i = 0; i < lines; i++)
            Bukkit.broadcastMessage(this.CLEAR_SPACE);
    }

    public void clear() {
        for (int i = 0; i < this.CLEAR_LINES; i++)
            Bukkit.broadcastMessage(this.CLEAR_SPACE);
    }


}
