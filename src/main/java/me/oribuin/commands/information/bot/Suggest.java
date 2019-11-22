package me.oribuin.commands.information.bot;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import me.oribuin.main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

import java.awt.*;

public class Suggest extends Command {
    public Suggest() {
        this.name = "Suggest";
        this.help = "Suggest new features to the bot.";
        this.cooldownScope = CooldownScope.USER;
        this.cooldown = 20;
    }

    @Override
    protected void execute(CommandEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");

        if (args.length < 2) {
            e.reply(e.getAuthor().getAsMention() + ", Please include a suggestion.");
        } else {
            EmbedBuilder Embed = new EmbedBuilder()
                    .setColor(Color.decode(Info.COLOR))
                    .setFooter("Lil' Ori v" + Info.VERSION)
                    .setAuthor("Suggestion Submitted", "https://github.com/Oribuin/Lil-Ori/")
                    .setDescription("I have submitted your suggestion to the developers :smile:");
            e.reply(Embed.build());
            if (e.getSelfMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                e.getMessage().delete().queue();
            }

            EmbedBuilder Submitted = new EmbedBuilder()
                    .setColor(Color.decode(Info.COLOR))
                    .setFooter("Lil' Ori v" + Info.VERSION)
                    .setAuthor("Suggestion Incoming", "https://github.com/Oribuin/Lil-Ori/")
                    .setDescription(e.getMessage().getContentRaw().substring(7));

            e.getJDA().getGuildById("608904742365822976").getTextChannelById("608904742365822976").sendMessage(Submitted.build()).queue();
        }
    }
}
