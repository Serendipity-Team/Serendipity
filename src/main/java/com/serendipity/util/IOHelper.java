package com.serendipity.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * NIO 操作文件
 */
public class IOHelper {

    /**
     * 读取文件内容
     * @param filePath
     * @return
     */
    public static String readFile(String filePath){
        return readFile(filePath,StandardCharsets.UTF_8);
    }
    /**
     * 读取文件内容
     * @param filePath
     * @param charset StandardCharsets.UTF_8
     * @return
     */
    public static String readFile(String filePath, Charset charset){
        StringBuffer sb = new StringBuffer();
        List<String> list = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(filePath),charset)) {
            list = stream.collect(Collectors.toList());
        }catch(IOException e){
            e.printStackTrace();
        }
        for(String str : list){
            sb.append(str).append("\n");
        }
        return  sb.toString();
    }

    /**
     * 写入文件内容
     * @param filePath
     * @param context
     * @return
     */
    public boolean writeFile(String filePath,String context){
        return writeFile(filePath,context,StandardCharsets.UTF_8);
    }
    /**
     * 写入文件内容
     * @param filePath
     * @param context
     * @param charset
     * @return
     */
    public boolean writeFile(String filePath,String context,Charset charset){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(filePath));
            FileChannel channel = fos.getChannel();
            ByteBuffer src = charset.encode(context);

            int length = 0;
            while ((length = channel.write(src)) != 0) {
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * copy nio 文件内容
     * @param fromPath
     * @param toPath
     * @return
     */
    public boolean copyFile(String fromPath, String toPath){
        File from = new File(fromPath);
        File to = new File(toPath);
        FileInputStream fin = null;
        FileOutputStream fout = null;
        FileChannel fic = null;
        FileChannel foc = null;
        try {
            fin = new FileInputStream(from);
            fout = new FileOutputStream(to);
            fic = fin.getChannel();
            foc = fout.getChannel();
            ByteBuffer bb = ByteBuffer.allocate(1024 << 4);
            while (fic.read(bb) > 0) {
                bb.flip();
                foc.write(bb);
                bb.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally{
            try {
                if (null != fic)
                    fic.close();
                if (null != foc)
                    foc.close();
                if (null != fin)
                    fin.close();
                if (null != fout)
                    fout.close();
            }catch(IOException e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
