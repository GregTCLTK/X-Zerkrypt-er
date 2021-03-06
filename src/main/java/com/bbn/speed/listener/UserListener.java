package com.bbn.speed.listener;

/*
 * @Author Skidder / GregTCLTK
 */

import com.bbn.speed.Speed;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class UserListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        Speed.rethink.insertUser(event.getUser().getId());
        super.onGuildMemberJoin(event);
    }

    @Override
    public void onGuildMemberRemove(@Nonnull GuildMemberRemoveEvent event) {
        //XZerkrypter.rethink.removeUser(event.getUser().getId());
        super.onGuildMemberRemove(event);
    }
}
