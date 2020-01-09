package com.bbn.XZerkrypter.commands.misc;

/*
 * @Author Skidder / GregTCLTK
 */

import com.bbn.XZerkrypter.XZerkrypter;
import com.bbn.XZerkrypter.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.time.Instant;

public class StatsCommand implements Command {

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder eb = new EmbedBuilder();

        if (XZerkrypter.rethink.isBotPremium(event.getAuthor().getId())) {
            eb.addField("Bot Plus", "ja", true);
        } else {
            eb.addField("Bot Plus", "nein", true);
        }
        event.getTextChannel().sendMessage(eb
                .setTitle("Deine Statistiken")
                .addField("Bäume", String.valueOf(XZerkrypter.rethink.getMoney(event.getAuthor().getId())), true)
                .setColor(Color.GREEN)
                .setTimestamp(Instant.now())
                .build()).queue();
    }
}
