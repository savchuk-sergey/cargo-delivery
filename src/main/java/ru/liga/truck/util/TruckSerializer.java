package ru.liga.truck.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ru.liga.truck.Truck;

import java.io.IOException;

public class TruckSerializer extends StdSerializer<Truck> {

    public TruckSerializer() {
        this(null);
    }

    public TruckSerializer(Class<Truck> t) {
        super(t);
    }

    @Override
    public void serialize(Truck truck, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        String placementsString = truck.toString();
        int height = truck.getFullness().size();
        int width = truck.getFullness().get(0).size();

        jgen.writeStartObject();
        jgen.writeStringField("placements", placementsString);
        jgen.writeNumberField("height", height);
        jgen.writeNumberField("width", width);
        jgen.writeEndObject();
    }
}
