package io.github.teamdevintia.round3.helper;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public final class TextCompFactory {

    private TextComponent textComponent;

    public TextCompFactory text(String text) {
        this.textComponent.setText(text);
        return this;
    }

    public TextCompFactory clickEvent(ClickEvent.Action action, String extra) {
        this.textComponent.setClickEvent(new ClickEvent(action, extra));
        return this;
    }

    public TextCompFactory hoverEvent(HoverEvent.Action action, String extra) {
        this.textComponent.setHoverEvent(new HoverEvent(action, new ComponentBuilder(extra).create()));
        return this;
    }

    public TextCompFactory hoverEvent(HoverEvent.Action action, String... extra) {
        ComponentBuilder componentBuilder = new ComponentBuilder(extra[0]);

        for (int i = 1; i < extra.length; i++) {
            componentBuilder.append("\n" + extra[i]);
        }

        this.textComponent.setHoverEvent(new HoverEvent(action, componentBuilder.create()));
        return this;
    }

    public TextComponent build(String prePosition, String postPosition) {
        TextComponent origin = new TextComponent("");
        origin.addExtra(prePosition);
        origin.addExtra(this.textComponent);
        origin.addExtra(postPosition);
        return origin;
    }

    public TextCompFactory rebind() {
        this.textComponent = new TextComponent();
        return this;
    }

}
