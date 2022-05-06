import java.util.*;
public class FaultyKeyboard {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String words[] = sc.nextLine().split(" ");
        String clip = sc.nextLine();

        HashMap<Character,Integer> hm = new HashMap<>();
        int idx = 0;
        for(char ch: clip.toCharArray()){
            hm.put(ch,idx);
            idx++;
        }

        int ans = 0;
        for(String wrd : words){
            int op = 0;
            for(int i=0;i<wrd.length();i++){
                int p=0, b=0, m=0;
                char ch = wrd.charAt(i);
                if(hm.containsKey(ch)){
                   p++;
                   int k=1;
                   while(wrd.length()>i+k && hm.containsKey(wrd.charAt(i+k))){
                        k++;
                   }
                   int pos;
                   if(k!=1){
                       pos = hm.get(wrd.charAt(i+k-1));
                       i=k-1;
                   }
                   else
                        pos = hm.get(ch);

                        
                   if(pos!=0){
                       m++;
                       b = b+pos;
                       m++;
                   }
                   if(pos!=clip.length()-1){
                       b = b  + clip.length()-1-pos;
                   }
                }
                op+=p+m+b;
            }
            ans+=op;
            // System.out.println(wrd+" : "+op);
        }

        System.out.println(ans);
    }
}
