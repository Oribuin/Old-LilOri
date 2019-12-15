package me.oribuin.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;
import java.util.Random;

public class Cookie extends Command {
    public Cookie() {
        this.name = "Cookie";
        this.aliases = new String[]{"cook"};
        this.help = "Give a player a cookie <3";
        this.cooldown = 10;
        this.cooldownScope = CooldownScope.USER;
        this.ownerCommand = Settings.OfflineMode;
    }

    @Override
    protected void execute(CommandEvent e) {

        String[] args = e.getMessage().getContentRaw().split(" ");
        /*
        e.getMessage().getMentionedUsers().get(0).getAsMention()
                || e.getGuild().getMemberById(args[1])
                || e.getGuild().getMembersByName(args[1], false);

         */


        String[] gifs = {
                "https://media1.tenor.com/images/51a659cee3d3d2b1d59014d967aafdc1/tenor.gif",
                "https://media1.tenor.com/images/09658c628ea5ceb7f26af42bf2033e38/tenor.gif",
                "https://media1.tenor.com/images/c10b4e9e6b6d2835b19f42cbdd276774/tenor.gif"
        };

        Integer randomInt = new Random().nextInt(gifs.length);

        EmbedBuilder Embed = new EmbedBuilder()
                .setColor(Color.decode(Info.COLOR))
                .setAuthor("You have been given a cookie!", "https://github.com/Oribuin/Lil-Ori/")
                .setFooter("Lil' Ori v" + Info.VERSION);

        if (args.length < 2) {
            e.reply(e.getAuthor().getAsMention() + ", You need to mention a User to give a cookie to. :heart:");

        } else if (args[1].equals(e.getMessage().getMentionedUsers().get(0).getAsMention())) {
            if (args[1].equals(e.getAuthor().getAsMention())) {
                e.reply(e.getAuthor().getAsMention() + ", You can't give cookies to yourself!");
                return;
            }

            Embed.setDescription(e.getAuthor().getAsTag() + " has given " + e.getMessage().getMentionedUsers().get(0).getAsTag() + " a christmas cookie! :cookie:");
            Embed.setImage(gifs[randomInt]);
            e.reply(Embed.build());

        } else {
            e.reply(e.getAuthor().getAsMention() + ", You need to mention a user to give a cookie to. :heart:");
        }
    }
}

        /*
        else if (args[1].equals(e.getMessage().getMentionedChannels().get(0).getAsMention())
                || args[1].equals(e.getGuild().getTextChannelById("" + args[1]))
                || args[1].equals(e.getGuild().getTextChannelsByName("" + args[1], true))) {

            if (e.getGuild().getMember(e.getAuthor()).hasPermission(Permission.ADMINISTRATOR)) {
                Embed.setDescription(e.getAuthor().getAsTag() + " has given this channel christmas cookies! :cookie: :cookie:");
                Embed.setImage("https://media1.tenor.com/images/ae1fd92f4ed82fba165d777e4a05c9de/tenor.gif");

                e.reply(Embed.build());

            } else {
                e.reply(e.getAuthor().getAsMention() + ", You cannot give channels cookies! :sad:");
            }
        }
         */