import java.util.Arrays;

    /* Approaches to Try 
        1. String length:  what when sn==sm  , when sn!=sm 
    */
public class StringProblemSets{

    
    //-------------------------------------------------------------
    //Problem 1: Longest Palindromic Subsequence         #LC516
    //-------------------------------------------------------------

    public int longestPalindromeSubseq(String s) {
        int sz = s.length();
        int dp[][] = new int[sz][sz];
        int ans =  longestPalindromicSubSeq_tabu(s,0,s.length()-1,dp);
        return ans;
    }
     
    public int longestPalindromicSubSeq_Rec(String s, int i, int j){// solving in terms of index
        if(i>=j){ // either size 0 or 1
            return i==j?1:0;
        }
        
        int call1 = longestPalindromicSubSeq_Rec(s,i+1,j-1);
        int call2 = longestPalindromicSubSeq_Rec(s,i+1,j);
        int call3 = longestPalindromicSubSeq_Rec(s,i,j-1);
        
        if(s.charAt(i) == s.charAt(j)) return call1+2;
        
        return Math.max(call2,call3);
    }
    
    public int longestPalindromicSubSeq_memo(String s, int i, int j, int dp[][]){
        if(i>=j){ // either size 0 or 1
            return dp[i][j] = (i==j?1:0);
        }
        
        if(dp[i][j] !=0 ) return dp[i][j];
        
        int call1 = longestPalindromicSubSeq_memo(s,i+1,j-1,dp);
        int call2 = longestPalindromicSubSeq_memo(s,i+1,j,dp);
        int call3 = longestPalindromicSubSeq_memo(s,i,j-1,dp);
        
        if(s.charAt(i) == s.charAt(j))
            return dp[i][j] = call1+2;
        
        return dp[i][j] = Math.max(call2,call3);
    }
    
    public int longestPalindromicSubSeq_tabu(String s, int I, int J, int dp[][]){
        int sz = s.length();
        for(int gap=0; gap<sz; gap++){
            for(int i=0, j=gap; j<sz; i++, j++){
                if(i>=j){ // either size 0 or 1
                    dp[i][j] = (i==j?1:0);
                    continue;
                }
                
                int call1 = dp[i+1][j-1];
                int call2 = dp[i+1][j];
                int call3 = dp[i][j-1];
                
                if(s.charAt(i) == s.charAt(j))
                 dp[i][j] = call1+2;
                
                else
                 dp[i][j] = Math.max(call2,call3);
                
            }
        }
        return dp[I][J];
    }// O(n/2)
 
    //-------------------------------------------------------------
    //Problem 2: Longest Common Subsequence             #LC1143
    //-------------------------------------------------------------
    public int longestCommonSubsequence(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int dp[][] = new int[n+1][m+1];
        for(int arr[]: dp) Arrays.fill(arr,-1);  // Because default 0, can be the part of ans,
        int ans = LCS_Tabu(s1,s2,n,m,dp);       //  When, abc, efg   LCS = 0
        return ans;
    }                         
                                            // solving in terms of length of string
    public int LCS_rec(String s1, String s2, int n, int m, int dp[][]){
        if(n==0|| m==0){
            return dp[n][m] =  0;
        }
        
        if(dp[n][m] != -1) return dp[n][m];
        
        int call1 = LCS_rec(s1,s2,n-1,m-1,dp);
        int call2 = LCS_rec(s1,s2,n-1,m,dp);
        int call3 = LCS_rec(s1,s2,n,m-1,dp);
        
        if(s1.charAt(n-1) == s2.charAt(m-1))
            return dp[n][m] = call1+1;
        else
            return dp[n][m] = Math.max(call2,call3);
    }
    
    public int LCS_Tabu(String s1, String s2, int N, int M, int dp[][]){
        for(int n=0; n<=N; n++ ){
            for(int m=0; m<=M; m++){
                if(n==0|| m==0){
                    dp[n][m] =  0;
                    continue;
                }
                
                int call1 = dp[n-1][m-1];
                int call2 = dp[n-1][m];
                int call3 = dp[n][m-1];

                if(s1.charAt(n-1) == s2.charAt(m-1))
                     dp[n][m] = call1+1;
                else
                     dp[n][m] = Math.max(call2,call3);
             }
        }
        
        return dp[N][M];         
    }


    //-------------------------------------------------------------
    //Problem 3: Edit Distance                          #LC72
    //-------------------------------------------------------------

    public int minDistance(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int dp[][] = new int[n+1][m+1];
        int ans = minDistance_Tabu(s1,s2,n,m,dp);
        return ans;
    }
    
    public int minDistance_Rec(String s1, String s2, int n, int m){
        if(n==0 || m==0){  // for either insert or delete
            return n==0? m : n;
        }
        int call1 = minDistance_Rec(s1,s2,n-1,m-1);// Equal or Replace
        int call2 = minDistance_Rec(s1,s2,n,m-1); // Insert
        int call3 = minDistance_Rec(s1,s2,n-1,m); // Delete
        
        if(s1.charAt(n-1) == s2.charAt(m-1)) 
            return call1; // No operation Required
        else
            return Math.min(Math.min(call1,call2),call3) + 1; // One Operation
    }
    
