package me.oribuin.commands.admin.channel;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.EnumSet;

public class ChClone extends Command {
    public ChClone() {
        this.name = "ChClone";
        this.help = "Clone a channel.";
        this.aliases = new String[]{"Clone"};
        this.botPermissions = new Permission[]{Permission.MANAGE_ROLES};
        this.ownerCommand = Settings.OfflineMode;
    }

    protected void execute(CommandEvent e) {

        OffsetDateTime timec = e.getMessage().getTimeCreated();

        EmbedBuilder Clone = new EmbedBuilder()
                .setColor(Color.decode(Info.COLOR))
                .setAuthor("Purged Channel", "https://github.com/Oribuin/Lil-Ori/")
                .setFooter("Lil' Ori Bot v" + Info.VERSION)
                .setDescription("Clone Created by: " + e.getAuthor().getAsMention() + "\n" +
                        "Cloned at: " + timec.getDayOfWeek() + " At: " + timec.getHour() + ":" + timec.getMinute());

        e.getMessage().getTextChannel().delete().queue();
        e.getGuild().createTextChannel(e.getChannel().getName())
                .setTopic(e.getTextChannel().getTopic())
                .setNSFW(e.getTextChannel().isNSFW())
                .setPosition(e.getTextChannel().getPosition())
                .setParent(e.getTextChannel().getParent()).queue(m -> {
            m.sendMessage(e.getAuthor().getAsMention()).queue();
            m.sendMessage(Clone.build()).queue();
        });
    }
}
