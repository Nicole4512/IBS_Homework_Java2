package IBS_DZ2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class File_use {
    static public String removePunctuations(String s) {
        String result = "";
        for (Character c : s.toCharArray()) {

            if(Character.isLetterOrDigit(c))
                result += c;
            else
                result += ' ';
        }
        return result;
    }
//         ../../Favorites/textFile.txt

    public static void main(String[] args) {
        BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
        String fileName = null;
        System.out.println("Пожалуйста, введите путь к файлу");


        try {
            fileName = reader.readLine();
        } catch (IOException e) {
            System.out.println("Введен некорректный пусть");
        }
        Path paths = Path.of(fileName);
        paths = paths.toAbsolutePath();
        URI pat = paths.toUri();


        String content = null;


        try {
            content = Files.lines(Paths.get(pat)).reduce("", String::concat);
        } catch (IOException e) {
            System.out.println("Неверный путь к файлу");
            e.getMessage();

        }

       //  Разделение на слова
        try {
            content = removePunctuations(content);
            content = content.toLowerCase();
        }
        catch (NullPointerException e){
            content = " ";
            System.out.println("Пустая переменная content, теперь она равна пробелу");
            e.getMessage();
        }
        String[] word = content.split(" ");
        List<String> words = new ArrayList<String>();
        for (String w : word) {
            if (!w.equals("")){
                words.add(w);
            }
        }

            // Сортировка по алфавиту и вывод на экран
        Collections.sort(words);
        System.out.println("Сортировка по алфавиту: ");
        for (String w : words) {
            System.out.println(w);
        }


            //Считаем, сколько раз встречается слово в файле и выводим статисику на экран
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String w : words) {
            if (!map.containsKey(w)) {
                map.put(w, Integer.valueOf(1));
            } else {
                map.put(w, map.get(w) + Integer.valueOf(1));
            }

        }



        System.out.println("_______________________________________________________________________");
        System.out.println("Вывод статистики: ");
        for (String w : map.keySet()){
            String key =  w;
            Integer value = map.get(w);
            System.out.println(key + " " + value);
        }

        //Находим слово, встречающееся max количество раз и выводим его частоту на экран
        Integer max = Integer.valueOf(0);
        float sum = 0f;
        for (String w : map.keySet()){
            if (map.get(w) > max){
                max = map.get(w);
            }
            sum += map.get(w);
        }
        System.out.println("_______________________________________________________________________");
        System.out.println("Слово(слова) встечающееся максимальное число раз в файле и его частота: ");
        for (String w : map.keySet()){
            if (map.get(w) == max){
                float ch = map.get(w)/ sum * 100f;
                System.out.println(w + " " + ch + "%") ;
            }
        }




    }
}
