import java.util.Scanner;

public class M05_GCD_LCM_Recursive {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 讀取兩個正整數
        long a = sc.nextLong();
        long b = sc.nextLong();
        
        // 計算 GCD
        long gcd = gcd(a, b);
        
        // 計算 LCM，使用先除後乘避免溢位
        long lcm = (a / gcd) * b;
        
        // 輸出結果
        System.out.println("GCD: " + gcd);
        System.out.println("LCM: " + lcm);
        
        sc.close();
    }
    
    /**
     * 遞迴實作歐幾里得演算法計算最大公約數 (GCD)
     * 使用遞迴公式：gcd(a, b) = gcd(b, a % b)
     * 終止條件：gcd(a, 0) = a
     * 
     * @param a 第一個正整數
     * @param b 第二個正整數
     * @return 兩數的最大公約數
     */
    public static long gcd(long a, long b) {
        // 終止條件：當 b 為 0 時，GCD 就是 a
        if (b == 0) {
            return a;
        }
        
        // 遞迴呼叫：gcd(a, b) = gcd(b, a % b)
        return gcd(b, a % b);
    }
    
    /**
     * 迭代版本的 GCD（供參考，非主要使用）
     * @param a 第一個正整數
     * @param b 第二個正整數
     * @return 兩數的最大公約數
     */
    public static long gcdIterative(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    /**
     * 計算最小公倍數 (LCM)
     * 使用公式：LCM(a, b) = (a * b) / GCD(a, b)
     * 為避免溢位，改寫為：LCM(a, b) = (a / GCD(a, b)) * b
     * 
     * @param a 第一個正整數
     * @param b 第二個正整數
     * @return 兩數的最小公倍數
     */
    public static long lcm(long a, long b) {
        long gcd = gcd(a, b);
        
        // 先除後乘，避免中間結果溢位
        // LCM = (a / GCD) * b = a * (b / GCD)
        // 選擇較大的數先除以 GCD，可以進一步減少溢位風險
        if (a >= b) {
            return (a / gcd) * b;
        } else {
            return a * (b / gcd);
        }
    }
    
    /**
     * 驗證函式：檢查計算結果的正確性
     * 驗證性質：GCD(a, b) * LCM(a, b) = a * b
     */
    public static void verify(long a, long b, long gcd, long lcm) {
        // 由於可能的大數乘法，這裡只做概念性驗證
        System.out.println("驗證：GCD * LCM = " + gcd + " * " + lcm + " = " + (gcd * lcm));
        System.out.println("原數乘積：a * b = " + a + " * " + b + " = " + (a * b));
        System.out.println("驗證結果：" + (gcd * lcm == a * b ? "正確" : "錯誤"));
    }
}

/*
 * Time Complexity: O(log(min(a, b)))
 * 說明：遞迴歐幾里得演算法的時間複雜度分析
 * 1. 每次遞迴呼叫中，較大的數變成較小的數，較小的數變成餘數
 * 2. 根據拉梅定理，遞迴深度最多為 O(log(min(a, b)))
 * 3. 每層遞迴只進行常數時間的運算（取餘數），因此總時間複雜度為 O(log(min(a, b)))
 * 
 * Space Complexity: O(log(min(a, b)))
 * 說明：遞迴呼叫堆疊空間分析
 * 1. 遞迴深度為 O(log(min(a, b)))
 * 2. 每層遞迴使用常數空間
 * 3. 總空間複雜度為 O(log(min(a, b)))
 * 
 * 溢位處理策略：
 * - LCM 計算使用 (a / GCD) * b 而非 (a * b) / GCD
 * - 確保中間結果不會超過 long 的範圍
 * - 當 a, b ≤ 10^9 且 GCD ≥ 1 時，(a / GCD) * b ≤ 10^18，在 long 範圍內
 * 
 * 演算法正確性：
 * - 基於歐幾里得演算法：GCD(a, b) = GCD(b, a mod b)
 * - LCM 與 GCD 關係：LCM(a, b) * GCD(a, b) = a * b
 */