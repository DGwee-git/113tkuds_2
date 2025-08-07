//練習 1.1：成績統計系統

public class GradeStatisticsSystem {

    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        // 1. 計算平均、最高、最低
        int max = scores[0];
        int min = scores[0];
        int sum = 0;

        for (int score : scores) {
            sum += score;
            if (score > max) max = score;
            if (score < min) min = score;
        }
        double average = (double) sum / scores.length;

        // 2. 統計等第人數
        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;

        for (int score : scores) {
            if (score >= 90) countA++;
            else if (score >= 80) countB++;
            else if (score >= 70) countC++;
            else if (score >= 60) countD++;
            else countF++;
        }

        // 3. 計算高於平均分的學生人數
        int aboveAverageCount = 0;
        for (int score : scores) {
            if (score > average) aboveAverageCount++;
        }

        // 4. 列印完整成績報表
        System.out.println("===== 成績統計報表 =====");
        System.out.println("成績列表: ");
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println("\n----------------------");
        System.out.printf("平均分: %.2f\n", average);
        System.out.println("最高分: " + max);
        System.out.println("最低分: " + min);
        System.out.println("----------------------");
        System.out.println("A: " + countA + " 人");
        System.out.println("B: " + countB + " 人");
        System.out.println("C: " + countC + " 人");
        System.out.println("D: " + countD + " 人");
        System.out.println("F: " + countF + " 人");
        System.out.println("----------------------");
        System.out.println("高於平均分的學生人數: " + aboveAverageCount + " 人");
        System.out.println("======================");
    }
}
