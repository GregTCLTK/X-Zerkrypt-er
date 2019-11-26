package com.bbn.XZerkrypter.commands.fun;

/*
 * @Author Skidder / GregTCLTK
 */

import com.bbn.XZerkrypter.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.time.Instant;

public class LottoCommand implements Command {

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        boolean lotto = Math.random() < 0.07;
        if (lotto) {
            if (event.getGuild().getSelfMember().hasPermission(Permission.MANAGE_ROLES)) {
                    if (event.getTextChannel().getSlowmode() == 6) {
                        event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById("648424818441715723")).reason("Im Lotto gewonnen").queue();
                    } else {
                        event.getTextChannel().getManager().setSlowmode(6).queue();
                        event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById("648424818441715723")).reason("Im Lotto gewonnen").queue();
                    }
            } else {
                event.getTextChannel().sendMessage(new EmbedBuilder()
                        .setTitle("Keine Permission")
                        .setDescription("Ich benötige die `Manage Roles` Permission um diesen Command auszuführen.")
                        .setColor(Color.RED)
                        .setTimestamp(Instant.now())
                        .build()).queue();
            }
        } else {
            event.getTextChannel().sendMessage(new EmbedBuilder()
                    .setTitle("Leider nichts")
                    .setDescription("Das war wohl leider nichts. Vielleicht nächstes mal.")
                    .setColor(Color.CYAN)
                    .setTimestamp(Instant.now())
                    .build()).queue();
        }
    }
}
