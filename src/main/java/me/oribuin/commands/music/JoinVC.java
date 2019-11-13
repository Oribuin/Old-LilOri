package me.oribuin.commands.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import java.awt.*;
import java.util.Objects;

public class JoinVC extends Command {
    public JoinVC() {
        this.name = "JoinVC";
        this.help = "Get Lil' Ori to join your Voice Channel.";
        this.cooldown = 5;
        this.cooldownScope = CooldownScope.USER;
        this.botPermissions = new Permission[] {Permission.VOICE_CONNECT, Permission.VOICE_SPEAK, Permission.VOICE_STREAM};
        this.ownerCommand = true;
    }

    public void execute(CommandEvent e) {

        Guild guild = e.getGuild();
        AudioManager manager = guild.getAudioManager();
        VoiceChannel channel = e.getGuild().getMember(e.getAuthor()).getVoiceState().getChannel();

        if(e.getGuild().getMember(e.getAuthor()).getVoiceState().inVoiceChannel()) {
            assert channel != null;

            manager.openAudioConnection(channel);
            EmbedBuilder Embed = new EmbedBuilder()
                    .setColor(Color.decode(Info.COLOR))
                    .setAuthor("Joined Voice Channel", "https://github.com/Oribuin/Lil-Ori/")
                    .setFooter("Lil' Ori v" + Info.VERSION)
                    .setDescription("**Channel:** " + channel.getName() + "\n" +
                            "**Requested By:** " + e.getAuthor().getAsMention());
            e.reply(Embed.build());
        } else {
            e.reply(e.getAuthor().getAsMention() + ", Please enter a Voice Channel that I can join.");
        }
    }
}
