package me.oribuin.commands.information.user;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class UserAvatar extends Command {
    public UserAvatar() {
        this.name = "Avatar";
        this.aliases = new String[]{"ava", "pfp"};
        this.help = "Get the avatar of someone's discord profile.";
        this.cooldown = 3;
        this.cooldownScope = CooldownScope.USER;
        this.guildOnly = true;
        this.ownerCommand = Settings.OfflineMode;
    }

    @Override
    protected void execute(CommandEvent e) {

        String[] args = e.getMessage().getContentRaw().split(" ");

        EmbedBuilder Embed = new EmbedBuilder()
                .setColor(Color.decode(Info.COLOR))
                .setAuthor("Lil' Ori's Avatars", "https://github.com/Oribuin/Lil-Ori/")
                .setFooter("Lil' Ori v" + Info.VERSION);

        //try {
            if (args.length < 2) {
                Embed.setImage(e.getAuthor().getAvatarUrl());
                e.reply(Embed.build());

            } else if (args[1].equals(e.getMessage().getMentionedUsers().get(0).getAsMention())) {
                Embed.setImage(e.getMessage().getMentionedUsers().get(0).getAvatarUrl());
                e.reply(Embed.build());

            } else if (args[1].equals(e.getGuild().getMemberById(args[1]).getId())) {
                Embed.setImage(e.getGuild().getMemberById(args[1]).getUser().getAvatarUrl());
                e.reply(Embed.build());

            } else if (args[1].equals(e.getGuild().getMemberByTag(args[1]).getUser().getAsTag())) {
                Embed.setImage(e.getGuild().getMemberByTag(args[1]).getUser().getAvatarUrl());
                e.reply(Embed.build());
            }
/*
        } catch (IllegalArgumentException err) {
            if (e.getMessage().getMentionedUsers().isEmpty()) {
                e.reply(e.getAuthor().getAsMention() + ", You do not have a profile picture I can collect.");
            } else if (!e.getMessage().getMentionedUsers().isEmpty()) {
                e.reply(e.getAuthor().getAsMention() + ", This user does not have a profile picture I can collect.");
        } else {
                e.reply("You have caught an Exception, Have you typed the command correctly?");

                EmbedBuilder ex = new EmbedBuilder()
                        .setColor(Color.RED)
                        .setAuthor("Exception Error")
                        .setFooter("Please report this to Ori#0004")
                        .setDescription(err.toString());
                e.reply(ex.build());
            }
        }*/
    }
}
