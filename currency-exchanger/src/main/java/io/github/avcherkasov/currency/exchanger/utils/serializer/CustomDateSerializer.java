package io.github.avcherkasov.currency.exchanger.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.avcherkasov.currency.exchanger.utils.CustomDateUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Custom date serializer
 *
 * @author Anatoly Cherkasov
 * @see io.github.avcherkasov.currency.exchanger.model.ConverterResult
 */
public class CustomDateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator generator, SerializerProvider provider) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(CustomDateUtils.DATE_PATTERN);
        String formattedDate = dateFormat.format(date);
        generator.writeString(formattedDate);
    }

}
