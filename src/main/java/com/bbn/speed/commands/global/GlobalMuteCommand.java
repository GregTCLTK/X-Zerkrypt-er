package com.bbn.speed.commands.global;

/*
 * @Author Skidder / GregTCLTK
 */

import com.bbn.speed.Speed;
import com.bbn.speed.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.time.Instant;

public class GlobalMuteCommand implements Command {

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Speed.rethink.isTeam(event.getAuthor().getId())) {
            if (event.getMessage().getMentionedUsers().size() == 1) {
                if (Speed.rethink.isUserMuted(event.getMessage().getMentionedUsers().get(0).getId())) {
                    Speed.rethink.setUserMute(event.getMessage().getMentionedUsers().get(0).getId(), false);
                    event.getTextChannel().sendMessage(new EmbedBuilder()
                            .setTitle("Erfolgreich ausgeführt")
                            .setDescription("Ich habe erfolgreich " + event.getMessage().getMentionedUsers().get(0).getAsTag() + " entmuted.")
                            .setColor(Color.GREEN)
                            .setFooter("Speed", "https://cdn.discordapp.com/avatars/648542896269819906/4bd3ff019e6107a65f8e96d6d9de7983.png")
                            .setTimestamp(Instant.now())
                            .build()).queue();
                } else {
                    Speed.rethink.setUserMute(event.getMessage().getMentionedUsers().get(0).getId(), true);
                    event.getTextChannel().sendMessage(new EmbedBuilder()
                            .setTitle("Erfolgreich ausgeführt")
                            .setDescription("Ich habe erfolgreich " + event.getMessage().getMentionedUsers().get(0).getAsTag() + " gemuted.")
                            .setColor(Color.GREEN)
                            .setFooter("Speed", "https://cdn.discordapp.com/avatars/648542896269819906/4bd3ff019e6107a65f8e96d6d9de7983.png")
                            .setTimestamp(Instant.now())
                            .build()).queue();
                }
            } else if (args[0].length() == 18) {
                try {
                    User u = event.getJDA().getUserById(args[0]);
                    if (Speed.rethink.isUserMuted(u.getId())) {
                        Speed.rethink.setUserMute(u.getId(), false);
                        event.getTextChannel().sendMessage(new EmbedBuilder()
                                .setTitle("Erfolgreich ausgeführt")
                                .setDescription("Ich habe erfolgreich " + u.getAsTag() + " entmuted.")
                                .setColor(Color.GREEN)
                                .setFooter("Speed", "https://cdn.discordapp.com/avatars/648542896269819906/4bd3ff019e6107a65f8e96d6d9de7983.png")
                                .setTimestamp(Instant.now())
                                .build()).queue();
                    } else {
                        Speed.rethink.setUserMute(u.getId(), true);
                        event.getTextChannel().sendMessage(new EmbedBuilder()
                                .setTitle("Erfolgreich ausgeführt")
                                .setDescription("Ich habe erfolgreich " + u.getAsTag() + " gemuted.")
                                .setColor(Color.GREEN)
                                .setFooter("Speed", "https://cdn.discordapp.com/avatars/648542896269819906/4bd3ff019e6107a65f8e96d6d9de7983.png")
                                .setTimestamp(Instant.now())
                                .build()).queue();
                    }
                } catch (Exception e) {
                    event.getTextChannel().sendMessage(new EmbedBuilder()
                            .setTitle("Nicht möglich")
                            .setDescription("Der angegebene Benutzer war noch nie auf einem Server auf dem ich auch war.")
                            .setColor(Color.RED)
                            .setFooter("Speed", "https://cdn.discordapp.com/avatars/648542896269819906/4bd3ff019e6107a65f8e96d6d9de7983.png")
                            .setTimestamp(Instant.now())
                            .build()).queue();
                }
            } else {
                event.getTextChannel().sendMessage(new EmbedBuilder()
                        .setTitle("Falsche Nutzung")
                        .setDescription("Du musst genau einen User angeben.")
                        .setColor(Color.RED)
                        .setFooter("Speed", "https://cdn.discordapp.com/avatars/648542896269819906/4bd3ff019e6107a65f8e96d6d9de7983.png")
                        .setTimestamp(Instant.now())
                        .build()).queue();
            }
        } else {
            event.getTextChannel().sendMessage(new EmbedBuilder()
                    .setTitle("Keine Berechtigung")
                    .setDescription("Du bist nicht Teil des Teams!")
                    .setColor(Color.RED)
                    .setFooter("Speed", "https://cdn.discordapp.com/avatars/648542896269819906/4bd3ff019e6107a65f8e96d6d9de7983.png")
                    .setTimestamp(Instant.now())
                    .build()).queue();
        }
    }
}
