
class MyLinkedList {

    private class Node{
        int val; 
        Node next;

        Node(int val){
            this.val = val;
        }
    }

    private Node head,tail;
    private int size;
    public MyLinkedList() {
        head = tail = null;
        size = 0;
    }
    
    public int get(int index) {
        if(index>=this.size) return -1;
        Node temp = head;
        for(int i=0; i<index; i++){
            temp = temp.next;
        }
        return temp.val;
    }//O(n)
    
    public void addAtHead(int val) {
        Node newNode = new Node(val);
        if(head == null){
            head = tail = newNode;
        }
        else{
            newNode.next = head;
            head = newNode;
        }
        this.size++;
    }//O(1)
    
    public void addAtTail(int val) {
        Node newNode = new Node(val);
        if(head == null){
            head = tail = newNode;
        }
        else{
            tail.next = newNode;
            tail = newNode;
        }
        this.size++;
    }//O(1)
    
    public void addAtIndex(int index, int val) {
        if(index>this.size) return;
        else if(index == 0) addAtHead(val);
        else if(index == this.size) addAtTail(val);
        else{
            Node temp = head;
            for(int i=0; i<index-1; i++) temp = temp.next;
            Node newNode = new Node(val);
            newNode.next = temp.next;
            temp.next = newNode;
            this.size++;
        }
        
    }//O(1)
    
    public void deleteAtIndex(int index) {
        if(index>=this.size) return;
        else if(index == 0) removeHead();
        else if(index == this.size-1) removeTail();
        else{
            Node temp = head;
            for(int i=0; i<index-1; i++){
                temp = temp.next;
            }
            Node rmNode = temp.next;
            temp.next = rmNode.next;
            rmNode.next = null;
            this.size--;
        }//O(n)
    }

    public void removeHead(){
        if(head == null) return;
        else if(head==tail) head=tail=null;
        else{
            Node rmNode = head;
            head = head.next;
            rmNode.next = null;
        }
        this.size--;
    }//O(1)

    public void removeTail(){
        if(head == null) return;
        else if(head == tail) head = tail = null;
        else{
            Node temp = head;
            while(temp.next.next!=null){
                temp = temp.next;
            }
            temp.next = null;
            tail = temp;
        }
        this.size--;
    }// O(n)



}

