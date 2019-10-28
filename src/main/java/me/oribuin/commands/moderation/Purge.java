package me.oribuin.commands.moderation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.sql.Time;
import java.time.OffsetDateTime;
import java.util.List;


public class Purge extends Command {
    public Purge() {
        this.name = "Purge";
        this.aliases = new String[]{"clear"};
        this.help = "Purge the channel of messages - ;purge <amount>";
        this.cooldown = 2;
        this.botPermissions = new Permission[]{Permission.MESSAGE_MANAGE, Permission.VIEW_CHANNEL, Permission.MESSAGE_HISTORY, Permission.MANAGE_CHANNEL};
        this.userPermissions = new Permission[]{Permission.MESSAGE_MANAGE, Permission.VIEW_CHANNEL, Permission.MESSAGE_HISTORY, Permission.MANAGE_CHANNEL};
        this.ownerCommand = Settings.OfflineMode;
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent e) {

        String[] args = e.getMessage().getContentRaw().split(" ");
        TextChannel channel = e.getTextChannel();
        OffsetDateTime timec = e.getMessage().getTimeCreated();


        try {

            if (args.length < 2) {
                e.reply("Please specify the amount of messages you wish to clear.");
            } else {

                EmbedBuilder Purged = new EmbedBuilder()
                        .setColor(Color.decode(Info.COLOR))
                        .setAuthor("Purged Messages", "https://github.com/Oribuin/Lil-Ori/", "" + e.getJDA().getSelfUser().getAvatarUrl())
                        .setFooter("Lil' Ori v" + Info.VERSION)
                        .setDescription("Amount: " + args[1] + "\n" +
                                "Purged by: " + e.getAuthor().getAsMention() + "\n" +
                                "Purged at: " + timec.getDayOfWeek() + " At: " + timec.getHour() + ":" + timec.getMinute());

                List<Message> msgs = channel.getHistory().retrievePast(Integer.parseInt(args[1]) + 1).complete();

                e.getMessage().delete().queue();

                channel.deleteMessages(msgs).queue();
                e.reply(Purged.build());
            }

        } catch (IllegalArgumentException err) {

            if (e.toString().startsWith("java.lang.IllegalArgumentException:")) {
                e.reply("You may only clear 2-100 messages at a time.");
            } else {
                e.reply("Messages older than 2 weeks cannot be cleared.");
            }
        }
    }
}
