package me.oribuin.commands.admin.bot;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.Permission;

public class SayCMD extends Command {
    public SayCMD() {
        this.name = "say";
        this.help = "Make Lil' Ori say something.";
        this.cooldown = 3;
        this.cooldownScope = CooldownScope.USER;
        this.userPermissions = new Permission[] {Permission.ADMINISTRATOR};
        this.ownerCommand = Settings.OfflineMode;
    }

    @Override
    protected void execute(CommandEvent e) {
        e.reply(e.getMessage().getContentDisplay().substring(4));
    }
}