import java.util.HashSet;

// Mid of a Linked list
public class LinkedList{

    static class ListNode{
        int val;
        ListNode next;

        ListNode(int val){
            this.val = val;
            this.next = null;
        }
    }

    // LC876 - 2nd mid
    public static ListNode midOfList(ListNode head){
        if(head==null || head.next==null) 
            return head;

        ListNode slow = head;
        ListNode fast = head;

        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Mid of List - 1st mid        // we use this mainly for solving
    public static ListNode midOfList_firstNode(ListNode head){
        if(head==null || head.next==null) 
            return head;

        ListNode slow = head;
        ListNode fast = head;

        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Length of List
    public static int length(ListNode head){
        int c = 0;
        while(head!=null){
            c++;
            head=head.next;
        }
        return c;
    }

    // LC206
    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next==null) return head;

        ListNode prev = null;
        ListNode curr = head;

        while(curr!=null){
            ListNode frwd = curr.next; // Backup
            
            curr.next = prev;
            prev = curr;
            curr = frwd;
        }
        return prev; 
    }

    // LC234
    public boolean isPalindrome(ListNode head) {
        ListNode midNode = midOfList_firstNode(head);
        ListNode nNode = midNode.next; // NewNode
        midNode.next = null;

        ListNode rhead = reverseList(nNode); // reverseHead

        while(rhead!=null){
            if(rhead.val!=head.val) return false;
            rhead = rhead.next;
            head = head.next;
        }

        ListNode ohead = reverseList(rhead);
        midNode.next = ohead;
        return true;
    }

    // LC143
    public static void reorderList(ListNode head){
        if(head == null || head.next==null) return ;
        ListNode midNode = midOfList_firstNode(head);
        ListNode nhead = midNode.next;
        midNode.next = null;

        ListNode rhead = reverseList(nhead);

        ListNode curr1 = head, curr2 = rhead;
        while(curr2!=null){
            ListNode frwd1 = curr1.next, frwd2 = curr2.next; // Backup

            curr1.next = curr2;
            curr2.next = frwd1;

            curr1 = frwd1;
            curr2 = frwd2;
        }
    }

    //LC21
    public static ListNode mergeTwoSortedList(ListNode head1, ListNode head2){
        
        ListNode dummy = new ListNode(Integer.MIN_VALUE);

        ListNode curr1 = head1, curr2 = head2;
        ListNode prev = dummy;

        while(curr1!=null && curr2!=null){
            if(curr1.val<=curr2.val){
                prev.next = curr1;
                curr1 = curr1.next;
            }
            else{
                prev.next = curr2;
                curr2 = curr2.next;
            }
            prev = prev.next;
        }

        prev.next = (curr1==null)?curr2:curr1;
        
        ListNode head = dummy.next;
        return head;
    }

    // unfold list - pepcoding
    public static void unfold(ListNode head) {
        if(head == null || head.next==null) 
            return;
        
        ListNode c1 = head;
        ListNode c2 = head.next;
        ListNode list2Head = c2;
        
        while(c2!=null && c2.next != null){
            c1.next = c1.next.next;
            c2.next = c2.next.next;
            
            c1 = c1.next;
            c2 = c2.next;
        }
        c1.next = null;
        if(c2!=null) c2.next = null;
        
        ListNode rhead = reverseList(list2Head);
        c1.next = rhead;
    }

    // LC19
    public ListNode removeNthFromEnd(ListNode head, int n) {

        int sz = length(head);
        int i = sz-n;
        if(i==0){       // To Remove the first Node
            head = head.next;
        }
        else{
            ListNode temp = head;
            while(--i != 0){
                temp = temp.next;
            }
            temp.next = temp.next.next;
        }
        return head;        
    }// Tc: 2n

    public ListNode removeNthFromEndOptimized(ListNode head, int n) {
        ListNode s = head; // Slow Pointer
        ListNode f = head; // fast Pointer
        
        while(n-- > 0){
            f = f.next;
        }

        if(f==null){ // To handle the 1st node to remove
            ListNode rm = s;
            head = rm.next;
            rm.next = null;  
        }
        else{
            while(f!=null && f.next!=null){
                s=s.next;
                f=f.next;
            }        
            ListNode rm = s.next;
            s.next = rm.next;
            rm.next = null;
        }       
        return head;
    }// Tc: n

    //  LC445: ADD Two Numbers II   
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode resHead = new ListNode(Integer.MIN_VALUE);
        ListNode prev = resHead;
        
        int c = 0;
        while(c!=0 || c1!=null || c2!=null){
            int sum = c;
            sum += (c1!=null) ? c1.val: 0;
            sum += (c2!=null) ? c2.val: 0;
            c = sum/10;
            sum = sum%10;
            ListNode nnode = new ListNode(sum);
            prev.next = nnode;
            prev = prev.next;
            if(c1!=null) c1=c1.next;
            if(c2!=null) c2=c2.next;
        }
        
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        
        ListNode rm = resHead;
        resHead = resHead.next;
        rm.next = null;
        resHead = reverseList(resHead); 
        
