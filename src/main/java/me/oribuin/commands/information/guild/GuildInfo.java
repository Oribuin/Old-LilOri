package me.oribuin.commands.information.guild;

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

public class GuildInfo extends Command {
    public GuildInfo() {
        this.name = "GuildInfo";
        this.aliases = new String[] {"ginfo"};
        this.help = "Information about a guild.";
        this.cooldown = 3;
        this.guildOnly = true;
        this.cooldownScope = CooldownScope.USER;
        this.ownerCommand = Settings.OfflineMode;
    }

    @Override
    protected void execute(CommandEvent e) {

        EmbedBuilder Embed = new EmbedBuilder()
                .setFooter("Lil' Ori v" + Info.VERSION);

            try {
            Embed.setColor(Objects.requireNonNull(e.getGuild().getOwner()).getColor());
            Embed.setAuthor(e.getGuild().getName() + " - ID: " + e.getGuild().getId(), "https://github.com/Oribuin/Lil-Ori/", e.getJDA().getSelfUser().getAvatarUrl());
            Embed.setDescription("**Roles:** " + e.getGuild().getRoles().size() + "\n" +
                    "**Channels: **" + e.getGuild().getChannels().size() + "\n" +
                    "**Emojis: **" + e.getGuild().getEmotes().size() + "\n" +
                    "**Boosts: **" + e.getGuild().getBoosters().size() + "\n" +
                    "**Owner: **<@" + e.getGuild().getOwnerIdLong() + "> \n" +
                    "**Members: **" + e.getGuild().getMembers().size() + "\n" +
                    "**Region: **" + e.getGuild().getRegion());

            if (e.getGuild().getIconUrl() == null) {
                Embed.setThumbnail("https://imgur.com/a/Opr30oR.png");
            } else {
                Embed.setThumbnail(e.getGuild().getIconUrl());
            }

            e.reply(Embed.build());

        } catch (
                Exception err) {
            e.reply("You have caught an Exception");

            EmbedBuilder ex = new EmbedBuilder()
                    .setColor(Color.RED)
                    .setAuthor("Exception Error")
                    .setFooter("Please report this to Ori#0004")
                    .setDescription(err.toString());
            e.reply(ex.build());
        }
    }
}
