package me.oribuin.commands.moderation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.HierarchyException;

import java.awt.*;
import java.util.Objects;

public class Kick extends Command {
    public Kick() {
        this.name = "Kick";
        this.help = "Kick a member from the guild.";
        this.cooldown = 3;
        this.botPermissions = new Permission[]{Permission.KICK_MEMBERS};
        this.userPermissions = new Permission[]{Permission.KICK_MEMBERS};
        this.ownerCommand = Settings.OfflineMode;
    }

    @Override
    public void execute(CommandEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");
        try {
            // If it doesn't include all arguments.
            if (args.length < 3 || e.getMessage().getContentRaw().equals(args[1])) {
                e.reply(e.getClient().getWarning() + " Correct usage: ;kick @user <reason>");
                return;

                // If args[1] doesn't equal a user to kick
            } else {
                e.getMessage().getMentionedMembers();
                if (e.getMessage().getMentionedMembers().isEmpty()) {
                    e.reply(e.getClient().getWarning() + " Correct usage ;kick @user <reason>");
                    return;

                    // If the user mentioned is the message author
                } else if (e.getMessage().getMentionedMembers().get(0).equals(e.getAuthor())) {
                    e.reply(e.getClient().getWarning() + " You cannot kick yourself.");
                    return;

                    // If User mentioned is owner, or has the same/higher role than user executing command.
                } else if (e.getMessage().getMentionedMembers().get(0).isOwner()
                        || e.getMessage().getMentionedMembers().get(0).getRoles().get(0).getPosition() > e.getGuild().getMember(e.getAuthor()).getRoles().get(0).getPosition()
                        || e.getMessage().getMentionedMembers().get(0).getRoles().get(0).getPosition() > e.getSelfMember().getRoles().get(0).getPosition()) {

                    e.reply(e.getAuthor().getAsMention() + ", You cannot kick this user due to Role Hierarchy.");
                    return;
                    // If the user is a bot, this will prevent errors with DMs.
                } else if (e.getMessage().getMentionedMembers().get(0).getUser().isBot()) {
                    e.reply(e.getClient().getWarning() + " I cannot kick other bots!");
                    return;
                }
            }

            // Embed Defining
            EmbedBuilder Embed = new EmbedBuilder()
                    .setColor(Color.decode(Info.COLOR))
                    .setAuthor("You have been kicked!")
                    .setFooter("Lil Ori v" + Info.VERSION);
            // Define new Embed description
            Embed.setDescription("Kicked from: " + e.getGuild().getName() + " (" + e.getGuild().getId() + ")\n" +
                    "Kicked By: " + e.getMessage().getAuthor().getAsTag() + "\n" +
                    "Reason: " + e.getMessage().getContentRaw().substring(args[0].length() + args[1].length() + 2));

            // Tell user they were kicked
            e.getMessage().getMentionedMembers().get(0).getUser().openPrivateChannel().queue(channel -> {
                channel.sendMessage(Embed.build()).queue();
            });

            // Kick the user
            e.getMessage().getMentionedMembers().get(0).kick(e.getMessage().getContentRaw().substring(args[0].length() + args[1].length() + 2)).queue();
            // Tell author they banned the player successfully
            e.reply(e.getClient().getSuccess() + " Successfully kicked " + e.getMessage().getMentionedMembers().get(0).getUser().getAsTag());
        } catch (Exception err) {
            err.printStackTrace();
            e.reply(e.getClient().getError() + " There was an error kicking this user.\n\n```" + err.toString() + "\n````");
        }
    }
}