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

        var input = reader.readLine().split(" ");
        var n = Integer.parseInt(input[0]);
        var k = Integer.parseInt(input[1]);

        var seqStr = reader.readLine().split(" ");
        var sequence = new int[n];

        for (int i = 0; i < n; i++) sequence[i] = Integer.parseInt(seqStr[i]);

        Deque<Integer> deque = new LinkedList<>();
        var result = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) deque.pollFirst();
            while (!deque.isEmpty() && sequence[deque.peekLast()] >= sequence[i]) deque.pollLast();

            deque.offerLast(i);

            if (deque.peekFirst() == null) return;
            if (i >= k - 1) {
                result.append(sequence[deque.peekFirst()]).append("\n");
            }
        }

        System.out.print(result);

        reader.close();
        writer.close();
    }
}
