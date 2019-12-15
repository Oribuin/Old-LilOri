package me.oribuin.commands.information.bot;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import me.oribuin.main.Main;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class Help extends Command {
    public Help() {
        this.name = "Help";
        this.aliases = new String[]{"cmds"};
        this.help = "The list of commands";
        this.cooldown = 3;
        this.cooldownScope = CooldownScope.USER;
        this.ownerCommand = Settings.OfflineMode;
    }

    @Override
    protected void execute(CommandEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ", 2);
        EmbedBuilder Embed = new EmbedBuilder()
                .setColor(Color.decode(Info.COLOR))
                .setFooter("Lil' Ori v" + Info.VERSION);

        if (e.getMessage().getContentRaw().equals(args[0])
                || args[1].equals("1")
                || args[1].equalsIgnoreCase("Info")) {

            Embed.setDescription("Lil' Ori Commands - Total: " + e.getClient().getCommands().size() + "\n \n" +
                    "**Info Commands** \n" +
                    "**-** :robot: Bot:\n" +
                    ";botinfo - Information about the bot. \n" +
                    ";botping - Get the latency connection of the bot. \n" +
                    "**-** :busts_in_silhouette: Guild: \n" +
                    ";guildinfo - Get the information about the guild. \n" +
                    ";gmembers [PageNum] - Get the list of Members. \n" +
                    ";guildroles [PageNum] - Get the list of Roles. \n" +
                    "**-** :bust_in_silhouette: User: \n" +
                    ";avatar @[user] - Get the avatar of a user. \n" +
                    ";uinfo @[user] - Get the information about a user. \n\n" +
                    "Command Aliases on GitHub - ;github");

            e.reply(e.getClient().getSuccess() + " Help Menu - Page 1");
            e.reply(Embed.build());

        } else if (args[1].equals("2")
                || args[1].equalsIgnoreCase("Fun")) {

            Embed.setDescription("**Fun Commands** \n" +
                    ";8ball - Magic 8Ball to answer questions. \n" +
                    ";cookie @[user] - Give a user a cookie.\n" +
                    ";gay - On a scale from 1-100, How gay are you? \n\n" +
                    "Command Aliases on GitHub - ;github");

            e.reply(e.getClient().getSuccess() + " Help Menu - Page 2");
            e.reply(Embed.build());

        } else if (args[1].equals("3")
                || args[1].equalsIgnoreCase("Moderation")) {

            Embed.setDescription("**Moderation Commands** \n" +
                    ";purge [msgs/channel] [amount/#channel]- Purge messages from the channel.\n" +
                    ";kick @[user] [reason] - Kick a user from the server. \n" +
                    ";ban @[user] [reason] - Ban a user from the server.\n\n" +
                    "Command Aliases on GitHub - ;github");

            e.reply(e.getClient().getSuccess() + " Help Menu - Page 3");
            e.reply(Embed.build());

        } else if (args[1].equals("4")
                || args[1].equalsIgnoreCase("Admin")) {

            Embed.setDescription("**Admin Commands** \n" +
                    "**-** :robot: Bot: \n" +
                    ";permlist - Permissions the bot has. \n" +
                    ";say [message] - Make the bot say something.\n" +
                    "**-** :speech_balloon: Channel: \n" +
                    ";chcreate [name] - Create a channel. \n" +
                    ";chdelete #[ChannelName] - Delete a Channel.\n" +
                    ";slowmode [time] - Set the channel's slowmode. \n\n" +
                    "Command Aliases on GitHub - ;github");

            e.reply(e.getClient().getSuccess() + " Help Menu - Page 4");
            e.reply(Embed.build());

        } else if (args[1].equals("5")
        || args[1].equalsIgnoreCase("Other")) {

            Embed.setDescription("**Other Commands** \n" +
                    ";github - The GitHub Link for Lil' Ori. \n" +
                    ";Suggest [suggestion] - Suggest new features to Lil' Ori.\n" +
                    ";Invite - The Invite Link for Lil' Ori. \n\n" +
                    "Command Aliases on GitHub - ;github");

            e.reply(e.getClient().getSuccess() + " Help Menu - Page 5");
            e.reply(Embed.build());
        } else {

            e.reply(e.getClient().getWarning() + " Please input a correct page number.");
            Embed.setDescription("Page One: Info\n" +
                    "Page Two: Fun\n" +
                    "Page Three: Moderation\n" +
                    "Page Four: Admin\n" +
                    "Page Five: Other");

            e.reply(Embed.build());
        }
    }
}
