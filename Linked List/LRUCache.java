import java.util.HashMap;

public class LRUCache {

    private static class Node{ // doubly LL
        int key = 0;
        int val = 0;
        Node prev = null, next = null;
        
        private Node(int key,int val){
            this.key = key;
            this.val = val;
        }
    }

    private int  cap = 0;
    public static Node head,tail;
    private HashMap<Integer,Node> map;

    private void makeRecentlyUsed(Node app){
        if(this.head == app) return;
        removeNode(app);
        addFirst(app);
    }

    public void balanceCapacity(){
        if(map.size()>this.cap){
            Node tail = this.tail;
            removeNode(tail);  // Remove From Linked List
            int key = tail.key;
            map.remove(key);    // Remove From HM
        }
    }

    private void addFirst(Node node){
        if(node == null) return;

        if(head == null){
            head = tail = node;
        }
        else{
            head.next = node;
            node.prev = head;
            head = node;
        }
    }

    private void removeNode(Node node){
        if(head==tail && head==node){
            head = tail = null;
        }
        else if(head == node){
            Node left = node.prev;
            left.next = null;
            head = node;
        }
        else if(this.tail == node){
            Node right = node.next;
            right.prev = null;
            tail = right;
        }
        else{
            Node left = node.prev;
            Node right = node.next;
            left.next = right;
            right.prev = left;
        }
        node.prev = node.next = null; // break completely
        
    }

    public LRUCache(int capacity) {  // Constructor
        this.cap = capacity;
        head = tail = null;
        map = new HashMap<>();
    }
    
    public int get(int key) { // If app already open get its state, and make it Recent
        if(map.containsKey(key)){ // App already open
            Node node = map.get(key);
            int value = node.val;
            makeRecentlyUsed(node);
            return value;
        }
        // Otherwise 
        return -1;
    }
    
    // key: AppName, Value:  StateOfApp
    public void put(int key, int value) {
        if(map.containsKey(key)){ // If app already Open
            Node app = map.get(key);
            app.val = value;
            makeRecentlyUsed(app);
        }
        else{ // New app to open
            Node newApp = new Node(key,value);
            addFirst(newApp);
            map.put(key,newApp); // add in hashmap
            balanceCapacity();
        }
    }

    public static void main(String args[]){
        LRUCache lrc = new LRUCache(4);
        

        lrc.put(1,10475);
        lrc.put(2,222);
        lrc.put(3,333);
        lrc.put(4,444);
        lrc.put(5,555);
        
        int val = lrc.get(1);
        System.out.println(val);
        System.out.println(head.val);
    }
}
