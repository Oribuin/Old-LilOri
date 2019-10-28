package me.oribuin.commands.admin.ori;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class LeaveServer extends Command {
    public LeaveServer() {
        this.name = "LeaveServer";
        this.help = "Force Lil' Ori to leave any server.";
        this.aliases = new String[]{
                "lsrv",
                "leave"
        };

        this.cooldown = 3;
        this.cooldownScope = CooldownScope.USER;
        //this.ownerCommand = Settings.OfflineMode;
        this.ownerCommand = true;
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent e) {

        String[] args = e.getMessage().getContentRaw().split(" ");

        if (args.length < 2) {
            e.reply(e.getAuthor().getAsMention() + ", Please include a server ID to leave.");

        } else if (e.getJDA().getGuildById(args[1]) == null) {
            e.reply(e.getAuthor().getAsMention() + ", The ID of the server is invalid.");

        } else {
            try {
                e.reply(e.getAuthor().getAsMention() + ", You have made Lil' Ori leave \"**" + e.getJDA().getGuildById(args[1]).getName() + "**\".");
                e.getJDA().getGuildById(args[1]).leave().queue();

            } catch (Exception err) {
                e.reply(e.getAuthor().getAsMention() + ", You have caught an exception.");
                EmbedBuilder Embed = new EmbedBuilder()
                        .setColor(Color.RED)
                        .setAuthor("Exception Error Catched")
                        .setDescription(err.toString());
                e.reply(Embed.build());
            }
        }
    }
}
