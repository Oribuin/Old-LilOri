package me.oribuin.events;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ReconnectedEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Startup extends ListenerAdapter {
    public void onReady(ReadyEvent event) {
        event.getJDA().getPresence().setActivity(Activity.playing("in " + event.getGuildTotalCount() + " guilds | ;help"));
    }

    public void onGuildJoin(GuildJoinEvent event) {
        event.getJDA().getPresence().setActivity(Activity.playing("in " + event.getJDA().getGuilds().size() + " guilds | ;help"));
    }

    public void onGuildLeave(GuildLeaveEvent event) {
        event.getJDA().getPresence().setActivity(Activity.playing("in " + event.getJDA().getGuilds().size() + " guilds | ;help"));
    }
}
