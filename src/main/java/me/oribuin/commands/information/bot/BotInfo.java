package me.oribuin.commands.information.bot;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class BotInfo extends Command {
    public BotInfo() {
        this.name = "BotInfo";
        this.aliases = new String[]{"lilori", "binfo"};
        this.help = "Information about the Bot.";
        this.cooldown = 3;
        this.cooldownScope = CooldownScope.USER;
        this.ownerCommand = Settings.OfflineMode;
    }

    @Override
    protected void execute(CommandEvent e) {
        EmbedBuilder em = new EmbedBuilder()
                .setColor(Color.decode(Info.COLOR))
                .setAuthor("Lil' Ori's Information", "https://github.com/Oribuin/Lil-Ori/")
                .setFooter("Lil' Ori v" + Info.VERSION)
                .setThumbnail("" + e.getJDA().getSelfUser().getAvatarUrl())
                .setDescription("Created By: **Ori**#0004\n\n" )
                .addField("⠀", "[Github](https://github.com/Oribuin/Lil-Ori/)", false)
                .addField("⠀", "Website Coming Soon", false)
                .addField("Guilds", "" + e.getJDA().getGuilds().size(), false)
                .addField("Users", "" + e.getJDA().getUsers().size(), true)
                .addField("Commands", String.valueOf(e.getClient().getCommands().size()), true);

        e.reply(em.build());
    }
}
