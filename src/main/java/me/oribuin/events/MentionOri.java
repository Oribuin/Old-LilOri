package me.oribuin.events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MentionOri extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        if (event.getMessage().getContentRaw().contains("<@345406020450779149>")) {
            event.getMessage().addReaction(":Ping:623499202252505098").queue();
        }
    }
}
