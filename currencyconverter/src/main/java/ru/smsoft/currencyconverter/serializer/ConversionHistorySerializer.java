package ru.smsoft.currencyconverter.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import ru.smsoft.currencyconverter.model.ConversionHistory;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

@JsonComponent
public class ConversionHistorySerializer extends JsonSerializer<ConversionHistory> {
    @Override
    public void serialize(ConversionHistory conversionHistory, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("user", conversionHistory.getUser().getUserLogin());
        jsonGenerator.writeStringField("currencyCharCodeFrom", conversionHistory.getCurrencyCharCodeFrom());
        jsonGenerator.writeStringField("currencyCharCodeTo",conversionHistory.getCurrencyCharCodeTo());
        jsonGenerator.writeStringField("moneyFrom", String.valueOf(conversionHistory.getAmountOfMoneyFrom()));
        jsonGenerator.writeStringField("moneyTo", String.valueOf(conversionHistory.getAmountOfMoneyTo()));
        jsonGenerator.writeStringField("conversionDate", conversionHistory.getDateOfConversion()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        jsonGenerator.writeEndObject();
    }
}
