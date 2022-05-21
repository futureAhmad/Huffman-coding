import java.io.*;
import java.util.Scanner;

public class Huffman {
    public static void main(String[] args) throws IOException {
        Scanner read = new Scanner(System.in);
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Hi, this project is about huffman coding algorithm, basically this algorithm is used for reducing the size of text file.");
        System.out.println("For example, if you have a text file contain a message the message is set of character, each character is represented by 8 bits.");
        System.out.println("Huffman coding is try to reduce the size of the message by compress the file depending on frequency ");
        System.out.println("of each letter.");
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.print("\nDo you want to compress some of your file ? Enter 1 for yes OR 0 for no >> ");
        int choice = read.nextInt();
        // ask the user if he want to compress any file or not
        if (choice == 1) {
            System.out.print("write the path of you text file, ( example, C:\\user\\download\\data.txt ) >> ");
            String path=read.next();
            System.out.println("------------------------------------------------------------------------------------------------------");
            try { // if the path doesn't exit, it will handle it
                FileReader file = new FileReader(path);
                BufferedReader reader = new BufferedReader(file);
                //read the file
                String key = ""; // the file message will store in this variable
                String line = reader.readLine();
                while (line != null) {
                    key += line + "\n"; // read every line
                    line = reader.readLine();
                }

                //create a list
                Linked_List LinkedList = new Linked_List(key); // create a list for each character in key variabel
                int list_length = LinkedList.nb;

                //convert list to priorityQ
                priorityQ queue = new priorityQ(LinkedList);

                //convert priorityQ to min heap
                heap minHeap = new heap(queue, list_length);

                //convert min heap to BST
                HuffmanNode temp = BST.remove_two(minHeap);
                BST.set_key(temp, "");
                temp.print_table(temp); // print all the node attribute ( character,frequent, binary code )

                // calc bit for each character after do the huffman code
                double bit = BST.calc_Bit(temp);
                System.out.println("size of message before compress: " + (key.length() * 8)+" bits");
                System.out.println("size of message after compress: " + bit+" bits");
                System.out.printf("Ratio of compression %.2f",  (bit / (key.length() * 8)) * 100);
                String encoding = "";
                Linked_List list = new Linked_List(temp); // create a list from huffman tree, to fast search
                                                         // about each character code
                // get code for each character ( 01 huffman code )
                for (int i = 0; i < key.length(); ++i) {
                    //System.out.println("i is: " + i + " char is: " + key.charAt(i)); // track each letter code
                    encoding = encoding.concat(list.search(key.charAt(i)));
                }

                System.out.println(" %\n\nencode text: " + encoding);
                System.out.println("\n-------------------------------------------------------------  decode text: ( the real text )  -------------------------------------------------------------");
                System.out.println(BST.decode(temp, encoding));
                System.out.println("______________________________________");
                temp.displayTree(temp);
                System.out.println("------- tree are ----------");
                System.out.println("Notice that first element id the root, and every node with |-- is left child , and with \\-- is right child");
                temp.print("",temp,false);
                System.out.println("------in order-----");
                temp.print_inOrder(temp);
            }catch (FileNotFoundException e){
                System.out.println("wrong path !");
            }
        }else{
            System.out.println("Thinks");
        }
    }
}
