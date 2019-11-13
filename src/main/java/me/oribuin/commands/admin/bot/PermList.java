package me.oribuin.commands.admin.bot;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;

import java.awt.*;

public class PermList extends Command {
    public PermList() {
        this.name = "PermList";
        this.aliases = new String[]{"perms", "permissions"};
        this.help = "Find out the list of permissions Lil' Ori has access to.";
        this.guildOnly = true;
        //this.userPermissions = new Permission[]{Permission.ADMINISTRATOR};
        this.ownerCommand = Settings.OfflineMode;
    }

    @Override
    protected void execute(CommandEvent e) {
        EmbedBuilder em = new EmbedBuilder()
                .setColor(Color.decode(Info.COLOR))
                .setAuthor("Lil' Ori Permissions", "https://github.com/Oribuin/Lil-Ori/")
                .setFooter("Lil' Ori v" + Info.VERSION)
                .setDescription("**Total: ** " + e.getGuild().getSelfMember().getPermissions().size() + "/32 \n\n" +
                        "" + e.getGuild().getSelfMember().getPermissions().toString().replaceAll(
                        ",", " **-**").replaceAll(
                        "\\[", " **-** ").replaceAll(
                        "]", "")
                );

        e.reply(em.build());
    }
}
