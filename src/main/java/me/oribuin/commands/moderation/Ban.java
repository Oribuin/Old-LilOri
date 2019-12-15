package me.oribuin.commands.moderation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;

public class Ban extends Command {
    public Ban() {
        this.name = "Ban";
        this.help = "Ban a member from the guild.";
        this.aliases = new String[]{"Expel"};
        this.cooldown = 3;
        this.botPermissions = new Permission[]{Permission.BAN_MEMBERS};
        this.userPermissions = new Permission[]{Permission.BAN_MEMBERS};
        this.ownerCommand = Settings.OfflineMode;
    }

    @Override
    public void execute(CommandEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");
        try {
            // If its just ;ban
            if (args.length < 3 || e.getMessage().getContentRaw().equals(args[1])) {
                e.reply(e.getClient().getWarning() + " Correct usage: ;ban @user <reason>");
                return;

                // If args[1] doesn't equal a user to ban
            } else {
                e.getMessage().getMentionedMembers();
                if (e.getMessage().getMentionedMembers().isEmpty()) {
                    e.reply(e.getClient().getWarning() + " Correct usage ;ban @user <reason>");
                    return;

                    // If the user mentioned is the message author
                } else if (e.getMessage().getMentionedMembers().get(0).equals(e.getAuthor())) {
                    e.reply(e.getClient().getWarning() + " You cannot ban yourself.");
                    return;

                    // If User mentioned is owner, or has the same/higher role than user executing command.
                } else if (e.getMessage().getMentionedMembers().get(0).isOwner()
                        || e.getMessage().getMentionedMembers().get(0).getRoles().get(0).getPosition() > e.getGuild().getMember(e.getAuthor()).getRoles().get(0).getPosition()
                        || e.getMessage().getMentionedMembers().get(0).getRoles().get(0).getPosition() > e.getSelfMember().getRoles().get(0).getPosition()) {

                    e.reply(e.getAuthor().getAsMention() + ", You cannot ban this user due to Role Hierarchy.");
                    return;
                    // If the user is a bot, this will prevent errors with DMs.
                } else if (e.getMessage().getMentionedMembers().get(0).getUser().isBot()) {
                    e.reply(e.getClient().getWarning() + " I cannot ban other bots!");
                    return;
                }
            }

            // Embed Defining
            EmbedBuilder Embed = new EmbedBuilder()
                    .setColor(Color.decode(Info.COLOR))
                    .setAuthor("You have been banned!")
                    .setFooter("Lil Ori v" + Info.VERSION);
            // Define new Embed description
            Embed.setDescription("Banned from: " + e.getGuild().getName() + " (" + e.getGuild().getId() + ")\n" +
                    "Banned By: " + e.getMessage().getAuthor().getAsTag() + "\n" +
                    "Reason: " + e.getMessage().getContentRaw().substring(args[0].length() + args[1].length() + 2));

            // Tell user they were banned
            e.getMessage().getMentionedMembers().get(0).getUser().openPrivateChannel().queue(channel -> {
                channel.sendMessage(Embed.build()).queue();
            });

            // Ban the user
            e.getMessage().getMentionedMembers().get(0).ban(0, e.getMessage().getContentRaw().substring(args[0].length() + args[1].length() + 2)).queue();
            // Tell author they banned the player successfully
            e.reply(e.getClient().getSuccess() + " Successfully Banned " + e.getMessage().getMentionedMembers().get(0).getUser().getAsTag());
        } catch (Exception err) {
            err.printStackTrace();
            e.reply(e.getClient().getError() + " There was an error banning this user.\n\n```" + err.toString() + "\n````");
        }
    }
}
