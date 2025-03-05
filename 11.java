package me.smokesh;

import java.io.*;
import java.util.*;

public class Main {

    /*
	Для чтения входных данных необходимо получить их
	из стандартного потока ввода (System.in).
	Данные во входном потоке соответствуют описанному
	в условии формату. Обычно входные данные состоят
	из нескольких строк. Можно использовать более производительные
	и удобные классы BufferedReader, BufferedWriter, Scanner, PrintWriter.

	С помощью BufferedReader можно прочитать из стандартного потока:
	* строку -- reader.readLine()
	* число -- int n = Integer.parseInt(reader.readLine());
	* массив чисел известной длины (во входном потоке каждое число на новой строке) --
	int[] nums = new int[len];
    for (int i = 0; i < len; i++) {
        nums[i] = Integer.parseInt(reader.readLine());
    }
	* последовательность слов в строке --
	String[] parts = reader.readLine().split(" ");

	Чтобы вывести результат в стандартный поток вывода (System.out),
	Через BufferedWriter можно использовать методы
	writer.write("Строка"), writer.write('A') и writer.newLine().

	Возможное решение задачи "Вычислите сумму чисел в строке":
	int sum = 0;
    String[] parts = reader.readLine().split(" ");
    for (int i = 0; i < parts.length; i++) {
        int num = Integer.parseInt(parts[i]);
        sum += num;
    }
    writer.write(String.valueOf(sum));
	*/

    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        Set<String> allLanguages = new HashSet<>();
        Set<String> commonLanguages = null;


        for (int i = 0; i < n; i++) {
            int m = Integer.parseInt(reader.readLine());
            Set<String> studentLanguages = new HashSet<>();
            for (int j = 0; j < m; j++) {
                String language = reader.readLine();
                studentLanguages.add(language);
                allLanguages.add(language);
            }
            if (commonLanguages == null) {
                commonLanguages = new HashSet<>(studentLanguages);
            } else {
                commonLanguages.retainAll(studentLanguages);
            }
        }

        if (commonLanguages == null) return;

        writer.write(commonLanguages.size() + "\n");
        for (String lang : commonLanguages) {
            writer.write(lang + "\n");
        }

        writer.write(allLanguages.size() + "\n");
        for (String lang : allLanguages) {
            writer.write(lang + "\n");
        }

        reader.close();
        writer.close();
    }

}
