class Solution {
    public int maximumSwap(int num) {
        int[] nums = Integer.toString(num).chars().map(c -> c-='0').toArray();
        
        int[] bucket = new int[10];
        for( int i = 0 ; i <  nums.length ; i++ ){
            bucket[nums[i]] = i;
        }
        for( int i = 0 ; i < nums.length ; i++ ){
            for( int j = 9 ; j > nums[i] ; j-- ){
                if( bucket[j] > i ){
                    swap(nums,i,bucket[j]);
                    
                    int res = convertBackToIntArray(nums);
                    return res;   
                }
            }
        }
        return num;
    }
    public void swap(int[] nums, int i , int j ){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private int convertBackToIntArray(int[] nums){
        int res = 0;
        for(int i : nums){
            res = res * 10 + i;
        }
        return res;
    }
}