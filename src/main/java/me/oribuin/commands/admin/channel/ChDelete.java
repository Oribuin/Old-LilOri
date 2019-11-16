package me.oribuin.commands.admin.channel;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;

import java.awt.*;
import java.time.OffsetDateTime;

public class ChDelete extends Command {
    public ChDelete() {
        this.name = "ChDelete";
        this.help = "Delete a channel.";
        this.aliases = new String[]{"delch", "delchannel"};
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
        OffsetDateTime timec = e.getMessage().getTimeCreated();

        EmbedBuilder Embed = new EmbedBuilder()
                .setColor(Color.decode(Info.COLOR))
                .setAuthor("Deleted Channel", "https://github.com/Oribuin/Lil-Ori/")
                .setFooter("Lil' Ori v" + Info.VERSION)
                .setDescription("Channel Deleted by: " + e.getAuthor().getAsMention() + "\n" +
                        "Channel Deleted: #" + e.getMessage().getMentionedChannels().get(0).getName() + "\n" +
                        "Deleted at: " + timec.getDayOfWeek() + " At: " + timec.getHour() + ":" + timec.getMinute());

        String[] args = e.getMessage().getContentRaw().split(" ");

        if (args.length < 2) {
            e.reply(e.getAuthor().getAsMention() + ", Please include an ID of the channel you wish to delete.");

        } else if (e.getMessage().getMentionedChannels().get(0) == null) {
            e.reply(e.getAuthor().getAsMention() + ", That channel is invalid.");

        } else {
            try {
                if (args[1].equals(e.getMessage().getMentionedChannels().get(0).getAsMention())) {
                    e.getMessage().getMentionedChannels().get(0).delete().queue();
                    e.reply(Embed.build());
                } else {
                    e.reply("That is not a channel. Please mention a correct channel.");
                }
            } catch (Exception err) {
                e.reply(e.getAuthor().getAsMention() + ", You have caught an exception.");
                EmbedBuilder Error = new EmbedBuilder()
                        .setColor(Color.RED)
                        .setAuthor("Exception Error Catched")
                        .setDescription(err.toString());
                e.reply(Error.build());
            }
        }
    }
}