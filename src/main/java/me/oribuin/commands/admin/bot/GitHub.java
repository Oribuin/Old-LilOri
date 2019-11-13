package me.oribuin.commands.admin.bot;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Settings;

public class GitHub extends Command {
    public GitHub() {
        this.name = "github";
        this.help = "Get the github link for the bot.";
        this.aliases = new String[]{
                "code",
                "sourcecode",
                "source"};
        this.cooldown = 5;
        this.cooldownScope = CooldownScope.USER;
        this.ownerCommand = Settings.OfflineMode;
        //this.ownerCommand = true;
    }

    @Override
    protected void execute(CommandEvent e) {

        e.reply(e.getAuthor().getAsMention() + ", https://github.com/Oribuin/lil-ori");
    }
}
