
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        readFile();

    }

    /**
     * 读入TXT文件
     * 按功能需求指定用户需要的功能
     */
    public static void readFile() {
        int count1=1,count2=2,count3=3;
        Scanner scanner=new Scanner(System.in);

        String pathname = "word.txt";
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            //指定单词词频统计功能
            System.out.println("===============功能选择===============");
            System.out.println("1.指定单词词频统计功能\n2.高频词统计功能\n3.统计文本所有单词数量");
            System.out.print("请输入你的选择：");
            int count=scanner.nextInt();
            String strArray[]=null;
            int arrCount[]=null;
            Map<String,Integer> map=new HashMap<>();
            int k=0;

            if(count==1){
                System.out.println("请输入单词：");
                String str=scanner.next();
                //字符串分割为单词，单词之间用空格分割
                strArray=str.split(" ");
                arrCount=new int[strArray.length];//统计每个单词的数量
            }else if(count==2){
                System.out.println("请输入高频词的个数k:");
                k=scanner.nextInt();

            }



            while ((line = br.readLine()) != null) {

                switch (count){
                    case 1:String strSource[]=line.split(" ");
                        for (int i = 0; i < strArray.length; i++) {
                            for (int j = 0; j < strSource.length; j++) {
                                if(strArray[i].equals(strSource[j])){
                                    arrCount[i]++;
                                }

                            }

                        }
                    default:String strAll[]=line.split(" ");
                        for (int i = 0; i <strAll.length ; i++) {
                            if(map.size()==0){

                              map.put(strAll[i],1);
                                continue;
                            }
                            int size=map.size();

                            boolean flag=false;
                            for (Map.Entry<String, Integer> entry : map.entrySet()) {

                                if(entry.getKey().equals(strAll[i])){
                                    int temp=entry.getValue();

                                    entry.setValue(++temp);
                                    flag=true;
                                }
                            }
                            if(flag==false){

                                map.put(strAll[i],1);
                            }
                        }

                        break;
                }
            }
            //输出
            if(count==1){
                for (int i = 0; i <strArray.length ; i++) {

                    System.out.print(strArray[i]+"-----数量："+arrCount[i]+" ");
                    //柱形图
                }
            }else if(count==2){

                Map<String,Integer>mapResult=MapSort.sortMapByValue(map);
                for (Map.Entry<String, Integer> entry : mapResult.entrySet()) {
                    if(k==0){
                        break;
                    }else {
                        System.out.println("单词= " + entry.getKey() + "  数量= " + entry.getValue());
                        k--;
                    }
                }

            }else if(count==3){
               Map<String,Integer>mapRe= MapSort.sortMapByValue(map);
                writeFile(mapRe);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 写入TXT文件
     */
    public static void writeFile(Map<String,Integer> map) {
        try {
            File writeName = new File("output.txt");
            writeName.createNewFile();
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                for (Map.Entry<String, Integer> entry : map.entrySet()) {

                    out.write(entry.getKey()+"数量："+entry.getValue()+"\r\n");

                }
                out.flush();



            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


