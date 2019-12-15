package me.oribuin.commands.moderation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.oribuin.main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.role.RoleCreateEvent;
import net.dv8tion.jda.api.requests.restaction.GuildAction;

import java.awt.*;
import java.util.EnumSet;

public class Mute extends Command {
    public Mute() {
        this.name = "Mute";
        this.help = "Mute a Member in the guild.";
        this.cooldownScope = CooldownScope.USER;
        this.cooldown = 7;
        this.userPermissions = new Permission[]{Permission.MANAGE_CHANNEL, Permission.MANAGE_ROLES, Permission.MANAGE_PERMISSIONS};
        this.userPermissions = new Permission[]{Permission.MANAGE_CHANNEL, Permission.MANAGE_ROLES, Permission.MANAGE_PERMISSIONS};
        this.ownerCommand = true;
    }

    @Override
    protected void execute(CommandEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");

        // If command is just ;mute
        if (args.length < 2) {
            // Tell them to input correct args
            e.reply(e.getClient().getWarning() + " Correct usage `;mute [@User]`");
            // If the user mentioned is the message author
        } else {

            // Define Embed
            EmbedBuilder Embed = new EmbedBuilder()
                    .setColor(Color.decode(Info.COLOR))
                    .setAuthor("You have been muted!")
                    .setFooter("Lil Ori v" + Info.VERSION)
                    .setDescription("Muted in: " + e.getGuild().getName() + " (" + e.getGuild().getId() + ")\n" +
                            "Muted By: " + e.getMessage().getAuthor().getAsTag());

            // Check if first arg is "Setup"
            /*
            if (args[1].equalsIgnoreCase("Setup")) {
                // If muted doesn't exist
                if (e.getGuild().getRolesByName("Muted", true).isEmpty()) {
                    // Create role
                    e.getGuild().createRole().setName("Muted")
                            // add permissions
                            .setPermissions(Permission.MESSAGE_READ,
                                    Permission.MESSAGE_HISTORY,
                                    Permission.VIEW_CHANNEL,
                                    Permission.VOICE_CONNECT).queue(m -> {
                    });
                    // say the role was created
                    e.reply(e.getClient().getSuccess() + " Successfully Created Muted Role, You may need to move the position of the role higher");
                    try {
                        for (GuildChannel channel : e.getGuild().getChannels()) {
                            channel.putPermissionOverride(e.getGuild().getRolesByName("Muted", true).get(0)).queue(permissionOverride -> {
                                permissionOverride.delete().queue();
                            });

                            channel.createPermissionOverride(e.getGuild().getRolesByName("Muted", true).get(0))
                                    .setAllow(e.getGuild().getRolesByName("Muted", true).get(0).getPermissions())
                                    .queue();
                        }
                    } catch (Exception err) {
                        err.printStackTrace();
                    }
                } else {
                    // If Muted Role already exists tell the executir
                    e.reply(e.getClient().getWarning() + " A \"muted\" role already exists, We have setup channels however.");

                    try {
                        for (GuildChannel channel : e.getGuild().getChannels()) {
                            channel.upsertPermissionOverride(e.getGuild().getRolesByName("Muted", true).get(0)).queue(permissionOverride -> {
                                permissionOverride.delete().queue();
                            });

                            channel.putPermissionOverride(e.getGuild().getRolesByName("Muted", true).get(0))
                                    .setAllow(e.getGuild().getRolesByName("Muted", true).get(0).getPermissions())
                                    .queue();
                        }
                    } catch (Exception err) {
                        err.printStackTrace();
                    }
                }
                // if args[1] is a user being mentioned

            } else  */
            if (!e.getMessage().getMentionedMembers().isEmpty()
                    && !e.getMessage().getMentionedMembers().get(0).getAsMention().isEmpty()) {

                if (e.getGuild().getRolesByName("Muted", true).isEmpty()) {
                    e.reply(e.getClient().getWarning() + "You must have a muted role setup.\nLearn More about our mute command on https://github.com/Oribuin/Lil-Ori/wiki/MuteCommand/ (Coming Soon)");
                }

                if (e.getMessage().getMentionedMembers().get(0).equals(e.getAuthor())) {
                    e.reply(e.getClient().getWarning() + " You cannot mute yourself.");
                    return;
                    // If User mentioned is owner, or has the same/higher role than user executing command.
                } else if (e.getMessage().getMentionedMembers().get(0).isOwner()
                        || e.getMessage().getMentionedMembers().get(0).getRoles().get(0).getPosition() > e.getGuild().getMember(e.getAuthor()).getRoles().get(0).getPosition()
                        || e.getMessage().getMentionedMembers().get(0).getRoles().get(0).getPosition() > e.getSelfMember().getRoles().get(0).getPosition()) {

                    e.reply(e.getAuthor().getAsMention() + ", You cannot mute this user due to Role Hierarchy.");
                    return;
                    // If the user is a bot, this will prevent errors with DMs.
                } else if (e.getMessage().getMentionedMembers().get(0).getUser().isBot()) {
                    e.reply(e.getClient().getWarning() + " I cannot mute other bots!");
                    return;
                }
                // Add the Muted Role to them
                e.getGuild().addRoleToMember(e.getMessage().getMentionedMembers().get(0).getId(), e.getGuild().getRolesByName("Muted", true).get(0)).queue();
                // Say they were muted
                e.reply(e.getClient().getSuccess() + " Successfully Muted " + e.getMessage().getMentionedMembers().get(0).getUser().getAsTag());

                // Tell the user they were muted.
                e.getMessage().getMentionedUsers().get(0).openPrivateChannel().queue(m -> {
                    m.sendMessage(Embed.build()).queue();
                });
            } else {
                // if its not @User or "setup"
                e.reply(e.getClient().getWarning() + " Correct usage `;mute [Setup/@User]`");
            }
        }
    }
}