package csee.ce291_team02.Data.LmaoNinjaApiClient;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.math.BigDecimal;

public class MugBigDecimalAdapter implements JsonDeserializer<BigDecimal>, JsonSerializer<Double> {

    public BigDecimal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        BigDecimal cost;
        try {
            cost = json.getAsBigDecimal();
        } catch (NumberFormatException e) {
            cost = new BigDecimal(0);
        }
        return cost;
    }

    public JsonElement serialize(Double src, Type typeOfSrc,
                                 JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }
}
