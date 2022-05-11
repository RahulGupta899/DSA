import java.util.Arrays;

public class DynamicProg{
    
    // Display 1D DP
    public static void display1D(int[] dp){
        for(int val: dp) System.out.print(val+" ");
        System.out.println();
    }

    // Display 2D Dp
    public static void display2D(int dp[][]){
        for(int[] dp1D: dp) display1D(dp1D);
    }

    /* Steps to solve Dp Problem
    -------------------------------
        1. Faith
        2. Tree Diagram
        3. Recursion
        4. Memoization solution
        5. Observe the dependency 
        6. Tabulization solution
        7. Optimize the space 

        Time complexity through Tabulization
        Both Recursion and Tabulization have same complexity
    */

    // category1: Two Pointer
    //---------------------------------------------------
    // Problem 1: Fibonacci Sequence
    //---------------------------------------------------

    public static int fibo_memo(int n, int dp[]){
        if(n<=1){
            return dp[n] = n;
        }
        if(dp[n]!=0) return dp[n];

        int nm1 = fibo_memo(n-1,dp);
        int nm2 = fibo_memo(n-2,dp);
        return dp[n] = nm1+nm2;
    }

    public static int fibo_tabu(int N, int dp[]){
        for(int n=0; n<=N; n++){
            if(n<=1){
                dp[n] = n;
                continue;
            }
            dp[n] = dp[n-1]+dp[n-2];
        }
        return dp[N];        
    }

    public static int fibo_opti(int N){
        int a=0,b=1;
        for(int n=0; n<N; n++){
            int sum = a+b;
            a=b;
            b=sum;
        }
        return a;        
    }

    //---------------------------------------------------
    // Problem 2: Tribonacci Series          #LC1137
    //---------------------------------------------------
    
    public int tribonacci(int n) {
        int dp[] = new int[n+1];
        int ans = tribo_memo(n, dp);
        return ans;
    }
    
    public int tribo_memo(int n, int dp[]){
        if(n<=2){
            return dp[n] = (n==0)?0:1;
        }
        
        if(dp[n]!=0) return dp[n];
        
        int nm1 = tribo_memo(n-1,dp);
        int nm2 = tribo_memo(n-2,dp);
        int nm3 = tribo_memo(n-3,dp);
        return dp[n] = nm1+nm2+nm3;
    }

    public int tribo_tabu(int N, int dp[]){
        for(int n=0; n<=N; n++){
            if(n<=2){
                dp[n] = (n==0)?0:1;
                continue;
            }
            dp[n] = dp[n-1]+dp[n-2]+dp[n-3];
        }
        return dp[N];    
    }

    public int tribo_opti(int N){
        int a=0, b=1, c=1;
        for(int n=0; n<N; n++){
            int sum = a+b+c;
            a=b; b=c; c = sum;
        }
        return a;
    }
    
    //---------------------------------------------------
    // Problem 3: Climbing Stairs           #LC70
    //---------------------------------------------------

    public static int climbStairs_memo(int n, int dp[]){
        if(n<=1){
            return dp[n] = 1;
        }
        if(dp[n]!=0) return dp[n];

        int nm1 = climbStairs_memo(n-1,dp);
        int nm2 = climbStairs_memo(n-2,dp);
        return dp[n] = nm1+nm2;
    }

    public static int climbStairs_tabu(int N, int dp[]){
        for(int n=0; n<=N; n++){
            if(n<=1){
                dp[n] = 1;
                continue;
            }
            dp[n] = dp[n-1]+dp[n-2];
        }
        return dp[N];        
    }

    public static int climbStairs_opti(int N){
        int a=1,b=1;
        for(int n=0; n<N; n++){
            int sum = a+b;
            a=b;
            b=sum;
        }
        return a;        
    }

    //---------------------------------------------------
    // Problem 4: Min climbing Stairs cost  #LC746
    //---------------------------------------------------

    public int minCostClimbingStairs(int[] cost) {
        int dp[] = new int[cost.length+1];
        int ans = minCostClimbStairs_tabu(cost.length, cost,dp);
        return ans;
    }
    
