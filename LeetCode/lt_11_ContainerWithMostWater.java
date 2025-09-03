public class lt_11_ContainerWithMostWater {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int max = 0;

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int width = right - left;
            max = Math.max(max, h * width);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        lt_11_ContainerWithMostWater solution = new lt_11_ContainerWithMostWater();
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println("最大盛水量: " + solution.maxArea(height)); // 預期 49
    }
}

/*
解題思路：
- 使用雙指針，一開始分別在最左和最右。
- 每次計算面積 = min(高度) * 寬度。
- 為了找到更大的容器，移動較矮的一邊。
- 時間複雜度 O(n)。
*/
