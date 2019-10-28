package me.oribuin.commands.information.guild;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.jagrosh.jdautilities.menu.Paginator;
import me.oribuin.main.Settings;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.exceptions.PermissionException;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class GuildChannels extends Command {

    private final Paginator.Builder pbuilder;

    public GuildChannels(EventWaiter waiter) {

        this.name = "GuildChannels";
        this.help = "List of channels in the guild";
        this.aliases = new String[]{"gchannels"};
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_ADD_REACTION, Permission.ADMINISTRATOR, Permission.MANAGE_CHANNEL};
        this.guildOnly = true;
        this.userPermissions = new Permission[]{Permission.MANAGE_CHANNEL, Permission.ADMINISTRATOR};
        this.ownerCommand = Settings.OfflineMode;

        pbuilder = new Paginator.Builder().setColumns(1)
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
        event.getGuild().getChannels().stream()
                .map(g -> "<#" + g.getId() + "> **-** ID: " + g.getId())
                .forEach(pbuilder::addItems);
        event.getJDA().getShardInfo();
        Paginator p = pbuilder.setColor(event.isFromType(ChannelType.TEXT) ? event.getSelfMember().getColor() : Color.black)
                .setText(event.getClient().getSuccess() + " **" + event.getGuild().getName() + "** Channel List\n **Total:** " + event.getGuild().getChannels().size())
                .setUsers(event.getAuthor())
                .build();
        p.paginate(event.getChannel(), page);
    }
}