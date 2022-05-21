public class BST {
    public static HuffmanNode root, current;
    static int bit = 0;

    public BST(HuffmanNode temp) {
        root = temp;
    }

    public boolean empty() {
        return root == null;
    }

    public int retrieve_frequency() {
        return current.frequency;
    }

    public char retrieve_letter() {
        return current.letter;
    }

    public static HuffmanNode remove_two(heap minHeap) {
        while (minHeap.getSize() != 1) {
            HuffmanNode one = minHeap.remove();
            HuffmanNode two = minHeap.remove();
            HuffmanNode temp = new HuffmanNode('#', -1);
            temp.frequency = one.frequency + two.frequency;
            temp.right = two;
            temp.left = one;
            minHeap.insert(temp);
        }
        return minHeap.getNode(1);
    }

    public static void set_key(HuffmanNode temp, String key) {
        if (temp == null)
            return;
        temp.key = key;
        set_key(temp.left, key + "0");
        set_key(temp.right, key + "1");
    }

    public static int calc_Bit(HuffmanNode e) {
        calc(e);
        return bit;
    }

    private static void calc(HuffmanNode e) {
        if (e == null)
            return;
        if (e.right == null && e.left == null)
            bit += e.frequency * e.key.length();
        calc(e.left);
        calc(e.right);
    }

    public static String decode(HuffmanNode root, String code) {
        StringBuffer text = new StringBuffer();
        HuffmanNode current = root;
        for (int i = 0; i < code.length(); ++i) {
            if (code.charAt(i) == '0')
                current = current.left;
            else
                current = current.right;
            if (current != null && current.left == null && current.right == null) {
                text.append(current.letter);
                current = root;
            }
        }
        String re=text.toString();
        return re;
    }
}
