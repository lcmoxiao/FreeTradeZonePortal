import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class TimeTest {

    public static void main(String[] args) {
        StringBuffer s ;
        StringBuilder sb;
        String ss;
        HashMap hm;
        System.out.println(Time());

    }


    private static Date Time() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        return c.getTime();
    }

}
