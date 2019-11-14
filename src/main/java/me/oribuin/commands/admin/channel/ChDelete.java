package me.oribuin.commands.admin.channel;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;

import java.awt.*;

public class ChDelete extends Command {
    public ChDelete() {
        this.name = "ChDelete";
        this.help = "Delete a channel.";
        this.aliases = new String[]{"delch", "chdelete", "deletech"};
        this.cooldown = 100;
        this.cooldownScope = CooldownScope.USER;
        this.userPermissions = new Permission[]{Permission.MANAGE_CHANNEL};
        this.botPermissions = new Permission[]{Permission.MANAGE_CHANNEL};
        this.ownerCommand = Settings.OfflineMode;
        //this.ownerCommand = true;
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent e) {

        String[] args = e.getMessage().getContentRaw().split(" ");

        if (args.length < 2) {
            e.reply(e.getAuthor().getAsMention() + ", Please include an ID of the channel you wish to delete.");

        } else if (e.getGuild().getTextChannelById(args[1]) == null) {
            e.reply(e.getAuthor().getAsMention() + ", The ID of the channel is invalid.");

        } else {
            try {
                if (args[1].equals(e.getGuild().getGuildChannelById(args[1]).getId())) {
                    e.getGuild().getTextChannelById(args[1]).delete().queue();
                    e.reply("Deleted Channel");
                } else {
                    e.reply("That is not a channel. Please include a correct ID.");
                }
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