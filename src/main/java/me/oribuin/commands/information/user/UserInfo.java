package me.oribuin.commands.information.user;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.util.Objects;

public class UserInfo extends Command {
    public UserInfo() {
        this.name = "UserInfo";
        this.aliases = new String[]{"uinfo"};
        this.help = "Information about a user.";
        this.cooldown = 3;
        this.guildOnly = true;
        this.cooldownScope = CooldownScope.USER;
        this.ownerCommand = Settings.OfflineMode;
    }

    @Override
    protected void execute(CommandEvent e) {

        String[] args = e.getMessage().getContentRaw().split(" ");

        if (e.getMessage().getMentionedUsers().isEmpty()) {
            Member author = e.getGuild().getMember(e.getAuthor());

            EmbedBuilder NoMention = new EmbedBuilder()
                    .setFooter("Lil' Ori v" + Info.VERSION);

            if (author.getRoles().isEmpty()) {
                NoMention.setDescription("**User ID: **" + author.getId() + "\n" +
                        "**Display Name: **" + author.getEffectiveName() + "\n" +
                        "**Joined: ** " + author.getTimeJoined().getMonth() + "/" + author.getTimeJoined().getDayOfMonth() + "/" + author.getTimeJoined().getYear() + "\n" +
                        "**Created: ** " + author.getTimeCreated().getMonth() + "/" + author.getTimeCreated().getDayOfMonth() + "/" + author.getTimeCreated().getYear() + "\n" +
                        "**Status: **" + author.getOnlineStatus().name().toUpperCase().replaceAll("_", " ") + "\n" +
                        "**Roles: **" + "None");
            } else {
                NoMention.setDescription("**User ID: **" + author.getId() + "\n" +
                        "**Display Name: **" + author.getEffectiveName() + "\n" +
                        "**Joined: ** " + author.getTimeJoined().getMonth() + "/" + author.getTimeJoined().getDayOfMonth() + "/" + author.getTimeJoined().getYear() + "\n" +
                        "**Created: ** " + author.getTimeCreated().getMonth() + "/" + author.getTimeCreated().getDayOfMonth() + "/" + author.getTimeCreated().getYear() + "\n" +
                        "**Status: **" + author.getOnlineStatus().name().toUpperCase().replaceAll("_", " ") + "\n" +
                        "**Roles: **" + author.getRoles().size() + "\n" +
                        "**Role Color:** " + String.format("#%02x%02x%02x", Objects.requireNonNull(author.getColor()).getRed(), author.getColor().getGreen(), author.getColor().getBlue()));
            }

            NoMention.setColor(author.getColor());
            NoMention.setAuthor(author.getUser().getAsTag(), "https://github.com/Oribuin/Lil-Ori/", author.getUser().getAvatarUrl());

            e.reply(NoMention.build());

        } else if (!e.getMessage().getMentionedUsers().isEmpty()){
            Member user = e.getMessage().getMentionedMembers().get(0);

            EmbedBuilder Mention = new EmbedBuilder()
                    .setFooter("Lil' Ori v" + Info.VERSION);

            if (user.getUser().isFake()) {
                e.reply("The user you mentioned is a fake account.");
                return;
            }

            if (user.getRoles().isEmpty()) {
                Mention.setDescription("**User ID: **" + user.getId() + "\n" +
                        "**Display Name: **" + user.getEffectiveName() + "\n" +
                        "**Joined: ** " + user.getTimeJoined().getMonth() + "/" + user.getTimeJoined().getDayOfMonth() + "/" + user.getTimeJoined().getYear() + "\n" +
                        "**Created: ** " + user.getTimeCreated().getMonth() + "/" + user.getTimeCreated().getDayOfMonth() + "/" + user.getTimeCreated().getYear() + "\n" +
                        "**Status: **" + user.getOnlineStatus().name().toUpperCase().replaceAll("_", " ") + "\n" +
                        "**Roles: **" + "None");
            } else {
                Mention.setDescription("**User ID: **" + user.getId() + "\n" +
                        "**Display Name: **" + user.getEffectiveName() + "\n" +
                        "**Joined: ** " + user.getTimeJoined().getMonth() + "/" + user.getTimeJoined().getDayOfMonth() + "/" + user.getTimeJoined().getYear() + "\n" +
                        "**Created: ** " + user.getTimeCreated().getMonth() + "/" + user.getTimeCreated().getDayOfMonth() + "/" + user.getTimeCreated().getYear() + "\n" +
                        "**Status: **" + user.getOnlineStatus().name().toUpperCase().replaceAll("_", " ") + "\n" +
                        "**Roles: **" + user.getRoles().size() + "\n" +
                        "**Role Color:** " + String.format("#%02x%02x%02x", Objects.requireNonNull(user.getColor()).getRed(), user.getColor().getGreen(), user.getColor().getBlue()));
            }

            Mention.setColor(user.getColor());
            Mention.setAuthor(user.getUser().getName() + "#" + user.getUser().getDiscriminator(), "https://github.com/Oribuin/Lil-Ori/", user.getUser().getAvatarUrl());
            e.reply(Mention.build());
        }
    }
}
