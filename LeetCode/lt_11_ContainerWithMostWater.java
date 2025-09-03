public class lt_11_ContainerWithMostWater {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int width = right - left;
            maxArea = Math.max(maxArea, h * width);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    // 測試程式
    public static void main(String[] args) {
        lt_11_ContainerWithMostWater solution = new lt_11_ContainerWithMostWater();

        int[] height1 = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        System.out.println("Example 1:");
        System.out.println("Input: [1,8,6,2,5,4,8,3,7]");
        System.out.println("Output: " + solution.maxArea(height1)); // 49
        System.out.println();

        int[] height2 = { 1, 1 };
        System.out.println("Example 2:");
        System.out.println("Input: [1,1]");
        System.out.println("Output: " + solution.maxArea(height2)); // 1
        System.out.println();

        int[] height3 = { 4, 3, 2, 1, 4 };
        System.out.println("Extra Example:");
        System.out.println("Input: [4,3,2,1,4]");
        System.out.println("Output: " + solution.maxArea(height3)); // 16
    }
}
