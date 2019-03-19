import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 格式化字符串
     * */
      public static String getDateFormat(){
          Date date=new Date();
          DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          try{
              String dateString=dateFormat.format(date);
              return dateString;
          }catch (Exception e){
              return e.getMessage();

          }

      }

    public static void main(String[] args) {
        System.out.println(getDateFormat());
        Date date=new Date();
        System.out.println(date.getTime());

    }
}
