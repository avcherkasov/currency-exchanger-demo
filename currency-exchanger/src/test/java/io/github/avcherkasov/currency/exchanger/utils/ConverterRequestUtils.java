package io.github.avcherkasov.currency.exchanger.utils;

import io.github.avcherkasov.currency.exchanger.ConverterRequest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Anatoly Cherkasov
 * @see ConverterRequest
 */
public class ConverterRequestUtils {

    private static final String SPLIT_DELIMITER = ", ";

    /**
     * Get converter request from file
     *
     * @param is InputStream
     * @return list of {@link ConverterRequest ConverterRequest}
     */
    public static List<ConverterRequest> getConverterRequestFromFile(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return br.lines().skip(1).map(mapToConverterRequest).collect(Collectors.toList());
    }

    private static Function<String, ConverterRequest> mapToConverterRequest = (line) -> {
        String[] p = line.split(SPLIT_DELIMITER);
        return new ConverterRequest(p[0], p[1], p[2], Double.parseDouble(p[3]), p[4], Integer.parseInt(p[5]));
    };

}
