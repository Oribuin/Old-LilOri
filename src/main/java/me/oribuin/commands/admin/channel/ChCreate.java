package me.oribuin.commands.admin.channel;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;

import java.awt.*;
import java.time.OffsetDateTime;

public class ChCreate extends Command {
    public ChCreate() {
        this.name = "ChCreate";
        this.aliases = new String[]{"create"};
        this.help = "Create a new Channel";
        this.cooldown = 5;
        this.botPermissions = new Permission[]{Permission.MANAGE_CHANNEL, Permission.VIEW_CHANNEL};
        this.userPermissions = new Permission[]{Permission.MANAGE_CHANNEL, Permission.MANAGE_SERVER, Permission.MANAGE_PERMISSIONS};
        this.ownerCommand = Settings.OfflineMode;
    }

    @Override
    protected void execute(CommandEvent e) {
        OffsetDateTime timec = e.getMessage().getTimeCreated();

        EmbedBuilder Embed = new EmbedBuilder()
                .setColor(Color.decode(Info.COLOR))
                .setAuthor("Created Channel", "https://github.com/Oribuin/Lil-Ori/")
                .setFooter("Lil' Ori v" + Info.VERSION)
                .setDescription("Channel Created by: " + e.getAuthor().getAsMention() + "\n" +
                        "Created at: " + timec.getDayOfWeek() + " At: " + timec.getHour() + ":" + timec.getMinute());

        try {

            String[] args = e.getMessage().getContentRaw().split(" ");

            if (args[1].isEmpty()) {
                e.reply("You must include a channel name.");
            } else {
                e.getGuild().createTextChannel(args[1]).queue(m -> {
                    m.sendMessage(e.getAuthor().getAsMention()).queue();
                    m.sendMessage(Embed.build()).queue();
                });
            }
        } catch (Exception err) {

            e.reply("You have caught an Exception, Have you typed the command correctly?");

            EmbedBuilder ex = new EmbedBuilder()
                    .setColor(Color.RED)
                    .setAuthor("Exception Error")
                    .setDescription(err.toString());
            e.reply(ex.build());
        }
    }
}
