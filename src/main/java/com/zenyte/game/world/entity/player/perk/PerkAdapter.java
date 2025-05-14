package com.zenyte.game.world.entity.player.perk;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * @author Tommeh | 11-1-2019 | 20:15
 * @see <a href="https://www.rune-server.ee/members/tommeh/">Rune-Server profile</a>}
 */
public class PerkAdapter implements JsonSerializer<Perk>, JsonDeserializer<Perk> {
    @Override
    public JsonElement serialize(Perk perk, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject result = new JsonObject();
        final PerkWrapper wrapper = PerkWrapper.get(perk.getClass());
        // the “type” is still the wrapper.name()
        result.add("type", new JsonPrimitive(wrapper.name()));
        // now we grab the category from the wrapper
        result.add("category", new JsonPrimitive(wrapper.getCategory().name()));
        // and the actual perk‐specific fields
        result.add("properties", context.serialize(perk, perk.getClass()));
        return result;
    }


    @Override
    public Perk deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
        throws JsonParseException {
        final JsonObject obj = json.getAsJsonObject();
        final String type = obj.get("type").getAsString();
        final PerkWrapper wrapper = PerkWrapper.get(type);
        if (wrapper == null) {
            throw new JsonParseException("Unknown perk: " + type);
        }
        // ✂ here’s the only change:
        JsonElement props = obj.get("properties");
        // we hand GSON *only* the properties object, so it will correctly populate level, maxLevel, etc.
        return context.deserialize(props, wrapper.getPerk());
    }
}
