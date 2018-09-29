package murphy.christopher.popularmoviesstage1.database.converter;

import android.arch.persistence.room.TypeConverter;
import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Date toDate(long date){
        return new Date(date);
    }

    @TypeConverter
    public static Long toDateLong(Date date){
        if(date != null){
            return date.getTime();
        }
        else{
            return null;
        }
    }
}
