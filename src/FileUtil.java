import java.io.*;

public class FileUtil {
    /**
     * 文件输出
     * @param content 输出内容
     * @throws Exception
     * */
    public static void byteOutStream(FileWriter fileWriter,String content)throws Exception{
//        String temp=content;
//        System.out.println(content);
//        Map<String,Object>param=(Map<String,Object>)JSON.parse(temp);
//        String fileName=param.get("PlatformName").toString()+"_"+StringUtil.strSplit(param.get("ResourceName").toString());
//        File file=new File("d:"+File.separator+"demo"+File.separator+fileName);


//        if(!file.getParentFile().exists()){//如果文件不存在
//            file.getParentFile().mkdirs();
//        }

        fileWriter.write(content+"\r\n");
        fileWriter.flush();




    }

    public static void main(String[] args)throws Exception {

//        for (int i = 0; i < 100000; i++) {
//            test("qwqwr");
//            System.out.println("12");
//        }
//        byteOutStream("11");

    }

    public static void test(String content)throws Exception{
        File file=new File("d:"+File.separator+"demo"+File.separator+"3.txt");

        if(!file.getParentFile().exists()){//如果文件不存在
            file.getParentFile().mkdirs();
        }
        FileWriter fileWriter=new FileWriter(file);
        fileWriter.write(content);
        fileWriter.close();
    }


    /**
     * 删除文件，如果是文件夹，则全部删除
     *
     * @param filePath
     *            文件路径或文件夹路径
     * @return
     */
    public static boolean deleteFile(String filePath) {
        File f = new File(filePath);
        if (f.isFile()) {
            return f.delete();
        }
        if (f.isDirectory()) {
            for (File file : f.listFiles()) {
                file.delete();
            }
        }
        return true;

    }

    public static boolean move(String sorucePath, String topath, String toFileName) {
        File sourceFile = new File(sorucePath);
        if (!sourceFile.exists()) {
            // LOGGER.info("move file error,source path not exists:" + sorucePath);
            return false;
        }
        createDir(topath);
		/*
		 * if(!topath.endsWith("/")){ topath+="/"; }
		 */
        return sourceFile.renameTo(new File(topath + toFileName));
    }

    /**
     * 读取文件为byte数组
     *
     * @param filePath
     *            文件路径
     * @return
     */
    public static byte[] getBytes(String filePath) {
        // LOGGER.info("filepath=" + filePath);
        File file = new File(filePath);
        byte[] buffer = new byte[0];
        if (file.isFile() && !file.exists()) {
            return buffer;
        }

        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static byte[] getBytes(File file) {
        //LOGGER.info("filepath=" + file.getPath());
        byte[] buffer = new byte[0];
        if (file.isFile() && !file.exists()) {
            return buffer;
        }

        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static byte[] getBytes(InputStream is) {
        byte[] buffer = new byte[0];
        try {

            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = is.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            is.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 文件是否存在
     *
     * @param path
     *            文件路径
     * @return
     */
    public static boolean IsExistsFile(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * 保存文件
     *
     * @param bfile
     *            文件二进制数组
     * @param filePath
     *            待保存的路径
     * @param fileName
     *            文件名称
     * @return
     */
    public static boolean SaveFile(byte[] bfile, String filePath, String fileName) {
        // LOGGER.info("filepath=" + filePath + ",filename=" + fileName);
        if (filePath == null || filePath.length() == 0) {
            return false;
        }
        if (!filePath.endsWith("/") && !filePath.endsWith("\\")) {
            filePath = filePath + "/";
        }
        File file = new File(filePath + fileName);
        try {
            file.createNewFile();
            OutputStream os = new FileOutputStream(file);
            os.write(bfile);
            os.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 创建文件夹，如果己存在返回true
     *
     * @param destDirName
     *            文件夹路径
     * @return
     */
    public static boolean createDir(String destDirName) {
        //  LOGGER.info("createDir:" + destDirName);
        try {
            File dir = new File(destDirName);
            if (dir.exists()) {
                return true;
            }
            if (!destDirName.endsWith(File.separator)) {
                destDirName = destDirName + File.separator;
            }
            // 创建目录
            if (dir.mkdirs()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public static void startTotalThread(String fileName){
        //3.1每小时上报时，同时对资源位置、资源类型、资源名称相同的记录（仅业务系统不同）进行累积，以上报业务系统为Total的总量数据。


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


        //getTotal(fileWriter);


        try {
            fileWriter.write("======================End Time:"+DateUtil.getDateFormat()+"============");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
