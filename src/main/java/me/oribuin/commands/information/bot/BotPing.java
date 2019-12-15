package me.oribuin.commands.information.bot;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class BotPing extends Command {
    public BotPing() {
        this.name = "BotPing";
        this.aliases = new String[]{"ping", "latency"};
        this.help = "Gateway Ping of the Bot.";
        this.cooldown = 3;
        this.cooldownScope = CooldownScope.USER;
        this.ownerCommand = Settings.OfflineMode;
    }

    @Override
    protected void execute(CommandEvent e) {
        long ping = e.getJDA().getGatewayPing();

        EmbedBuilder em = new EmbedBuilder()
                .setAuthor("Latency", "https://github.com/Oribuin/Lil-Ori/")
                .setFooter("Lil' Ori v" + Info.VERSION)
                .setDescription("**Ping: **" + ping + "ms");

        if (ping < 101) {
            em.setColor(Color.green);
        }

        if (ping > 100) {
            em.setColor(Color.decode("#ffff00"));
        }

        if (ping > 199) {
            em.setColor(Color.decode("#ffa500"));
        }

        if (ping > 299) {
            em.setColor(Color.red);
        }

        e.reply(em.build());
    }
}