        return resHead;    
    }

    //substract list
    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
            int cmptr = comparator(l1,l2);
            if(cmptr == -1){
                ListNode temp = l1;
                l1 = l2;
                l2 = temp;
            }
            
            ListNode l1RevHead = reverseList(l1);
            ListNode l2RevHead = reverseList(l2);
            
            ListNode c1 = l1RevHead, c2 = l2RevHead;
            ListNode ansHead = new ListNode(-1);
            ListNode prev = ansHead;
            
            int borrow = 0;
            while(borrow!=0 || c1!=null || c2!=null){
                int sum = -borrow;
                sum+= (c1!=null? c1.val: 0) - (c2!=null? c2.val: 0);
                
                borrow = (sum<0)? 1 : 0;    // taking borrow
                sum = (sum<0)? sum+10 : sum; 
                
                ListNode nnode = new ListNode(sum); // store digit
                prev.next = nnode;
                prev = prev.next;
                
                if(c1!=null) c1 = c1.next;
                if(c2!=null) c2 = c2.next;
            }
            ansHead = reverseList(ansHead.next);
            
            ansHead = removePrecedingZeros(ansHead);
            if(ansHead == null){
                ListNode nnode = new ListNode(0);
                ansHead = nnode;
            }
            return ansHead;
    }

    public static ListNode removePrecedingZeros(ListNode head){
        while(head!=null && head.val==0){
            head = head.next;
        }
        return head;
    }

    public static int comparator(ListNode l1, ListNode l2){
    int len1 = length(l1);
    int len2 = length(l2);
    if(len1 < len2) return -1; // if less digits
    else if(len1 > len2) return 1; 
    else{
        while(l1!=null && l2!=null){
            if(l1.val<l2.val) return -1;
            
            l1 = l1.next;
            l2 = l2.next;
        }
        return 0;
    }

}

    // LC83 Remove Duplicates 
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)  return head==null? null : head;
            
        ListNode prev = head;
        ListNode curr = head.next;
        
        while(curr!=null){
            if(prev.val != curr.val){
                prev.next = curr;
                prev = prev.next;
            }            
            curr = curr.next; 
        }
        prev.next = null;
        return head;
    }   // TC: 0(n)

    //  LC82 Remove Duplicates from Sorted List II 
    public ListNode deleteDuplicatesII(ListNode head) {
        if(head==null || head.next==null) return head==null? null : head;
        ListNode dummy = new ListNode(-1);
        ListNode itr = dummy;
        itr.next = head; // Potential unique element
        ListNode curr = head.next;
        
        while(curr!=null){
            boolean isLoopRun = false;
            while(curr!=null && itr.next.val == curr.val){
                isLoopRun = true;
                curr = curr.next;
            }
            if(isLoopRun) itr.next = curr;
            else itr = itr.next;
            
            if(curr!=null) curr = curr.next;
        }
        return dummy.next;
    }

    // LC328 Odd Even Linked List  
    public ListNode oddEvenList(ListNode head) {
        if(head==null || head.next==null) return head==null? null : head;
        
        ListNode o = head;
        ListNode e = head.next;
        ListNode snode = head.next;
        while(e!=null && e.next!=null){
            o.next = o.next.next;
            e.next = e.next.next;
            o = o.next;
            e = e.next;
        } 
        o.next = snode;
        return head;
    }// TC: O(n/2); Sc: O(1);

    // Segregate Even Odd 
    public static ListNode segregateEvenOdd(ListNode head) {
        ListNode dummyOdd = new ListNode(-1);
        ListNode dummyEven = new ListNode(-1);
        
        ListNode curr = head,o=dummyOdd, e = dummyEven;
        
        while(curr!=null){
            if(curr.val%2 != 0){// odd
                o.next = curr;
                o = o.next;
            }
            else{
                e.next = curr;
                e = e.next;
            }
            curr = curr.next;
        }
        e.next = dummyOdd.next;
        o.next = null;
        
        return dummyEven.next;
    }// TC: O(n), SC:O(1)

    //  LC148 Sort List 
    public ListNode sortList(ListNode head) {
        if(head==null || head.next == null) return head==null? null : head;
        ListNode mid = midOfList_firstNode(head);
        ListNode left = head; // Left Half List
        ListNode right = mid.next; // right Half List 
        mid.next = null;
        
        ListNode fhalf = sortList(left);
        ListNode shalf = sortList(right);
        ListNode fullSortedList = mergeTwoSortedList(fhalf,shalf);
        return fullSortedList;  
    } // TC: O(nlogn)

    // Lc:23 Merge K sorted List
    public ListNode mergeKLists_BF(ListNode[] lists) { // Brute force
        ListNode ans = null;
        for(ListNode list: lists){
            ans = mergeTwoSortedList(ans, list);
        }
        return ans;
    }// TC: Nk

    public ListNode mergeKLists_Optimal(ListNode[] lists) {
        if(lists.length == 0 ) return null;
        ListNode res = mergeKListsHelper(lists, 0, lists.length-1);
        return res;
    }
    
    public ListNode mergeKListsHelper(ListNode lists[], int si, int ei){
        if(si == ei){
            return lists[si];
        }
        int mid = (si+ei)/2;
        ListNode lhalf = mergeKListsHelper(lists,si,mid);   // T(k/2)
        ListNode rhalf = mergeKListsHelper(lists,mid+1,ei); // T(k/2)
        ListNode slist = mergeTwoSortedList(lhalf, rhalf);  // O(kl)
        return slist;
    }// T(k) = T(k/2)+T(k/2)+kl
     //      = 2T(k/2) + kl

    // LC:25 Reverse Nodes in K Groups
    class Solution {
    
    ListNode th=null, tt = null; // temp head, temp tail
    private void addFirst(ListNode node){
        if(th==null)
            th = tt = node;
        else{
            node.next = th;
            th = node;
        }
    } 
    
    private  int length(ListNode node){
        int count = 0;
        while(node != null){
            count++;
            node = node.next;
        }
        return count;
    }
    
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null || head.next==null || k==1) return head;
        
        int len = length(head);
        ListNode ah=null, at=null, curr = head; // ah=actual head, at = actual tail
        
        while(len>=k){
            int tempk = k;
            while(tempk-- > 0){
                ListNode frwd = curr.next;
                curr.next = null;
                addFirst(curr);
                curr = frwd;
            }
            len-=k;
            if(ah == null){
                ah = th;
                at = tt;
            }
            else{
                at.next = th;
                at = tt;
            }
            th=tt = null;
        } 
        at.next = curr;
        return ah;
    }
}
    
    // LC: 92 Reverse List in between
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null|| m==n) return head;
        
        int idx = 1;
        ListNode prev = null;
        ListNode curr = head;
        
        while(curr!=null){
            while(idx>=m && idx<=n){
                ListNode frwd = curr.next;
                curr.next = null;
                addFirst(curr);
                curr = frwd;
                idx++;
            }
            if(idx>n){
                if(prev==null){  // if m==1
                    head = afH;
                    afT.next = curr;
                    return head;
                }
                else{
                    prev.next = afH;
                    afT.next = curr;
                }
                
                break;
            }
            
            idx++;
            prev = curr;
            curr = curr.next;
        }
        
        return head;
    }
    
    ListNode afH = null, afT = null; // addFirstHead  , addFirstTail
    private void addFirst(ListNode node){
        if(afH==null) afH=afT=node;
        else{
            node.next = afH;
            afH = node;
        }
    }

    // LC: 203 Remove Linked List Elements
    public ListNode removeElements(ListNode head, int val) {
        if(head == null ) return head;
        
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy, curr = head;
        while(curr!=null){
            if(curr.val != val){
                prev.next = curr;
                prev = curr;
            }         
            curr = curr.next;
        }
        prev.next = null;
        
        return dummy.next;
    }

    // LC: 817 Linked list components
    public int numComponents(ListNode head, int[] nums) {
        if(head == null) return 0;
        
        HashSet<Integer> hs = new HashSet<>();
        for(int val: nums) hs.add(val);
        
        int comp = 0;
        boolean flag = false;
        while(head!=null){
            if(hs.contains(head.val) && flag==false){
                comp++;
                flag = true;
            }
            else if(hs.contains(head.val)==false) flag = false;
            
            head=head.next;
        }
        return comp;
        
    }

    // LC: 141 Linked List Cycle
    public boolean hasCycle(ListNode head) {
        if(head==null || head.next == null) return false;
        
        ListNode slow = head,fast=head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;   // speed = 2x , but can be of any 3x,5x,8x,...
            
            if(slow == fast) return true;
        }
        return false;
    }

    // LC: 142 Linked list Cycle II - Find the Intersection
    public ListNode detectCycle(ListNode head) {
        if(head==null || head.next == null) return null;
        
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            
            if(slow == fast) break;
        }
        
        if(slow!=fast) return null; // No Cycle present
        
        slow = head;        // Find intersection
        while(slow!=fast){
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
        
    }

    // LC: 160 Intersection of Two Linked Lists
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        
        ListNode tailA = getTail(headA);
        tailA.next = headB;
        ListNode intersectNode = detectCycle(headA);
        
        tailA.next = null;
        return intersectNode;
    }
    
    private ListNode getTail(ListNode head){
        if(head==null || head.next == null) return head;
        
        ListNode curr = head;
        while(curr.next!=null) curr = curr.next;
        
        return curr;
    }

    // LC: 138. Copy List with Random Pointer
    // public Node copyRandomList(Node head) {
        
    // }
}


