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
        var grid = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] row = reader.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(row[j]);
            }
        }

        int[][] dp = new int[N][M];
        char[][] direction = new char[N][M];

        dp[0][0] = grid[0][0];
        for (int j = 1; j < M; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
            direction[0][j] = 'R';
        }

        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
            direction[i][0] = 'D';
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                    direction[i][j] = 'D';
                } else {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                    direction[i][j] = 'R';
                }
            }
        }

        var path = new StringBuilder();

        int maxSum = dp[N-1][M-1];
        int i = N - 1, j = M - 1;

        while (i > 0 || j > 0) {
            if (direction[i][j] == 'D') {
                path.append('D');
                i--;
            } else {
                path.append('R');
                j--;
            }
        }

        path.reverse();

        writer.write(maxSum + "\n");
        writer.write(path + "\n");
        writer.flush();

        reader.close();
        writer.close();
    }
}
