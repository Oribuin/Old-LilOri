package me.oribuin.commands.information.user;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class UserAvatar extends Command {
    public UserAvatar() {
        this.name = "Avatar";
        this.aliases = new String[]{"ava", "pfp"};
        this.help = "Get the avatar of someone's discord profile.";
        this.cooldown = 3;
        this.cooldownScope = CooldownScope.USER;
        this.guildOnly = true;
        this.ownerCommand = Settings.OfflineMode;
    }

    @Override
    protected void execute(CommandEvent e) {

        EmbedBuilder Embed = new EmbedBuilder()
                .setColor(Color.decode(Info.COLOR))
                .setAuthor("Lil' Ori's Avatars", "https://github.com/Oribuin/Lil-Ori/", "" + e.getJDA().getSelfUser().getAvatarUrl())
                .setFooter("Lil' Ori v" + Info.VERSION);

        try {
            if (e.getMessage().getMentionedUsers().isEmpty()/* || Objects.requireNonNull(e.getMessage().getMember()).getUser().getName().isEmpty()*/) {

                Embed.setImage("" + e.getAuthor().getAvatarUrl());
            } else {
                Embed.setImage("" + e.getMessage().getMentionedUsers().get(0).getAvatarUrl());

            }

            e.reply(Embed.build());
        } catch (IllegalArgumentException err) {
            if (e.getMessage().getMentionedUsers().isEmpty()) {
                e.reply("You do not have a profile picture I can collect.");
            } else if (!e.getMessage().getMentionedUsers().isEmpty()) {
                e.reply("This user does not have a profile picture I can collect.");
            } else {
                e.reply("There was an exception. Please report this to Ori#0004!\n\n" + err.toString() );
            }
        }
    }
}
