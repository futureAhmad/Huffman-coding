import java.util.Stack;
public class HuffmanNode {
     int frequency;
     HuffmanNode next,left,right;
     char letter;
     int postition;
     String key;

    public HuffmanNode(char l, int nb) {
        frequency = 1;
        next = left = right = null;
        letter=l;
        postition=nb;
    }
    void print_table(HuffmanNode e){
        System.out.println("--------------------------------------------------------");
        System.out.println("\tCHARACTER \t|\tFREQUENCY\t|\tBINARY HUFFMAN CODING");
        print_node(e);
        System.out.println("--------------------------------------------------------");
    }
    void print_node(HuffmanNode e ){
        if (e==null)
            return;
        if (e.left==null&&e.right==null)
            System.out.println("|\t\t"+e.letter+"\t\t|\t\t"+e.frequency+"\t\t|\t\t"+e.key);
            //System.out.println("\t"+e.letter+"\t|\t"+e.frequency+"\t|\t"+e.key);
        print_node(e.left);
        print_node(e.right);
    }
    public int getFrequency() {
        return frequency;
    }
    public  void displayTree(HuffmanNode tree) {
        //global stack
        Stack Stack1 = new Stack();
        Stack1.push(tree);
        int nBlanks = 40;
        boolean isRowEmpty = false;
        System.out.println("-----------------------------------Tree-----------------------------------");
        while(isRowEmpty==false)
        {
            //local stack
            Stack Stack2 = new Stack();
            isRowEmpty = true;
            for(int j=0; j<nBlanks; j++)
                System.out.print(' ');
            while(Stack1.isEmpty()==false)
            {
                HuffmanNode temp = (HuffmanNode)Stack1.pop();

                if(temp != null) {
                    if(temp.left == null || temp.right == null)
                        System.out.print(temp.letter+""+temp.frequency);
                    else
                        System.out.print(""+temp.frequency);

                    Stack2.push(temp.left);
                    Stack2.push(temp.right);
                    if(temp.left != null || temp.right != null)
                        isRowEmpty = false;
                }
                else
                {
                    System.out.print("--");
                    Stack2.push(null);
                    Stack2.push(null);
                }
                for(int j=0; j<nBlanks*2-2; j++)
                    System.out.print(' ');
            } // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while(Stack2.isEmpty()==false)
                Stack1.push( Stack2.pop() );
        } // end while isRowEmpty is false
        System.out.println("--------------------------------------------------------------------------");
    } // end displayTree()
    public void print(String prefix, HuffmanNode n, boolean isLeft) {
        if (n != null) {
            System.out.println(prefix + (isLeft ? "|-- " : "\\-- ") + n.frequency+""+n.letter);
            print(prefix + (isLeft ? "|   " : "    "), n.left, true);
            print(prefix + (isLeft ? "|   " : "    "), n.right, false);
        }
    }
    public void print_inOrder(HuffmanNode e){
        if (e==null)
            return;
        print_inOrder(e.left);
        System.out.print(e.frequency+""+e.letter+"   ");
        print_inOrder(e.right);
    }
}


