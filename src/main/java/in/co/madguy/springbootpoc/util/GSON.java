package in.co.madguy.springbootpoc.util;

import com.google.gson.*;
import in.co.madguy.springbootpoc.configuration.SpringfoxJsonToGsonAdapter;
import springfox.documentation.spring.web.json.Json;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static in.co.madguy.springbootpoc.util.Constants.DEFAULT_DATE_FORMAT;
import static in.co.madguy.springbootpoc.util.Constants.DEFAULT_DATE_FORMAT_TZ;

public class GSON {

    private final Gson _GSON;

    private GSON() {
        JsonSerializer<LocalDateTime> localDateTimeJsonSerializer = new JsonSerializer<LocalDateTime>() {
            @Override
            public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(src.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
            }
        };

        JsonDeserializer<LocalDateTime> localDateTimeJsonDeserializer = new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
            }
        };

        JsonSerializer<ZonedDateTime> zonedDateTimeJsonSerializer = new JsonSerializer<ZonedDateTime>() {
            @Override
            public JsonElement serialize(ZonedDateTime src, Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(src.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT_TZ)));
            }
        };

        JsonDeserializer<ZonedDateTime> zonedDateTimeJsonDeserializer = new JsonDeserializer<ZonedDateTime>() {
            @Override
            public ZonedDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return ZonedDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT_TZ));
            }
        };

        _GSON = new GsonBuilder()
            //.excludeFieldsWithoutExposeAnnotation()
            .registerTypeAdapter(LocalDateTime.class, localDateTimeJsonSerializer)
            .registerTypeAdapter(LocalDateTime.class, localDateTimeJsonDeserializer)
            .registerTypeAdapter(ZonedDateTime.class, zonedDateTimeJsonSerializer)
            .registerTypeAdapter(ZonedDateTime.class, zonedDateTimeJsonDeserializer)
            .registerTypeAdapter(Json.class, new SpringfoxJsonToGsonAdapter())
            .create();
    }

    private static class GsonHelper {
        private static GSON INSTANCE = null;

        private static Gson getGson() {
            if (null == INSTANCE) {
                INSTANCE = new GSON();
            }

            return INSTANCE._GSON;
        }
    }

    /**
     * This method provides access to the Singleton instance of com.google.gson.Gson.
     *
     * @return Singleton com.google.gson.Gson instance
     */
    public static Gson gson() {
        return GsonHelper.getGson();
    }
}