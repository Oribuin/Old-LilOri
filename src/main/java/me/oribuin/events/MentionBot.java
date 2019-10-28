package me.oribuin.events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

public class MentionBot extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] responses = {
                "Hello, " + event.getAuthor().getAsMention() + "! <a:PanGLoveG:623375387833139210>",
                "How are you? <a:PanGLoveG:623375387833139210>",
                "How can I help? <a:PanGLoveG:623375387833139210>",
                "¡Hola! <a:PanGLoveG:623375387833139210>",
                "¡Hola, mucho gustó! <a:PanGLoveG:623375387833139210>",
                "¿Como estas? <a:PanGLoveG:623375387833139210>",

        };

        Integer randInt = new Random().nextInt(responses.length);

        if (event.getMessage().getContentRaw().contains("<@581203970203189269>")) {
            event.getChannel().sendMessage(responses[randInt]).queue();
        }
    }
}