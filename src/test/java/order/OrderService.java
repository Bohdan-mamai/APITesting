package order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Random;

public class OrderService {

    public static final String ISO_8601_24H_FULL_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public static Order createDefaultOrder() {
        return Order.builder()
                .id(idGenerator())
                .petId(1)
                .quantity(1)
//                .shipDate(dateGenerator())
                .complete(true)
                .status("placed")
                .build();
    }

    public static int idGenerator() {
        Random ran = new Random();
        String id = String.format("%04d", ran.nextInt(10000));
        return Integer.parseInt(id);
    }

    public static String dateGenerator() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ISO_8601_24H_FULL_FORMAT);
        return LocalDateTime.now().format(formatter);
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
//        return LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).format(dateTimeFormatter);

//        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
//        LocalTime time = LocalTime.now();
//        return formatter.format(time);

    }
}
