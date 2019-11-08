package hu.uni.eszterhazy;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class PlayingJava {
    @Test
    public void test() throws IOException {
        File file = new File("target");
        if (!file.exists()) {
            file.createNewFile();
        }
        System.out.println(file.getAbsolutePath());
        List<File> files = Arrays.asList(file.listFiles());
        for (File f : files) {
            System.out.println(f.getName());
        }

        File kiscica = new File("kiscica.txt");
        if (!kiscica.exists()) {
            kiscica.createNewFile();
        }
        FileWriter writer = new FileWriter(kiscica,true);
        writer.write("Szia");
        writer.write("\n");
        writer.write("Mizu");
        writer.close();


//        FileReader reader = new FileReader(kiscica);
//        char[] content = new char[reader.read()];
//        reader.read(content);
//        System.out.println(content);

        Scanner sc = new Scanner(kiscica);
        while (sc.hasNext()){
            System.out.println(sc.next());
        }

        String string = "2019.12.25";
        System.out.println(string.matches("\\d{4}.([1-9]\\d|[1-9]).\\d{2}"));
        String[] result = string.split("\\.",0);
        System.out.println(result);
        String.valueOf(3);
        Integer.parseInt("3");

        Set<String> stringset= new HashSet<>();
        stringset.add("SZIA");

        List<String> stringlist = new ArrayList<>();
        stringlist.indexOf("SZIA");

        Map<String, Double> map = new HashMap<>();
        map.put("Cica", new Double(3.4));
        System.out.println(map.get("Cica"));

        LocalDate date = LocalDate.now();
        System.out.println(date);
        date.isBefore(LocalDate.now().plusYears(2));
        LocalDate datum = LocalDate.parse("2019-05-25");
        LocalDate.of(2017,12,14 );
        System.out.println(datum);
        System.out.println(Nem.No);
    }
}

enum Nem{
    No, Ferfi, Egyeb
}
