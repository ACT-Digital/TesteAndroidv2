package com.drebtchinsky.testeandroidv2.database.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class ConverterCalendar {
    @TypeConverter
    public Long toLong(Calendar value){
        if(value != null){
            return value.getTimeInMillis();
        }
        return null;
    }

    @TypeConverter
    public Calendar toCalendar(Long value){
        Calendar thisMoment = Calendar.getInstance();
        if(value != null){
            thisMoment.setTimeInMillis(value);
        }
        return thisMoment;
    }
}
