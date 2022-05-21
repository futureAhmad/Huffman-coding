public class Linked_List {
    private static HuffmanNode head,current,tail;
     int nb,size;
    public Linked_List(String line) {
        head = current = null;
        nb=0;
        size=0;
        for (int i=0; i < line.length();i++){ // read every character in line ( message in file )
            if (line.charAt(i)=='\n'){
                insert('&'); // cause of replace \n by & , that is when we print the output the \n doesn't effect
                continue;
            }
            insert(line.charAt(i));
            //print(); //traverce the insert every step
        }
    }
    public Linked_List(HuffmanNode bst){
        add_from_tree(bst);
        replace_new_line('&'); // do you remember when we replace \n by & ? ( above in other constrctor in this class ),
    }                            // now we change it  to old letter which are \n ( change & to \n )
    public boolean empty () { return head == null; }
    public boolean last () { return current.next == null; }
    public void findfirst () { current = head; }
    public void findnext () { current = current.next; }
    public int retrieve_frequency() { return current.frequency; }
    public char retrieve_letter() { return current.letter; }
    public void insert (char letter) { // create node for every character
        if (exist(letter)) // if the character was exit, don't crea a new node for it, just increment the count for this character
            return;
        nb++;
        HuffmanNode val=new HuffmanNode(letter,nb);
        if (empty()) {
            current = tail = head = val;
        }
        else {
            tail.next = val;
            tail = val;
        }
    }
    public boolean exist(char letter){ // check in the list if the character was exist or not, to decide if we
        findfirst();                  // create new node or just increment the counter for the existing character
        if (empty())
            return false;
        while (!last()){
            if (retrieve_letter()==letter) {
                current.frequency++;
                return true;
            }
            findnext();
        }
        if (retrieve_letter()==letter) {
            current.frequency++;
            return true;
        }
        return false;

    }
    public void remove(char letter){
        findfirst();
        if (retrieve_letter()==letter){
            head=head.next;
            nb--;
            return;
        }
        HuffmanNode prev=head;
        while (!last()){

            if (retrieve_letter()==letter){
                prev.next=current.next;
                nb--;
                break;
            }
            prev=current;
            findnext();
        }
        if (retrieve_letter()==letter) {
            prev.next = current.next;
            nb--;
        }

    }
    public HuffmanNode remove_front(){ // remove head
        HuffmanNode temp=head;
        head=head.next;
        nb--;
        findfirst();
        return temp;
    }
    public void add_from_tree(HuffmanNode e){ // traverse the huffman tree and add each character in new list
        if (e==null)                         // to decode the huffman code
            return;
        if (e.right==null&& e.left==null) {
            insert_front(e);
            nb++;
        }
        add_from_tree(e.left);
        add_from_tree(e.right);
    }
    public void insert_front(HuffmanNode e){ // insert from huffman tree, to decode the huffman code
        if (empty())
            head=e;
        else{
            e.next=head;
            head=e;
        }
    }
    public String search(char e){ // return huffman code for specific letter
        HuffmanNode p=head;
        while (p!=null){
            if (p.letter==e)
                return p.key;
            p=p.next;
        }
        return "";
    }
    public static void replace_new_line(char e){ // replace new line the we was replace it to &, to avoid print problem
        HuffmanNode p=head;
        while (p!=null){
            if (p.letter==e) {
                p.letter = '\n';
                break;
            }
            p=p.next;
        }
    }
    public void print(){
        HuffmanNode p=head;
        while (p!=null){
            System.out.print(p.letter+""+p.frequency+"\t");
            p=p.next;
        }
        System.out.println();
    }


}
