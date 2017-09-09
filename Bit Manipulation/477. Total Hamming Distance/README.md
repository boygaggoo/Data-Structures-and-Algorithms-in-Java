### Use XOR to find the different bit
```
class Solution {
    public int totalHammingDistance(int[] nums) {
        int count = 0;
        for(int i = 0 ; i < nums.length - 1 ; i++ ){
            for( int j = i + 1 ; j < nums.length ; j++ ){
                count += hammingDistance(nums[i],nums[j]);
            }
        }
        return count;
    }
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x^y);
    }
}
```

### Optimization
* Suppose that i numbers have a rightmost 0-bit
* and j numbers have a 1-bit. 
* Then out of the pairs, i * j of them will have 1 in the rightmost bit of the XOR.

```
class Solution {
    public int totalHammingDistance(int[] nums) {
        int n = 31;
        int len = nums.length;
        int[] count = new int[n];
        
        for( int i = 0 ; i < len ; i++ ){
            for( int j = 0 ; j < n ; j++){
                count[j] += (nums[i] >> j) & 1;
            }
        }
        int res = 0;
        for( int i : count){
            res += i * ( len - i);
        }
        return res;

    }
}
```