package me.oribuin.commands.information.guild;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;

import java.awt.*;
import java.util.Objects;

public class RoleInfo extends Command {
    public RoleInfo() {
        this.name = "RoleInfo";
        this.help = "Get the information about a role";
        this.aliases = new String[] {"rinfo"};
        this.cooldownScope = CooldownScope.USER;
        this.cooldown = 7;
    }

    public void execute(CommandEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");
        Role role = e.getGuild().getRolesByName(args[1], true).get(0);

        if (role == null || e.getMessage().getContentRaw().equalsIgnoreCase(String.valueOf(e.getGuild().getRolesByName(args[1], true || args[1] == null))))  {
            e.reply(e.getAuthor().getAsMention() + ", That role does not exist. Format ;rinfo <role-name>");
        } else {
            EmbedBuilder Embed = new EmbedBuilder()
                    .setColor(Color.decode(Info.COLOR))
                    .setAuthor("Role Info", "https://github.com/Oribuin/Lil-Ori/")
                    .setFooter("Lil' Ori v" + Info.VERSION)
                    .setDescription("**Role:** " + role.getAsMention() + "\n" +
                            "**ID: **" + role.getId() + "\n" +
                            "**Position:** " + role.getPosition() + "\n" +
                            "**RGB Color:** " + role.getColorRaw() + "\n" +
                            "**Hex Color:** " + String.format("#%02x%02x%02x", role.getColor().getRed(), role.getColor().getGreen(), role.getColor().getBlue()));
            e.reply(Embed.build());
        }
    }
}
