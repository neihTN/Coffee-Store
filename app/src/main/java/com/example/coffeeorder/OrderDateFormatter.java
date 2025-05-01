package com.example.coffeeorder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OrderDateFormatter {
    public static String formatOrderDate(Date orderDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM | hh:mm a", Locale.ENGLISH);
        return dateFormat.format(orderDate);
    }
}

