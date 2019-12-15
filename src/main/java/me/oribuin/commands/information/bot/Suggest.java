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
            e.reply(e.getAuthor().getAsMention() + ", I have send your suggestion directly to admins, Thank you for suggesting!");
            if (e.getSelfMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                e.getMessage().delete().queue();
            }

            EmbedBuilder Submitted = new EmbedBuilder()
                    .setColor(Color.decode(Info.COLOR))
                    .setFooter("Lil' Ori v" + Info.VERSION)
                    .setAuthor("Suggestion Incoming", "https://github.com/Oribuin/Lil-Ori/")
                    .setDescription("**User:** " + e.getMessage().getAuthor().getAsTag() + " (" + e.getAuthor().getId() + ")\n" +
                    "**Server:** " + e.getMessage().getGuild().getName() + " (" + e.getMessage().getGuild().getId() + ")\n" +
                    "**Suggestion:** " +  e.getMessage().getContentRaw().substring(8));

            e.getJDA().getGuildById("608904742365822976").getTextChannelById("647251528083701790").sendMessage(Submitted.build()).queue();
        }
    }
}
