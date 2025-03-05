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

        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            String[] edge = reader.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int[] colors = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            if (colors[i] == 0) {
                if (!isAvailablePaint(graph, colors, i)) {

                    writer.write("NO\n");
                    writer.flush();
                    reader.close();
                    writer.close();
                    return;
                }
            }
        }

        writer.write("YES\n");
        writer.flush();

        reader.close();
        writer.close();
    }

    private static boolean isAvailablePaint(List<List<Integer>> graph, int[] colors, int start) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        colors[start] = 1;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            int currentColor = colors[node];

            for (int neighbor : graph.get(node)) {
                if (colors[neighbor] == 0) {
                    colors[neighbor] = -currentColor;
                    queue.add(neighbor);
                } else if (colors[neighbor] == currentColor) {
                    return false;
                }
            }
        }
        return true;
    }
}
