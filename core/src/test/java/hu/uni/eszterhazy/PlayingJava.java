package hu.uni.eszterhazy;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PlayingJava {
    @Test
    public void test() throws IOException {
      File file = new File("target");
      if(!file.exists()){
          file.createNewFile();
      }
        System.out.println(file.getAbsolutePath());
        List<File> files = Arrays.asList(file.listFiles());
        for(File f: files){
            System.out.println(f.getName());
        }

    }
}
