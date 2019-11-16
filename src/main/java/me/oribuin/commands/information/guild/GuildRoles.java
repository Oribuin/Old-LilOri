package me.oribuin.commands.information.guild;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.jagrosh.jdautilities.menu.Paginator;
import me.oribuin.main.Info;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.exceptions.PermissionException;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class GuildRoles extends Command {

    private final Paginator.Builder pbuilder;

    public GuildRoles(EventWaiter waiter) {

        this.name = "GuildRoles";
        this.help = "List of roles the guild has";
        this.aliases = new String[]{"groles", "roles"};
        this.botPermissions = new Permission[]{
                Permission.MESSAGE_EMBED_LINKS,
                Permission.MESSAGE_ADD_REACTION};
        //this.userPermissions = new Permission[]{
        // Permission.MANAGE_ROLES};
        this.guildOnly = true;
        this.ownerCommand = Settings.OfflineMode;

        pbuilder = new Paginator.Builder()//.setColumns(1)
                .setItemsPerPage(10)
                .showPageNumbers(true)
                .waitOnSinglePage(true)
                .useNumberedItems(false)
                .setFinalAction(m -> {
                    try {
                        m.clearReactions().queue();
                    } catch (PermissionException ex) {
                        m.delete().queue();
                    }
                })
                .setEventWaiter(waiter)
                .setTimeout(1, TimeUnit.MINUTES);
    }

    @Override
    protected void execute(CommandEvent event) {
        int page = 1;
        if (!event.getArgs().isEmpty()) {
            try {
                page = Integer.parseInt(event.getArgs());
            } catch (NumberFormatException e) {
                event.reply(event.getClient().getError() + " `" + event.getArgs() + "` is not a valid integer!");
                return;
            }
        }

        pbuilder.clearItems();
        event.getGuild().getRoles().stream()
                .map(g -> g.getAsMention() + " **-** ID: " + g.getId())
                .forEach(pbuilder::addItems);
        event.getJDA().getShardInfo();

        Paginator p = pbuilder.setColor(event.isFromType(ChannelType.TEXT) ? event.getSelfMember().getColor() : Color.black)
                .setText(event.getClient().getSuccess() + " **" + event.getGuild().getName() + "** Role List\n**Total:** " + event.getGuild().getRoles().size())
                .setUsers(event.getAuthor())
                .setBulkSkipNumber(3)
                .setColor(event.getGuild().getMember(event.getAuthor()).getColor())
                .build();

        p.paginate(event.getChannel(), page);
    }
}