    public int minDistance_Memo(String s1, String s2, int n, int m, int dp[][]){
        if(n==0 || m==0){  // for either insert or delete
            return dp[n][m] = (n==0? m : n);
        }
        
        if(dp[n][m] !=0 ) return dp[n][m]; 
        
        int call1 = minDistance_Memo(s1,s2,n-1,m-1,dp);// Equal or Replace
        int call2 = minDistance_Memo(s1,s2,n,m-1,dp); // Insert
        int call3 = minDistance_Memo(s1,s2,n-1,m,dp); // Delete
        
        if(s1.charAt(n-1) == s2.charAt(m-1)) 
            return dp[n][m] =  call1; // No operation Required
        else
            return dp[n][m] = Math.min(Math.min(call1,call2),call3) + 1; // One Operation
    }
    
    public int minDistance_Tabu(String s1, String s2, int N, int M, int dp[][]){
        
         for(int n=0; n<=N; n++){
            for(int m=0; m<=M; m++){
                if(n==0 || m==0){  // for either insert or delete
                    dp[n][m] = (n==0? m : n);
                    continue;
                }
                
                int call1 = dp[n-1][m-1];// Equal or Replace
                int call2 = dp[n][m-1]; // Insert
                int call3 = dp[n-1][m]; // Delete
                
                if(s1.charAt(n-1) == s2.charAt(m-1)) 
                     dp[n][m] =  call1; // No operation Required
                else
                     dp[n][m] = Math.min(Math.min(call1,call2),call3) + 1; // One Operation
            } 
         }
         
         return dp[N][M];       
    }
                                            //Tc: O(str1 * str2), SC: O(str1*str2)

    //-------------------------------------------------------------
    //Problem 4: Edit Distance with cost             
    //-------------------------------------------------------------
                                            
    static int cost[] ={2,1,4}; 
    public int minDistanceWithCost_Tabu(String s1, String s2, int N, int M, int dp[][], int cost[]){
        
        for(int n=0; n<=N; n++){
           for(int m=0; m<=M; m++){
               if(n==0 || m==0){  // for either insert or delete
                   dp[n][m] = (n==0? m : n);
                   continue;                                            // cost of insertion, deletion , Replace given
               }                                                        // insertion, deletion, replace
               
               int call1 = dp[n-1][m-1] ;           // Equal
               int call2 = dp[n][m-1] + cost[0];    // Insert
               int call3 = dp[n-1][m] + cost[1];    // Delete
               int call4 = dp[n-1][m-1] + cost[2];  // Replace
               
               if(s1.charAt(n-1) == s2.charAt(m-1)) 
                    dp[n][m] =  call1; // No operation Required
               else
                    dp[n][m] = Math.min(Math.min(call1,call2),call3);
           } 
        }
        
        return dp[N][M];       
   }

    //-------------------------------------------------------------
    //Problem 5: Minimum number of deletions and insertions  #gfg             
    //-------------------------------------------------------------
    public int minOperations(String str1, String str2) 
	{ 
	    int n = str1.length();
	    int m = str2.length();
	    int dp[][] = new int[n+1][m+1]; // Because 0 might be the potential ans
	    for(int arr[]: dp) Arrays.fill(arr,-1);
	    int ans = minOperations_Memo(str1,str2,n,m,dp);
	    return ans;
	} 
	
	public static int minOperations_Memo(String s1, String s2, int n, int m, int dp[][]){
	    if(n==0 || m==0){  // for either insert or delete
            return dp[n][m] = (n==0? m : n);
        }
        
        if(dp[n][m] !=-1 ) return dp[n][m]; 
        
        int call1 = minOperations_Memo(s1,s2,n-1,m-1,dp);// Equal 
        int call2 = minOperations_Memo(s1,s2,n,m-1,dp); // Insert
        int call3 = minOperations_Memo(s1,s2,n-1,m,dp); // Delete
        
        if(s1.charAt(n-1) == s2.charAt(m-1)) 
            return dp[n][m] =  call1; // No operation Required
        else
            return dp[n][m] = Math.min(call2,call3) + 1; // One Operation
	}


    //-------------------------------------------------------------
    //Problem 6:          #LC115
    //-------------------------------------------------------------


    //-------------------------------------------------------------
    //Problem 7:          #LC583
    //-------------------------------------------------------------

    //-------------------------------------------------------------
    //Problem 8:         #LC1035
    //-------------------------------------------------------------

    //-------------------------------------------------------------
    //Problem 9: Longest Palindromic Substring         #LC5
    //-------------------------------------------------------------

    //-------------------------------------------------------------
    //Problem 10: Longest Common Substring         
    //-------------------------------------------------------------

}
                       

