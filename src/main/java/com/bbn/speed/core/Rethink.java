package com.bbn.speed.core;

/*
 * @Author Skidder / GregTCLTK
 */

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.util.NoSuchElementException;

public class Rethink {
    private RethinkDB r = RethinkDB.r;
    private Connection conn;

    public void connect() {
        try {
            conn = r.connection()
                    .hostname("localhost")
                    .db("Speed")
                    .port(28015)
                    .user("admin", "")
                    .connect();
            System.out.println("DB CONNECTED");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DB CONNECTION FAILED");
        }
    }

    private JSONArray getAsArray(String table, String where, String value) {
        try {
            String string = String.valueOf(r.table(table).filter(row -> row.g(where.toLowerCase()).eq(value)).coerceTo("array").toJson().run(conn).first());
            return new JSONArray(string);
        } catch (NoSuchElementException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }

    private Object get(String table, String where, String value, String column) {
        JSONArray array = this.getAsArray(table, where, value);
        if (array.length() > 0)
            if (array.getJSONObject(0).has(column))
                return array.getJSONObject(0).get(column);
            else return null;
        else return null;
    }

    private void update(String table, String where, String what, String value) {
        try {
            r.table(table).get(where).update(r.hashMap(what, value)).run(conn);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    private void update(String table, String where, String what, boolean value) {
        try {
            r.table(table).get(where).update(r.hashMap(what, value)).run(conn);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    private void insert(String table, Object object) {
        try {
            r.table(table).insert(object).run(conn);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    private void remove(String table, String where, String value) {
        r.table(table).filter(row -> row.g(where.toLowerCase()).eq(value)).delete().run(conn);
    }

    public void setBotPremium(String id) {
        this.update("user", id, "bot_premium", true);
    }

    public boolean isBotPremium(String id) {
        return (Boolean) this.get("user", "id", id, "bot_premium");
    }

    public void setLottoTime(Instant time, String id) {
        this.update("user", id, "last_lotto", time.toString());
    }

    public Instant getLottoTime(String id) {
        try {
            return Instant.parse((CharSequence) this.get("user", "id", id, "last_lotto"));
        } catch (Exception e) {
            return null;
        }
    }

    public void setMoney(String id, int amount) {
        r.table("user").get(id).update(r.hashMap("money", amount)).run(conn);
    }

    public int getMoney(String id) {
        return (int) this.get("user", "id", id, "money");
    }

    public void setGlobal(String guild_id, String channel_id) {
        this.update("guilds", guild_id, "channel", channel_id);
    }

    public boolean hasGlobal(String guild_id) {
        return !JSONObject.NULL.equals(this.get("guilds", "id", guild_id, "channel"));
    }

    public String getGlobal(String guild_id) {
        if (JSONObject.NULL.equals(this.get("guilds", "id", guild_id, "channel"))) {
            return null;
        } else {
            return (String) this.get("guilds", "id", guild_id, "channel");
        }
    }

    public void addTeam(String id) {
        this.update("user", id, "team", true);
    }

    public void removeTeam(String id) {
        this.update("user", id, "team", false);
    }

    public boolean isTeam(String id) {
        return (Boolean) this.get("user", "id", id, "team");
    }

    public void setUserMute(String id, boolean b) {
        this.update("user", id, "muted", b);
    }

    public boolean isUserMuted(String id) {
        return (Boolean) this.get("user", "id", id, "muted");
    }

    public void setDailyTime(Instant time, String id) {
        this.update("user", id, "last_daily", time.toString());
    }

    public Instant getDailyTime(String id) {
        try {
            return Instant.parse((CharSequence) this.get("user", "id", id, "last_daily"));
        } catch (Exception e) {
            return null;
        }
    }

    public void setWorkTime(Instant time, String id) {
        this.update("user", id, "last_work", time.toString());
    }

    public Instant getWorkTime(String id) {
        try {
            return Instant.parse((CharSequence) this.get("user", "id", id, "last_work"));
        } catch (Exception e) {
            return null;
        }
    }

    public void setGuildMute(String id, boolean b) {
        this.update("guilds", id, "muted", b);
    }

    public boolean isGuildMuted(String id) {
        return (Boolean) this.get("guilds", "id", id, "muted");
    }

    public void setRobTime(Instant time, String id) {
        this.update("user", id, "last_rob", time.toString());
    }

    public Instant getRobTime(String id) {
        try {
            return Instant.parse((CharSequence) this.get("user", "id", id, "last_rob"));
        } catch (Exception e) {
            return null;
        }
    }

    public void setHelp(String id) {
        this.insert("help", r.hashMap("id", id));
    }

    public boolean isHelp(String id) {
        return this.get("help", "id", id, "id") != null;
    }

    public void insertUser(String id) {
        this.insert("user", r
                .hashMap("id", id)
                .with("bot_premium", false)
                .with("last_lotto", null)
                .with("last_daily", null)
                .with("last_work", null)
                .with("money", 0)
                .with("team", false)
                .with("muted", false));
    }

    public void insertGuild(String id) {
        this.insert("guilds", r
                .hashMap("id", id)
                .with("channel", null)
                .with("muted", false)
        );
    }

    public void removeGuild(String id) {
        this.remove("guilds", "id", id);
    }

    public void removeUser(String id) {
        this.remove("user", "id", id);
    }
}
