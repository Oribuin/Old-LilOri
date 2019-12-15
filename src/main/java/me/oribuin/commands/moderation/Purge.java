package me.oribuin.commands.moderation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.sql.Time;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;


public class Purge extends Command {
    public Purge() {
        this.name = "Purge";
        this.aliases = new String[]{"clear"};
        this.help = "Purge the channel of messages - ;purge <messages/channel> <amount/#channel>";
        this.cooldown = 2;
        this.botPermissions = new Permission[]{Permission.MESSAGE_MANAGE, Permission.VIEW_CHANNEL, Permission.MESSAGE_HISTORY, Permission.MANAGE_CHANNEL};
        this.userPermissions = new Permission[]{Permission.MESSAGE_MANAGE, Permission.VIEW_CHANNEL, Permission.MESSAGE_HISTORY, Permission.MANAGE_CHANNEL};
        this.ownerCommand = Settings.OfflineMode;
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent e) {

        // Variables
        String[] args = e.getMessage().getContentRaw().split(" ");
        TextChannel channel = e.getTextChannel();
        OffsetDateTime time = e.getMessage().getTimeCreated();


        try {

            // If there isn't sufficient arguments.
            if (args.length < 3 || e.getMessage().getContentRaw().equals(args[1])) {
                e.reply(e.getClient().getWarning() + " Correct usage: ;purge <msgs/channel> <amount/#channel>");
            } else {
                // if second argument is "messages"
                if (args[1].equalsIgnoreCase("messages") || args[1].equalsIgnoreCase("msgs")) {

                    if (args[2].isEmpty()) {
                        e.reply(e.getClient().getWarning() + " You must include the number of messages you wish to purge.");
                    }
                    // get the messages list
                    List<Message> msgs = channel.getHistory().retrievePast(Integer.parseInt(args[2]) + 1).complete();

                    // if the message size is more then 100
                    if (msgs.size() > 100) {
                        e.reply(e.getClient().getWarning() + " You cannot purge messages over 100");
                        // if they're lower than 3
                    } else if (msgs.size() < 3) {
                        e.reply(e.getClient().getWarning() + " You cannot purge less than 3 messages.");
                    } else {

                        // purge channel messages

                        e.getMessage().delete().queue(m -> {
                            e.getChannel().purgeMessages(msgs);
                        });
                        // define embed
                        EmbedBuilder Messages = new EmbedBuilder()
                                .setColor(Color.decode(Info.COLOR))
                                .setAuthor("Purged Messages", "https://github.com/Oribuin/Lil-Ori/")
                                .setFooter("Lil' Ori v" + Info.VERSION)
                                .setDescription("Amount: " + args[2] + "\n" +
                                        "Purged by: " + e.getAuthor().getAsMention() + "\n" +
                                        "Purged at: " + time.getDayOfWeek() + " At: " + time.getHour() + ":" + time.getMinute());
                        
                        // Send the Embed in channel
                        e.reply(Messages.build());

                    }
                    // if second argument is channel
                } else if (args[1].equalsIgnoreCase("channel")) {
                    // define second embed
                    EmbedBuilder Channel = new EmbedBuilder()
                            .setColor(Color.decode(Info.COLOR))
                            .setAuthor("Purged Channel", "https://github.com/Oribuin/Lil-Ori/")
                            .setFooter("Lil' Ori v" + Info.VERSION)
                            .setDescription("Channel: #" + e.getMessage().getMentionedChannels().get(0).getName() + "\n" +
                                    "Purged by: " + e.getAuthor().getAsMention() + "\n" +
                                    "Purged at: " + time.getDayOfWeek() + " At: " + time.getHour() + ":" + time.getMinute());

                    // if the second argument is a channel being mentioned
                    if (args[2].equals(e.getMessage().getMentionedChannels().get(0).getAsMention())) {

                        // delete the channel
                        e.getMessage().getMentionedChannels().get(0).delete().queue();

                        // create new text channel
                        e.getGuild().createTextChannel(e.getMessage().getMentionedChannels().get(0).getName())
                                .setParent(e.getMessage().getMentionedChannels().get(0).getParent())
                                .setPosition(e.getMessage().getMentionedChannels().get(0).getPosition())
                                .setNSFW(e.getMessage().getMentionedChannels().get(0).isNSFW())
                                .setSlowmode(e.getMessage().getMentionedChannels().get(0).getSlowmode()).queue(m -> {
                            m.sendMessage(e.getAuthor().getAsMention()).queue();
                            m.sendMessage(Channel.build()).queue();
                        });
                    } else {
                        // tell them the correct usage of the command
                        e.reply(e.getClient().getWarning() + " Correct usage: ;purge <msgs/channel> <amount/#channel>");
                    }
                }

            }
        } catch (Exception err) {
            err.printStackTrace();
            e.reply(e.getClient().getError() + " There was an error purging.\n```" + err.toString() + "\n```");
        }
    }
}
