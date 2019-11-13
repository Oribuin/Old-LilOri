package me.oribuin.main;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import me.oribuin.commands.admin.bot.GitHub;
import me.oribuin.commands.admin.bot.PermList;
import me.oribuin.commands.admin.bot.SayCMD;
import me.oribuin.commands.admin.channel.ChClone;
import me.oribuin.commands.admin.channel.ChCreate;
import me.oribuin.commands.admin.channel.ChSlowmode;
import me.oribuin.commands.admin.ori.LeaveServer;
import me.oribuin.commands.fun.EightBall;
import me.oribuin.commands.fun.GayMeter;
import me.oribuin.commands.information.bot.BotInfo;
import me.oribuin.commands.information.bot.BotPing;
import me.oribuin.commands.information.bot.Help;
import me.oribuin.commands.information.guild.*;
import me.oribuin.commands.information.user.UserAvatar;
import me.oribuin.commands.information.user.UserInfo;
import me.oribuin.commands.moderation.Ban;
import me.oribuin.commands.moderation.Kick;
import me.oribuin.commands.moderation.Purge;
import me.oribuin.commands.music.JoinVC;
import me.oribuin.commands.music.LeaveVC;
import me.oribuin.commands.testers.EditTime;
import me.oribuin.commands.testers.Tester;
import me.oribuin.events.MentionBot;
import me.oribuin.events.MentionOri;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {

        CommandClientBuilder builder = new CommandClientBuilder();

        builder.setOwnerId("345406020450779149");

        builder.setPrefix(";");
        builder.useHelpBuilder(false);
        //  Bot is in Online Mode
//        /*
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("Version v" + Info.VERSION));
//        */

        // Bot is in Offline Mode
        /*
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.setActivity(Activity.playing("In Offline Mode"));
         */


        builder.addCommands(
                new Tester(),
                //Ori Commands
                new LeaveServer(),
                //Help Command
                new Help(),
                new GuildList(new EventWaiter()),

                //Admin
                // Bot
                new PermList(),
                // Channels

                new ChCreate(),
                new ChSlowmode(),
                new ChClone(),
                new EditTime(),

                //Moderation
                new Purge(),
                new Kick(),
                new Ban(),

                //Fun
                new GayMeter(),
                new EightBall(),
                new SayCMD(),
                // Ouc
                //Information
                // User
                new UserAvatar(),
                new UserInfo(),
                // Bot
                new BotPing(),
                new BotInfo(),
                new GitHub(),
                // Guild
                new GuildInfo(),
                new GuildRoles(new EventWaiter()),
                new GuildMembers(new EventWaiter()),
                new GuildChannels(new EventWaiter()),

                //Music
                // Basic Voice Channel
                new JoinVC(),
                new LeaveVC()

        );

        CommandClient client = builder.build();

        new JDABuilder(AccountType.BOT)
                .setToken(Settings.TOKEN)
                .addEventListeners(client, new MentionOri(), new MentionBot())
                .build();

        System.out.println(" ");
        System.out.println("==================");
        System.out.println("Bot Loaded: Lil' Ori#7349");
        System.out.println("Bot Author: Ori#0004");
        System.out.println("Library: Discord-JDA");
        System.out.println("Version: v" + Info.VERSION);
        System.out.println("==================");
    }
}
