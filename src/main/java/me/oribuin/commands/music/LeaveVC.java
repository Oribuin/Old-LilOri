package me.oribuin.commands.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.managers.AudioManager;

import java.awt.*;

public class LeaveVC extends Command {
    public LeaveVC() {
        this.name = "LeaveVC";
        this.help = "Get Lil' Ori to leave a Voice Channel.";
        this.cooldown = 5;
        this.cooldownScope = CooldownScope.USER;
        this.botPermissions = new Permission[]{Permission.VOICE_CONNECT, Permission.VOICE_SPEAK, Permission.VOICE_STREAM};
        this.ownerCommand = true;
    }

    public void execute(CommandEvent e) {
        AudioManager manager = e.getGuild().getAudioManager();

        EmbedBuilder Embed = new EmbedBuilder()
                .setColor(Color.decode(Info.COLOR))
                .setAuthor("Left Voice Channel", "https://github.com/Oribuin/Lil-Ori/", "" + e.getJDA().getSelfUser().getAvatarUrl())
                .setFooter("Lil' Ori v" + Info.VERSION)
                .setDescription("**Requested By:** " + e.getAuthor().getAsMention());
        e.reply(Embed.build());

        manager.closeAudioConnection();
    }
}
