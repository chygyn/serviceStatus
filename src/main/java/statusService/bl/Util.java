package statusService.bl;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String getCurDate() {
        System.out.println("Util|getCurDate|start");
        Date dt = new Date();
        SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String sCurDT = ISO8601DATEFORMAT.format(dt);
        System.out.println("Util|getCurDate|sCurDT "+sCurDT);
        return sCurDT;
    }

}