    public int minCostClimbStairs_memo(int n, int cost[], int dp[]){
        
        if(n<=1){
            return dp[n] = cost[n];
        }
        if(dp[n]!=0) return dp[n];
        int nm1C = minCostClimbStairs_memo(n-1,cost,dp);
        int nm2C = minCostClimbStairs_memo(n-2,cost,dp);
        
        int ans = Math.min(nm1C,nm2C) + (n==cost.length ?0:cost[n]) ;
        
        return dp[n] = ans;
    }
    
    public int minCostClimbStairs_tabu(int N, int cost[], int dp[]){
        for(int n=0; n<=N; n++){
            if(n<=1){
                dp[n] = cost[n];
                continue;
            }
            int nm1C = dp[n-1];
            int nm2C = dp[n-2];
            int ans = Math.min(nm1C,nm2C) + (n==cost.length ?0:cost[n]) ;
            dp[n] = ans;
        }
        return dp[N];
    }
    
    //---------------------------------------------------
    // Problem 5: Friends Pairing           #gfg
    //---------------------------------------------------

    public long countFriendsPairings(int n) {  
       long dp[] = new long[n+1];
       long mod = (long) 1e9+7;
       long ans = countFriendsPairings_opti(n,mod);
       return ans;
    }
    
    public long countFriendsPairings_memo(int n, long dp[], long mod){
         if (n <= 1) {
            return dp[n] = 1;
        }

        if (dp[n] != 0)
            return dp[n];

        long single = countFriendsPairings_memo(n - 1, dp,mod);
        long pairUp = countFriendsPairings_memo(n - 2, dp,mod) * (n - 1);
        
        return dp[n] = ((single%mod) + (pairUp%mod)) % mod;
        
        // return dp[n] = single + pairUp; // fine but due to addition number can become -v
                                        // -ve so after addition needed to mode once again
    }
    
