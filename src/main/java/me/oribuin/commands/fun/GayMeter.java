package me.oribuin.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class GayMeter extends Command {
    public GayMeter() {
        this.name = "GayMeter";
        this.aliases = new String[]{"howgay", "gay"};
        this.help = "On a scale from 1-100, How gay are you?";
        this.cooldown = 0;
        this.cooldownScope = CooldownScope.USER;
        this.ownerCommand = Settings.OfflineMode;
    }

    @Override
    protected void execute(CommandEvent e) {
        EmbedBuilder Embed = new EmbedBuilder()
                .setColor(Color.decode(Info.COLOR))
                .setAuthor("Gay Meter", "https://github.com/Oribuin/Lil-Ori/")
                .setFooter("Lil' Ori v" + Info.VERSION);

        int randomInt = (int) (101.0 * Math.random());
        Embed.setDescription("**" + randomInt + "% Gay** :gay_pride_flag:");

        e.reply(Embed.build());
    }
}
