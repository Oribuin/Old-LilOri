package me.oribuin.main;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import me.oribuin.commands.admin.bot.GitHub;
import me.oribuin.commands.admin.bot.PermList;
import me.oribuin.commands.admin.bot.SayCMD;
import me.oribuin.commands.admin.channel.ChCreate;
import me.oribuin.commands.admin.channel.ChDelete;
import me.oribuin.commands.admin.channel.ChSlowmode;
import me.oribuin.commands.admin.guild.GuClone;
import me.oribuin.commands.admin.ori.*;
import me.oribuin.commands.fun.Cookie;
import me.oribuin.commands.fun.EightBall;
import me.oribuin.commands.fun.GayMeter;
import me.oribuin.commands.hangout.MsgLog;
import me.oribuin.commands.hangout.SayEmbed;
import me.oribuin.commands.information.bot.*;
import me.oribuin.commands.information.guild.*;
import me.oribuin.commands.information.user.UserAvatar;
import me.oribuin.commands.information.user.UserInfo;
import me.oribuin.commands.moderation.*;
import me.oribuin.commands.music.JoinVC;
import me.oribuin.commands.music.LeaveVC;
import me.oribuin.commands.testers.EditTime;
import me.oribuin.commands.testers.Tester;
import me.oribuin.events.James;
import me.oribuin.events.MentionBot;
import me.oribuin.events.MentionOri;
import me.oribuin.events.Startup;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {


        EventWaiter waiter = new EventWaiter();

        CommandClientBuilder builder = new CommandClientBuilder();

        builder.setOwnerId("345406020450779149");

        builder.setEmojis("<a:Agree:645279978929258506>", "⚠️", "<a:Disagree:645279979650809856>");

        builder.setPrefix(";");
        builder.useHelpBuilder(false);

        builder.setStatus(OnlineStatus.ONLINE);

        builder.addCommands(
                // Info Commands
                new InviteLink(), new Suggest(), new GitHub(),
                new Tester(),
                //Ori Commands
                new LeaveServer(), new GetInvite(), new DeleteServer(), new GuildList(waiter), new MsgLog(), new SayEmbed(),

                //Help Command
                new Help(), new GetUsage(),

                //Admin
                // Bot
                new PermList(),
                // Guilds
                new GuClone(),
                // Channels

                new ChCreate(), new ChDelete(), new ChSlowmode(), new EditTime(),

                //Moderation
                new Purge(), new Kick(), new Ban(), new Mute(),

                //Fun
                new GayMeter(), new EightBall(), new SayCMD(), new Cookie(),
                //Information
                // User
                new UserAvatar(), new UserInfo(),
                // Bot
                new BotPing(), new BotInfo(),
                // Guild
                new GuildInfo(), new GuildRoles(waiter), new GuildMembers(waiter), new GuildChannels(waiter),

                // Roles
                new RoleInfo(),

                //Music
                // Basic Voice Channel
                new JoinVC(), new LeaveVC()

        );

        CommandClient client = builder.build();
        JDA jda = new JDABuilder(AccountType.BOT)
                .setToken(Settings.TOKEN)
                .addEventListeners(client, waiter,
                        new MentionOri(),
                        new MentionBot(),
                        new James(),
                        new Startup())
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
