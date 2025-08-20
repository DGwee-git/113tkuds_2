import java.util.Scanner;

public class M04_TieredTaxSimple {
    
    // 稅率級距定義
    private static final int[] TAX_BRACKETS = {0, 120001, 500001, 1000001};
    private static final double[] TAX_RATES = {0.05, 0.12, 0.20, 0.30};
    private static final int[] BRACKET_LIMITS = {120000, 500000, 1000000, Integer.MAX_VALUE};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("請輸入測試案例數量: ");
        // 讀取測試案例數量
        int n = sc.nextInt();
        
        System.out.println("請輸入 " + n + " 個年度收入:");
        long totalTax = 0; // 總稅額，用於計算平均
        
        // 處理每個收入
        for (int i = 0; i < n; i++) {
            long income = sc.nextLong();
            long tax = calculateTax(income);
            
            System.out.println("Tax: " + tax);
            totalTax += tax;
        }
        
        // 計算並輸出平均稅額（取整數）
        long averageTax = Math.round((double) totalTax / n);
        System.out.println("Average: " + averageTax);
        
        sc.close();
    }
    
    /**
     * 根據級距稅率計算稅額
     * @param income 年度收入
     * @return 應繳稅額
     */
    public static long calculateTax(long income) {
        if (income <= 0) {
            return 0;
        }
        
        long tax = 0;
        long remainingIncome = income;
        
        // 逐段計算稅額
        for (int i = 0; i < TAX_BRACKETS.length; i++) {
            // 確定當前級距的應稅收入
            long bracketIncome;
            
            if (i == 0) {
                // 第一級距：0 到 120000
                bracketIncome = Math.min(remainingIncome, 120000);
            } else if (i == TAX_BRACKETS.length - 1) {
                // 最後一級距：1000001 以上
                bracketIncome = Math.max(0, remainingIncome);
            } else {
                // 中間級距
                long bracketSize = BRACKET_LIMITS[i] - TAX_BRACKETS[i] + 1;
                bracketIncome = Math.min(remainingIncome, bracketSize);
            }
            
            // 計算該級距的稅額
            if (bracketIncome > 0) {
                tax += Math.round(bracketIncome * TAX_RATES[i]);
                remainingIncome -= bracketIncome;
            }
            
            // 如果收入已經分配完畢，結束計算
            if (remainingIncome <= 0) {
                break;
            }
        }
        
        return tax;
    }
    
    /**
     * 替代實作：使用更直觀的分段計算方式
     * @param income 年度收入
     * @return 應繳稅額
     */
    public static long calculateTaxAlternative(long income) {
        if (income <= 0) {
            return 0;
        }
        
        long tax = 0;
        
        // 第一級距：0 - 120,000 (5%)
        if (income <= 120000) {
            tax = Math.round(income * 0.05);
        }
        // 第二級距：120,001 - 500,000 (前120k按5%，超過部分按12%)
        else if (income <= 500000) {
            tax = Math.round(120000 * 0.05 + (income - 120000) * 0.12);
        }
        // 第三級距：500,001 - 1,000,000 (前面按對應稅率，超過部分按20%)
        else if (income <= 1000000) {
            tax = Math.round(120000 * 0.05 + 380000 * 0.12 + (income - 500000) * 0.20);
        }
        // 第四級距：1,000,001 以上 (前面按對應稅率，超過部分按30%)
        else {
            tax = Math.round(120000 * 0.05 + 380000 * 0.12 + 500000 * 0.20 + (income - 1000000) * 0.30);
        }
        
        return tax;
    }
}

/*
 * Time Complexity: O(n * k)
 * 說明：級距稅額計算的時間複雜度分析
 * 1. 對於每個收入案例，需要遍歷 k 個稅率級距 (k=4)，每個級距的計算為 O(1)
 * 2. 總共有 n 個收入案例需要處理
 * 3. 因此總時間複雜度為 O(n * k)，由於 k 為常數，實際上是 O(n)
 * 
 * Space Complexity: O(1)
 * 說明：
 * 1. 只使用了固定大小的陣列來存儲稅率級距資訊
 * 2. 計算過程中只使用了常數個變數
 * 3. 空間複雜度為 O(1)
 * 
 * 演算法特點：
 * - 使用累進稅制：每個級距只對該級距內的收入課稅
 * - 一次遍歷即可完成單個收入的稅額計算
 * - 支援大數值收入計算（使用 long 型別）
 */