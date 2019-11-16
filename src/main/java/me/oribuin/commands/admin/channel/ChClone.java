package me.oribuin.commands.admin.channel;
// Imports, Obviously

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.EnumSet;

public class ChClone extends Command {
    public ChClone() {
        // Typical stuff declaring everything.
        this.name = "ChClone";
        this.help = "Clone a channel.";
        this.aliases = new String[]{"Clone"};
        this.userPermissions = new Permission[]{Permission.ADMINISTRATOR};
        this.botPermissions = new Permission[]{Permission.MANAGE_CHANNEL, Permission.MESSAGE_MANAGE, Permission.VIEW_CHANNEL};
        this.ownerCommand = Settings.OfflineMode;
    }

    protected void execute(CommandEvent e) {

        // Making the code shortened by using "timec" as a OffsetDateTime placeholder that replaces "e.getMessage().getTimeCreated()"
        OffsetDateTime timec = e.getMessage().getTimeCreated();

        // Arguments Declared, Splits them apart so its not all one sentence.
        String[] args = e.getMessage().getContentRaw().split(" ");

        // If There isn't any args it clones the channel the message is sent in.
        if (args.length < 2
                //|| args[1].equals(!e.getMessage().getMentionedChannels().get(0).getAsMention())
                || args[1].isEmpty()) {
            /// Embed Created - The following comments are the exact same as before pretty much.
            EmbedBuilder Clone1 = new EmbedBuilder()
                    .setColor(Color.decode(Info.COLOR))
                    .setAuthor("Cloned Channel", "https://github.com/Oribuin/Lil-Ori/")
                    .setFooter("Lil' Ori Bot v" + Info.VERSION)
                    .setDescription("Clone Created by: " + e.getAuthor().getAsMention() + "\n" +
                            "Cloned Channel: #" + e.getChannel().getName() + "\n" +
                            "Cloned at: " + timec.getDayOfWeek() + " At: " + timec.getHour() + ":" + timec.getMinute());

            // Create the channel clone, Copies the previous channel's info before it is removed.
            e.getGuild().createTextChannel(e.getChannel().getName())
                    .setTopic(e.getTextChannel().getTopic())
                    .setPosition(e.getTextChannel().getPosition())
                    .setNSFW(e.getTextChannel().isNSFW())
                    .setSlowmode(e.getTextChannel().getSlowmode())
                    .setParent(e.getTextChannel().getParent()).queue(m -> {
                        // Once it's done it pings the message author and sends Embed
                m.sendMessage(e.getAuthor().getAsMention()).queue();
                m.sendMessage(Clone1.build()).queue();

                // Deletes Channel.
                e.getMessage().getTextChannel().delete().queue();

                // Sends Embed to owner just in-case there are complications.
                m.getGuild().getOwner().getUser().openPrivateChannel().queue(msg -> {
                    msg.sendMessage(Clone1.build()).queue();
                });
            });

            // If there is an argument and it is a channel that is mentioned
        } else if (args[1].equals(e.getMessage().getMentionedChannels().get(0).getAsMention())) {

            // Just in-case?
            if (!e.getMessage().getMentionedChannels().get(0).getType().equals(ChannelType.TEXT)) {
                e.reply(e.getAuthor().getAsMention() + ", You cannot clone a channel that isn't a Text Channel.");

            } else {

                // Same format as before
                try {
                    TextChannel channel = e.getMessage().getMentionedChannels().get(0);

                    EmbedBuilder Clone2 = new EmbedBuilder()
                            .setColor(Color.decode(Info.COLOR))
                            .setAuthor("Cloned Channel", "https://github.com/Oribuin/Lil-Ori/")
                            .setFooter("Lil' Ori Bot v" + Info.VERSION)
                            .setDescription("Clone Created by: " + e.getAuthor().getAsMention() + "\n" +
                                    "Cloned Channel: #" + channel.getName() + "\n" +
                                    "Cloned at: " + timec.getDayOfWeek() + " At: " + timec.getHour() + ":" + timec.getMinute());

                    // Adds a reaction to clarify that the channel was cloned, Just in-case?
                    e.getMessage().addReaction("✅").queue();

                    // Same thing as before except it uses the channel mentioned instead of the channel the message was send in.
                    e.getGuild().createTextChannel(channel.getName())
                            .setTopic(channel.getTopic())
                            .setPosition(channel.getPosition())
                            .setNSFW(channel.isNSFW())
                            .setSlowmode(channel.getSlowmode())
                            .setParent(channel.getParent()).queue(m -> {

                        m.sendMessage(e.getAuthor().getAsMention()).queue();
                        m.sendMessage(Clone2.build()).queue();

                        // Deletes Channel.
                        e.getMessage().getMentionedChannels().get(0).delete().queue();

                        m.getGuild().getOwner().getUser().openPrivateChannel().queue(msg -> {
                            msg.sendMessage(Clone2.build()).queue();
                        });
                    });
                } catch (Exception err) {
                    // Just in-case again?
                    e.reply("You have caught an exception error.\n\n" + err.toString());
                }
            }
        } else {
            // Just In-case
            e.reply("You haven't mentioned a correct channel, Are you sure you input the right args? `;chclone #<channel>");
        }
    }
}
