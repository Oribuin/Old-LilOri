package me.oribuin.commands.information.bot;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class InviteLink extends Command {
    public InviteLink() {
        this.name = "Invite";
        this.help = "The invite link for Lil' Ori.";
        this.cooldownScope = CooldownScope.USER;
        this.cooldown = 7;
        this.aliases = new String[]{"invite", "addbot"};
    }

    @Override
    protected void execute(CommandEvent e) {
        e.reply(e.getAuthor().getAsMention() + ", Here are a list of all the basic permissions needed for all Lil' Ori Commands.\nhttps://discordapp.com/oauth2/authorize?client_id=581203970203189269&permissions=20839511&scope=bot");
    }
}