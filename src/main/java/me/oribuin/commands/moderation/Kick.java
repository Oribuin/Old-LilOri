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
    protected void execute(CommandEvent e) {


        String[] args = e.getMessage().getContentRaw().split(" ");

        try {

            if (args.length < 2) {
                e.reply(e.getAuthor().getAsMention() + ", Please mention a user to kick from the server.");

            } else if (!args[1].equalsIgnoreCase(e.getMessage().getMentionedMembers().get(0).getAsMention())) {
                e.reply(e.getAuthor().getAsMention() + ", Please mention a user to kick from the server.");

            } else {
                EmbedBuilder Embed = new EmbedBuilder()
                        .setColor(Color.decode(Info.COLOR))
                        .setAuthor("Kicked User", "https://github.com/Oribuin/Lil-Ori/", "" + e.getJDA().getSelfUser().getAvatarUrl())
                        .setFooter("Lil' Ori v" + Info.VERSION)
                        .setDescription("Kicked by: " + e.getAuthor().getAsMention() + "\n" + "User Kicked: " + e.getMessage().getMentionedMembers().get(0).getUser().getAsTag());

                e.getGuild().getMember(e.getMessage().getMentionedUsers().get(0)).getUser().openPrivateChannel().queue(channel -> {
                    channel.sendMessage("You were kicked from: **" + e.getMessage().getGuild().getName() + "**.").queue();
                    e.getGuild().getMember(e.getMessage().getMentionedUsers().get(0)).kick().queue();
                    e.reply(Embed.build());
                });

                System.out.print(e.getAuthor().getAsTag() + "/ ID:" + e.getAuthor().getId() + " Just kicked: " + e.getMessage().getMentionedMembers().get(0).getUser().getAsTag() + "/ ID:" + e.getMessage().getMentionedMembers().get(0).getId() +
                        "\nGuild: " + e.getGuild().getName());

            }

        } catch (IndexOutOfBoundsException err) {

            if (e.toString().startsWith("java.lang.IndexOutOfBoundsException")) {
                e.reply(e.getAuthor().getAsMention() + ", Please mention a user to kick from the server.");
            } else {
                e.reply(e.getAuthor().getAsMention() + ", You cannot kick someone Higher or Equal Role from the bot or yourself.");
            }
        }
    }
}
