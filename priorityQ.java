public class priorityQ {
    private int size;
    private HuffmanNode head;
    int space=1;
    public priorityQ(Linked_List list) {
        head = null;
        size = 0;
        System.out.println("list are: "); // print list before convert it to priorityQ
        while (!list.empty()){
            HuffmanNode temp=list.remove_front();
            System.out.print(temp.letter+","+ temp.frequency+"       ");
            enqueue(temp); // add node from list to priorityQ
            if (space%15==0) // each 15 print node, make new line ( for design )
                System.out.println();
            space++; // to count when we make new line ( for design )
        }
        System.out.println();
    }
    public int length (){
        return size;
    }
    public void enqueue(HuffmanNode e) {
        int pty=e.frequency;
        if((size == 0) || (pty < head.frequency)) {
            e.next = head;
            head = e;
        }
        else {
            HuffmanNode p = head;
            HuffmanNode q = null;
            while((p != null) && (pty >= p.frequency)) {
                q = p;
                p = p.next;
            }
            e.next = p;
            q.next = e;
        }
        size++;
    }
    public HuffmanNode serve(){
        HuffmanNode temp = head;
        head = head.next;
        size--;
        return temp;
    }




}
