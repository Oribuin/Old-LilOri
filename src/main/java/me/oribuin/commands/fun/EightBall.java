package me.oribuin.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;
import org.apache.http.impl.client.TargetAuthenticationStrategy;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class EightBall extends Command {
    public EightBall() {
        this.name = "EightBall";
        this.aliases = new String[]{"8ball", "ball"};
        this.help = "Magic 8 Ball - Ask any it any question you have.";
        this.cooldown = 5;
        this.cooldownScope = CooldownScope.USER;
        this.ownerCommand = Settings.OfflineMode;
    }

    @Override
    protected void execute(CommandEvent e) {
        String[] answers = {
                "It is certain. :8ball:",
                "It is decidedly so. :8ball:",
                "Without a doubt. :8ball:",
                "Definitely. :8ball:",
                "You may rely on it. :8ball:",
                "As I see it, yes. :8ball:",
                "Most likely. :8ball:",
                "Outlook good. :8ball:",
                "Yes. :8ball:",
                "Signs point to yes. :8ball:",
                "Reply hazy, try again. :8ball:",
                "Ask again later. :8ball:",
                "Better not tell you now. :8ball:",
                "Cannot predict now. :8ball:",
                "Concentrate and ask again. :8ball:",
                "Don't count on it. :8ball:",
                "My reply is no. :8ball:",
                "My sources say no. :8ball:",
                "Outlook not so good. :8ball:",
                "Very doubtful. :8ball:"
        };

        Integer randomInt = new Random().nextInt(answers.length);

        String[] args = e.getMessage().getContentRaw().split(" ");

        if (e.getGuild().getId().equals("481741000365178881")) {
            e.reply("This guild is forbidden to use this command.");
            return;
        }

        try {
            if (e.getMessage().getContentRaw().equals(args[0])) {
                e.reply("Please input a question for the bot.");

            } else {
                EmbedBuilder ball = new EmbedBuilder()
                        .setColor(Color.decode(Info.COLOR))
                        .setAuthor("Lil' Ori's Magic 8Ball", "https://github.com/Oribuin/Lil-Ori/", "" + e.getJDA().getSelfUser().getAvatarUrl())
                        .setFooter("Lil' Ori v" + Info.VERSION)
                        .setDescription(answers[randomInt]);

                e.reply(ball.build());
            }
        } catch (Exception err) {
            e.reply("You have caught an Exception, Have you typed the command correctly?");

            EmbedBuilder ex = new EmbedBuilder()
                    .setColor(Color.RED)
                    .setAuthor("Exception Error")
                    .setFooter("Please report this to Ori#0004")
                    .setDescription(err.toString());
            e.reply(ex.build());
        }
    }
}