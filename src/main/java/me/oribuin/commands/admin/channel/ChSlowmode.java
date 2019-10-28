package me.oribuin.commands.admin.channel;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;

import java.awt.*;

public class ChSlowmode extends Command {
    public ChSlowmode() {
        this.name = "ChSlowmode";
        this.aliases = new String[]{"slow", "slowmode"};
        this.help = "Set the channel slowmode";
        this.cooldown = 3;
        this.botPermissions = new Permission[]{Permission.MANAGE_CHANNEL, Permission.VIEW_CHANNEL};
        this.userPermissions = new Permission[]{Permission.MANAGE_CHANNEL, Permission.MANAGE_SERVER, Permission.MANAGE_PERMISSIONS};
        this.ownerCommand = Settings.OfflineMode;
    }

    @Override
    protected void execute(CommandEvent e) {

        String[] args = e.getMessage().getContentRaw().split(" ");

        try {
            if (e.getMessage().getContentRaw().equals(args[0]) || args[1].equals("0")) {
                e.getTextChannel().getManager().setSlowmode(0).queue();
                e.reply("Reset the channel's slowmode.");
            } else {
                e.getTextChannel().getManager().setSlowmode(Integer.parseInt(args[1])).queue();
                e.reply("Set the slowmode time to: " + args[1]);
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