    public long countFriendsPairings_tabu(int N, long dp[], long mod){
        
        for(int n=0;  n<=N; n++){
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }
            dp[n] = (dp[n-1]%mod + (dp[n-2] * (n-1))%mod) % mod ;      
        }
        return dp[N];
    }
    
    public long countFriendsPairings_opti(int N, long mod){
        long a = 1, b = 1;
        for(int n=0;  n<N; n++){
            long sum = (a*(n-1+2)+b)%mod;  // imp
            a = b; 
            b = sum;
        } 
        return a;
    }

    //---------------------------------------------------
    // Problem 7: Maze Path          
    //---------------------------------------------------

    public static int mazePath(int m, int n){
        int dr=m-1,dc=n-1;
        int dirArr[][] = {{0,-1},{-1,-1},{-1,0}}; // h v d upward directions
        int dp[][] = new int[dr+1][dc+1];
        for(int row[] : dp) Arrays.fill(row,-1);
        
        int ans =  mazePath_Tabu(dr,dc,dirArr,dp);
        display2D(dp);
        return ans;
    }

    public static int mazePath_memo(int dr, int dc, int dirArr[][], int dp[][]){
        if(dr==0 && dc==0){
            return dp[dr][dc] =  1;
        }
        if(dp[dr][dc]!=-1) return dp[dr][dc];
        int count = 0;
        for(int d=0; d<dirArr.length; d++){
            int r = dr+dirArr[d][0];
            int c = dc+dirArr[d][1];

            if(r>=0 && c>=0 && r<=dr && c<=dc){
                count+= mazePath_memo(r,c,dirArr,dp);
            }
        }
        dp[dr][dc] = count;
        return count;
    }

    public static int mazePath_Tabu(int DR, int DC, int dirArr[][], int dp[][]){

        for(int dr=0; dr<=DR; dr++){
            for(int dc=0; dc<=DC; dc++){
                if(dr==0 && dc==0){
                    dp[dr][dc] =  1;
                    continue;
                }

                int count = 0;
                for(int d=0; d<dirArr.length; d++){
                    int r = dr+dirArr[d][0];
                    int c = dc+dirArr[d][1];

                    if(r>=0 && c>=0 && r<=dr && c<=dc){
                        count+= dp[r][c];
                    }
                }
                dp[dr][dc] = count;
            }
        }

        return dp[DR][DC];      
    }
    

    //---------------------------------------------------
    // Problem 8: Maze Path with jumps         
    //---------------------------------------------------

    public static int mazePathJump(int m, int n){
        int dr=m-1,dc=n-1;
        int dirArr[][] = {{0,-1},{-1,-1},{-1,0}}; // h v d upward directions
        int dp[][] = new int[dr+1][dc+1];
        for(int row[] : dp) Arrays.fill(row,-1);
        
        int ans =  mazePath_memo(dr,dc,dirArr,dp);
        display2D(dp);
        return ans;
    }

    public static int mazePathJump_memo(int dr, int dc, int dirArr[][], int dp[][]){
        if(dr==0 && dc==0){
            return dp[dr][dc] =  1;
        }
        if(dp[dr][dc]!=-1) return dp[dr][dc];
        int count = 0;
        for(int d=0; d<dirArr.length; d++){
            int r = dr+dirArr[d][0];
            int c = dc+dirArr[d][1];
            while(r>=0 && c>=0 && r<=dr && c<=dc){
                count+= mazePath_memo(r,c,dirArr,dp);
                r = dr+dirArr[d][0]; // radius 1-1 karke grow hote rahega
                c = dc+dirArr[d][1];
            }
        }
        dp[dr][dc] = count;
        return count;
    }

    public static int mazePathJump_Tabu(int DR, int DC, int dirArr[][], int dp[][]){

        for(int dr=0; dr<=DR; dr++){
            for(int dc=0; dc<=DC;dc++){
                if(dr==0 && dc==0){
                    dp[dr][dc] =  1;
                    continue;
                }

                int count = 0;
                for(int d=0; d<dirArr.length; d++){
                    int r = dr+dirArr[d][0];
                    int c = dc+dirArr[d][1];
                    while(r>=0 && c>=0 && r<=dr && c<=dc){
                        count+= dp[dr][dc];
                        r = dr+dirArr[d][0]; // radius 1-1 karke grow hote rahega
                        c = dc+dirArr[d][1];
                    }
                }
                dp[dr][dc] = count;
            }
        }
        return dp[DR][DC];        
    }

    //---------------------------------------------------
    // Problem 9: Unique Paths                   #LC62          
    //---------------------------------------------------
    public int uniquePaths(int m, int n) {
        int dirArr[][] = {{0,-1},{-1,0}}; // Left, up
        int dp[][] = new int[m][n];
        for(int arr[] : dp) Arrays.fill(arr,-1);
        int ans = uniquePaths_memo(m-1, n-1, dirArr, dp);
        return ans;
    }
    
    public int uniquePaths_memo(int dr, int dc, int dirArr[][],int[][] dp){
        
        if(dr==0 && dc==0){
           return dp[dr][dc] =  1;
        }
        if(dp[dr][dc] != -1) return dp[dr][dc];
        
        int count = 0;
        for(int d=0; d<dirArr.length; d++){
            int r = dr+dirArr[d][0];
            int c = dc+dirArr[d][1];
            if(r>=0 && c>=0 && r<=dr && c<=dc){
                count += uniquePaths_Tabu(r,c,dirArr,dp);
            }
        }
        
        return dp[dr][dc] = count;
    }
    
    public int uniquePaths_Tabu(int DR, int DC, int dirArr[][],int[][] dp){
        for(int dr=0; dr<=DR; dr++){
            for(int dc=0; dc<=DC; dc++){
                if(dr==0 && dc==0){
                    dp[dr][dc] =  1;
                    continue;
                }
                
                int count = 0;
                for(int d=0; d<dirArr.length; d++){
                    int r = dr+dirArr[d][0];
                    int c = dc+dirArr[d][1];
                    if(r>=0 && c>=0 && r<=dr && c<=dc){
                        count += dp[r][c];
                    }
                }
                dp[dr][dc] = count;
            }
        }
        return dp[DR][DC];
    }// TC: O(m*n)

    //---------------------------------------------------
    // Problem 10: Unique PathsII                #LC63         
    //---------------------------------------------------
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if(grid[m-1][n-1] == 1 || grid[0][0] == 1 ) return 0;

        int dirArr[][] = {{0,-1},{-1,0}};
        int dp[][] = new int[m][n];
        for(int arr[]: dp) Arrays.fill(arr,-1);
        int ans = uniquePathsWithObstacles_Tabu(grid,m-1,n-1,dirArr,dp);
        display2D(dp);
        return ans;
    }
    
    public int uniquePathsWithObstacles_memo(int grid[][], int dr, int dc, int dirArr[][],int dp[][]){
        if(dr==0 && dc==0) return dp[dr][dc] = 1;
        
        if(dp[dr][dc] != -1) return dp[dr][dc];
        
        int count = 0;
        for(int d=0; d<dirArr.length; d++){
            int r = dr+dirArr[d][0];
            int c = dc+dirArr[d][1];
            
            if(r>=0 && c>=0 && r<=dr && c<=dc && grid[r][c]!=1){
                count += uniquePathsWithObstacles_memo(grid,r,c,dirArr,dp);
            }
        }
        return dp[dr][dc] = count;
    }
    
    public int uniquePathsWithObstacles_Tabu(int grid[][], int DR, int DC, int dirArr[][],int dp[][]){        
        for(int dr=0; dr<=DR; dr++){
            for(int dc=0; dc<=DC; dc++){

                if(grid[dr][dc] == 1) continue;

                if(dr==0 && dc==0){
                    dp[dr][dc] = 1;
                    continue;
                }

                int count = 0;
                for(int d=0; d<dirArr.length; d++){
                    int r = dr+dirArr[d][0];
                    int c = dc+dirArr[d][1];

                    if(r>=0 && c>=0 && r<=dr && c<=dc && grid[r][c]!=1){
                        count += dp[r][c];
                    }
                }

                dp[dr][dc] = count;
            }
        }
        
        return dp[DR][DC];
    }

    //---------------------------------------------------
    // Problem 11: Max Rotate Sum               #LC396         
    //---------------------------------------------------
        
    public int maxRotateFunction(int[] nums) {
        int sumSFar = 0;
        int sum  = 0;
        for(int i=0; i<nums.length; i++){
            sumSFar += i*nums[i];
            sum += nums[i];
        }
        
        int maxSum = sumSFar;
        for(int i=0;i<nums.length-1; i++){
            sumSFar = sumSFar - sum + (nums.length)*nums[i];
            maxSum = (maxSum>sumSFar)? maxSum : sumSFar;
        }
        
        return maxSum;
    }//O(n) , also Not a dp problem

    //---------------------------------------------------
    // Problem 12: Minimum Path sum                #LC64         
    //---------------------------------------------------

    public int minPathSum(int[][] grid) {
        int dirArr[][] = {{0,-1},{-1,0}};
        int m = grid.length;
        int n = grid[0].length;
        int dp[][] = new int[m][n];
        for(int arr[]: dp) Arrays.fill(arr,-1);
        int ans = minPathSum_Tabu(grid,m-1,n-1,dirArr,dp);
        return ans;
    }
    
    public int minPathSum_memo(int grid[][], int dr, int dc, int dirArr[][],int dp[][]){
        if(dr==0 && dc==0) return dp[dr][dc] = grid[dr][dc];
        
        if(dp[dr][dc]!=-1) return dp[dr][dc];
        
        int count = (int) 1e9;
        for(int d=0; d<dirArr.length; d++){
            int r = dr+dirArr[d][0];
            int c = dc+dirArr[d][1];
            
            if(r>=0 && c>=0 && r<=dr && c<=dc){
                count = Math.min(count, minPathSum_memo(grid,r,c,dirArr,dp));
            }
        }
        return  dp[dr][dc] = count + grid[dr][dc];
    }
    
    public int minPathSum_Tabu(int grid[][], int DR, int DC, int dirArr[][],int dp[][]){
        for(int dr=0; dr<=DR; dr++){
            for(int dc=0; dc<=DC; dc++){
                if(dr==0 && dc==0){
                    dp[dr][dc] = grid[dr][dc];
                    continue;
                } 
                
                int count = (int) 1e9;
                for(int d=0; d<dirArr.length; d++){
                    int r = dr+dirArr[d][0];
                    int c = dc+dirArr[d][1];

                    if(r>=0 && c>=0 && r<=dr && c<=dc){
                        count = Math.min(count, dp[r][c]);
                    }
                }
                dp[dr][dc] = count + grid[dr][dc];   
            }
        }
        return dp[DR][DC];      
    }

    //---------------------------------------------------
    // Problem 13: Goldmine                     #gfg         
    //---------------------------------------------------

    public static int maxGold(int m, int n, int M[][]){
        int max = 0;
        int dirArr[][] = {{-1,-1},{0,-1},{1,-1}};
        int dp[][] = new int[m][n];
        for(int arr[]: dp) Arrays.fill(arr,-1);
        // Memoized solution
        // for(int r=0; r<m; r++){
        //     max = Math.max(max, maxGold_memo(M,r,n-1,dirArr,dp));
        // }

        // Tabulized Solution
        max = maxGold_Tabu(M,m-1,n-1,dirArr,dp);
        return max;
    }// 2^(m*n)
    
    public static int maxGold_rec(int mine[][], int dr, int dc, int dirArr[][]){
        if(dc==0){
            return mine[dr][dc];
        }
        
        int count = 0;
        for(int d=0; d<dirArr.length; d++){
            int r = dr+dirArr[d][0];
            int c = dc+dirArr[d][1];
            if(r>=0 && c>=0 && r<mine.length && c<mine[0].length){
                count = Math.max(count,maxGold_rec(mine,r,c,dirArr));
            }
        }
        return count+mine[dr][dc];
    }//(m*n)
    
    public static int maxGold_memo(int mine[][], int dr, int dc, int dirArr[][],int dp[][]){
        if(dc==0){
            return dp[dr][dc] = mine[dr][dc];
        }
        
        if(dp[dr][dc] != -1) return dp[dr][dc];
        int count = 0;
        for(int d=0; d<dirArr.length; d++){
            int r = dr+dirArr[d][0];
            int c = dc+dirArr[d][1];
            if(r>=0 && c>=0 && r<mine.length && c<mine[0].length ){
                count = Math.max(count,maxGold_memo(mine,r,c,dirArr,dp));
            }
        }
        return dp[dr][dc] = count+mine[dr][dc];
    }
    
    public static int maxGold_Tabu(int mine[][], int DR, int DC, int dirArr[][],int dp[][]){
        int maxExtract = 0;
        for(int dc=0; dc<=DC; dc++){ // column Fixed
            for(int dr=0; dr<=DR; dr++){ // Row wise travel
                if(dc==0){
                    dp[dr][dc] = mine[dr][dc];
                    if(dc==DC) maxExtract = Math.max(maxExtract,dp[dr][dc]);
                    continue;
                }
                int maxCoin = 0;
                for(int d=0; d<dirArr.length; d++){
                    int r = dr+dirArr[d][0];
                    int c = dc+dirArr[d][1];
                    
                    if(r>=0 && c>=0 && r<=DR && c<=DC){
                        maxCoin = Math.max(maxCoin,dp[r][c]);
                    }
                }
                dp[dr][dc] = maxCoin+mine[dr][dc];
                if(dc==DC) maxExtract = Math.max(maxExtract,dp[dr][dc]);
            }
        }
        return maxExtract;
    }   // special Test Case: m=2, n=1 
        // 1
        // 2

    //---------------------------------------------------
    // Problem 13:Count the number of ways to divide N in k groups incrementally         #gfg          
    //---------------------------------------------------- 



    // category 2 - String set
    // category 3 - wildcard

    public static void main(String args[]){
        System.out.println(mazePathJump(4,4));
    }

   

}