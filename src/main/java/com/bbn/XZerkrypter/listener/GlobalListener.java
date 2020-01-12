package com.bbn.XZerkrypter.listener;

/*
 * @Author Skidder / GregTCLTK
 */

import com.bbn.XZerkrypter.XZerkrypter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.awt.*;
import java.time.Instant;
import java.util.Objects;

public class GlobalListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            if (XZerkrypter.rethink.hasGlobal(event.getGuild().getId()) && event.getChannel().equals(event.getGuild().getTextChannelById(XZerkrypter.rethink.getGlobal(event.getGuild().getId())))) {
                event.getMessage().delete().queue();
                if (!XZerkrypter.rethink.isMuted(event.getAuthor().getId())) {
                    for (Guild g : event.getJDA().getGuilds()) {
                        if (XZerkrypter.rethink.hasGlobal(g.getId())) {
                            EmbedBuilder eb = new EmbedBuilder()
                                    .setAuthor(event.getAuthor().getAsTag(), event.getAuthor().getAvatarUrl(), event.getAuthor().getAvatarUrl())
                                    .setThumbnail(event.getGuild().getIconUrl())
                                    .setTitle("**" + event.getGuild().getName() + "**")
                                    .setDescription(event.getMessage().getContentRaw())
                                    .setFooter("Message provided by Speed", "https://cdn.discordapp.com/avatars/648542896269819906/4bd3ff019e6107a65f8e96d6d9de7983.png")
                                    .setTimestamp(Instant.now());

                            if (event.getAuthor().getId().equals("601366418759483393")) {
                                eb.setColor(Color.RED).setTitle("**\uD83D\uDC51 Inhaber**");
                            } else if (XZerkrypter.rethink.isTeam(event.getAuthor().getId())) {
                                eb.setColor(Color.BLUE).setTitle("**" + event.getGuild().getName() + "**").setAuthor("⚒ " + event.getAuthor().getAsTag(), event.getAuthor().getAvatarUrl(), event.getAuthor().getAvatarUrl());
                            }

                            Objects.requireNonNull(g.getTextChannelById(XZerkrypter.rethink.getGlobal(g.getId()))).sendMessage(eb.build()).queue();
                        }
                    }
                } else {
                    event.getAuthor().openPrivateChannel().complete().sendMessage(new EmbedBuilder()
                            .setTitle("Nicht erlaubt")
                            .setDescription("Dir ist es nicht erlaubt im Global Chat zu schreiben!")
                            .setColor(Color.RED)
                            .setTimestamp(Instant.now())
                            .build()).queue();
                }
            }
        }
    }
}
