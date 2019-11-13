package me.oribuin.commands.admin.bot;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.Permission;

public class SayCMD extends Command {
    public SayCMD() {
        this.name = "say";
        this.help = "Make Lil' Ori say something.";
        this.cooldown = 5;
        this.cooldownScope = CooldownScope.USER;
        this.ownerCommand = Settings.OfflineMode;
    }

    @Override
    protected void execute(CommandEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");

        if (args.length < 2) {
            e.reply("You did not provide the correct arguments for this command.");

        } else if (e.getAuthor().getId().equals("345406020450779149")){
            e.reply(e.getMessage().getContentDisplay().substring(4));

        } else {

            if (e.getGuild().getMember(e.getAuthor()).hasPermission(Permission.ADMINISTRATOR)) {
                e.reply(e.getMessage().getContentDisplay().substring(4));
            } else {
                e.reply("You must have the Administrator permission in this Guild to use that.");
            }
        }
    }
}