## 1.DFS

## 2.DP
* Time : O(n^2)

由于这个最长上升序列不一定是连续的，对于每一个新加入的数，都有可能跟前面的序列构成一个较长的上升序列，或者跟后面的序列构成一个较长的上升序列。比如1,3,5,2,8,4,6，对于6来说，可以构成1,3,5,6，也可以构成2,4,6。因为前面那个序列长为4，后面的长为3，所以我们更愿意6组成那个长为4的序列，所以对于6来说，它组成序列的长度，实际上是之前最长一个升序序列长度加1，注意这个最长的序列的末尾是要小于6的，不然我们就把1,3,5,8,6这样的序列给算进来了。这样，我们的递推关系就隐约出来了，假设dp[i]代表加入第i个数能构成的最长升序序列长度，我们就是要在dp[0]到dp[i-1]中找到一个最长的升序序列长度，又保证序列尾值nums[j]小于nums[i]，然后把这个长度加上1就行了。同时，我们还要及时更新最大长度。

```java
public class Solution {
    public int lengthOfLIS(int[] input) {
        if (input.length == 0) return 0;
        int len = input.length, max = 0;
        // dp[i] means the longest length til ith position
        int[] dp = new int[len];
        
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (input[i] > input[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

```


## 3.Binary Search + DP
* Time : O(nlogn) + Space : O(n)

在1,3,5,2,8,4,6这个例子中，当到6时，我们一共可以有四种

1. 不同长度
2. 且保证该升序序列在同长度升序序列中末尾最小的升序序列

```
1
1,2
1,3,4
1,3,5,6
```
这些序列都是未来有可能成为最长序列的候选人。这样，每来一个新的数，我们便按照以下规则更新这些序列

* 如果nums[i]比所有序列的末尾都大，或等于最大末尾，说明有一个新的不同长度序列产生，我们把最长的序列复制一个，并加上这个nums[i]。
* 如果nums[i]比所有序列的末尾都小，说明长度为1的序列可以更新了，更新为这个更小的末尾。
* 如果在中间，则更新那个末尾数字刚刚大于等于自己的那个序列，说明那个长度的序列可以更新了。

如果再来一个0，那就是第一种情况，更新序列为

```
0
1,2
1,3,3
1,3,5,6

```
如果再来一个3，那就是第二种情况，更新序列为

```
1
1,2
1,3,3
1,3,5,6
```

```
比如这时，如果再来一个9，那就是第三种情况，更新序列为

```
1
1,2
1,3,4
1,3,5,6
1,3,5,6,9


前两种都很好处理，O(1)就能解决，主要是第三种情况，实际上我们观察直到6之前这四个不同长度的升序序列，他们末尾是递增的，所以可以用二分搜索来找到适合的更新位置。

二分搜索时如果在tails数组中，找到我们要插入的数，也直接返回那个结尾的下标，虽然这时候更新这个结尾没有意义，但少了些判断简化了逻辑

```java
public class Solution {
    public int lengthOfLIS(int[] input) {
        if(input == null || input.length == 0 ) return 0;
        int[] dp = new int[input.length];
        dp[0] = input[0];
        int index = 0;
        for( int i = 1; i < input.length; i++){
            int pos = binarySearch(dp,index,input[i]);
            if( input[i] < dp[pos]) dp[pos] = input[i];
            if( pos > index){
                index = pos;
                dp[index] = input[i];
            }
        }
        return index+1;
    }
    public int binarySearch(int[] arr, int len, int target){
        int start = 0;
        int end = len;
        while( start + 1 <  end ){
            int mid = ( start + end ) >>> 1;
            if(arr[mid] == target) return mid;
            else{
                if(arr[mid] < target) start = mid;
                else end = mid;
            }
        }
        if( arr[end] < target) return len+1;
        else if (arr[start] >= target) return start;
        else return end;
    }
}
```