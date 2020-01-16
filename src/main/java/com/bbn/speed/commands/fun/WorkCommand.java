package com.bbn.speed.commands.fun;

/*
 * @Author Skidder / GregTCLTK
 */

import com.bbn.speed.Speed;
import com.bbn.speed.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.time.Instant;
import java.util.Random;

public class WorkCommand implements Command {

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Speed.rethink.getWorkTime(event.getAuthor().getId()) == null) {
            work(event);
        } else if (Speed.rethink.getWorkTime(event.getAuthor().getId()).isBefore(Instant.now().minusSeconds(1800000L))) {
            work(event);
        } else event.getTextChannel().sendMessage(new EmbedBuilder()
                .setTitle("Nicht möglich")
                .setDescription("Du kannst nur alle 30 Minuten arbeiten gehen.")
                .setColor(Color.RED)
                .setTimestamp(Instant.now())
                .build()).queue();
    }

    private void work(MessageReceivedEvent event) {
        int i = new Random().nextInt(5);
        Random r = new Random();
        int low = 20;
        int high = 70;
        int salary = r.nextInt(high-low) + low;

        switch (i) {
            case 0:
                event.getTextChannel().sendMessage(new EmbedBuilder()
                        .setTitle("Erfolgreich gearbeitet")
                        .setDescription("Du hast bei Angela Merkel als Putzfrau gearbeitet und erhälst dafür " + salary + " Bäume.")
                        .setColor(Color.MAGENTA)
                        .setTimestamp(Instant.now())
                        .build()).queue();
                break;
            case 1:
                event.getTextChannel().sendMessage(new EmbedBuilder()
                        .setTitle("Erfolgreich gearbeitet")
                        .setDescription("Du hast im US-Verteidigungsministerium die Toiletten sauber gemacht und erhältst dafür " + salary + " Bäume.")
                        .setColor(Color.MAGENTA)
                        .setTimestamp(Instant.now())
                        .build()).queue();
                break;
            case 2:
                event.getTextChannel().sendMessage(new EmbedBuilder()
                        .setTitle("Erfolgreich gearbeitet")
                        .setDescription("Du hast als Supporter bei Microsoft " + salary + " Bäume verdient.")
                        .setColor(Color.MAGENTA)
                        .setTimestamp(Instant.now())
                        .build()).queue();
                break;
            case 3:
                event.getTextChannel().sendMessage(new EmbedBuilder()
                        .setTitle("Erfolgreich gearbeitet")
                        .setDescription("Du hast erfolgreich an der Rezeption eines 5 Sterne Hotels gearbeitet und erhältst dafür " + salary + " Bäume.")
                        .setColor(Color.MAGENTA)
                        .setTimestamp(Instant.now())
                        .build()).queue();
                break;
            case 4:
                event.getTextChannel().sendMessage(new EmbedBuilder()
                        .setTitle("Erfolgreich gearbeitet")
                        .setDescription("Du hast als Full Stack Developer bei Discord " + salary + " Bäume verdient.")
                        .setColor(Color.MAGENTA)
                        .setTimestamp(Instant.now())
                        .build()).queue();
                break;
            case 5:
                event.getTextChannel().sendMessage(new EmbedBuilder()
                        .setTitle("Erfolgreich gearbeitet")
                        .setDescription("Du hast in einer Grundschule Mathe unterrichtet und " + salary + " Bäume verdient.")
                        .setColor(Color.MAGENTA)
                        .setTimestamp(Instant.now())
                        .build()).queue();
                break;
        }
        Speed.rethink.setMoney(event.getAuthor().getId(), Speed.rethink.getMoney(event.getAuthor().getId()) + salary);
        Speed.rethink.setWorkTime(Instant.now(), event.getAuthor().getId());
        Speed.rethink.setMoney(event.getAuthor().getId(), Speed.rethink.getMoney(event.getAuthor().getId()) + 150);
    }
}
