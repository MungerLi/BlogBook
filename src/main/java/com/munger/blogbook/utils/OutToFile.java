package com.munger.blogbook.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@Component
public class OutToFile {
    public static void toFile(String title, String article){
        String title1=title.substring( 0,10 );
        File file= new File( "D://00//"+title1+".html" );
                //如果文件的目录不存在
        if(!file.getParentFile().exists()){
            //创建目录
            file.getParentFile().mkdirs();
        }
        Writer writer = null;
        try {
            writer=new FileWriter(file);
            writer.write( article );
            // todo 导致写入文件失败
            writer.flush();//刷新内存，将内存中的数据立刻写出。
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //        Map<String,String > map=new HashMap<>( 16 ) ;
//        map.put( title,article );

}


