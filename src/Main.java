import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        //写入文件
          String fileName="sdf";
        File file = new File("d:" + File.separator + "demo" + File.separator + fileName);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write("======================total_Begin Time:"+DateUtil.getDateFormat()+"============");
            fileWriter.write("\r\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
        //写入数据总量


        inputTxt(fileWriter);


        try {
            fileWriter.write("======================End Time:"+DateUtil.getDateFormat()+"============");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileUtil.byteOutStream(fileWriter, JSON.serialize(param).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void inputTxt(FileWriter fileWriter){

    }
}
