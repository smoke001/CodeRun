package me.smokesh;

import java.io.*;

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

        var dims = reader.readLine().split(" ");
        var N = Integer.parseInt(dims[0]);
        var M = Integer.parseInt(dims[1]);
        int[][] dp = new int[N][M];

        dp[0][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i - 2 >= 0 && j - 1 >= 0) {
                    dp[i][j] += dp[i - 2][j - 1];
                }
                if (i - 1 >= 0 && j - 2 >= 0) {
                    dp[i][j] += dp[i - 1][j - 2];
                }
            }
        }

        System.out.println(dp[N - 1][M - 1]);

        reader.close();
        writer.close();
    }
}
