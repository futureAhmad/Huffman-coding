public class heap {
        private HuffmanNode[] Heap;
        private int size;
        private int maxsize;
        private static final int FRONT = 1; // The root in heap
        public heap(priorityQ list, int nb){
            this.maxsize = nb;
            this.size = 0;
            Heap = new HuffmanNode[this.maxsize + 1];
            HuffmanNode temp;
            System.out.println("\nPQ are:"); // print priorityQ before convert it to min heap
            for (int i=0;i< maxsize;++i){
                temp= list.serve();
                System.out.print(temp.letter+","+ temp.frequency+"       ");
                insert(temp);
                if ((i+1)%15==0) // make new line ( for design )
                    System.out.println();
            }
            System.out.println();
            System.out.println("\nMin heap:");
            print();
            System.out.println("_____________________\n key in tree are:");

        }
        private int parent(int pos) { return pos / 2; }
        private int leftChild(int pos) { return (2 * pos); }
        private int rightChild(int pos) { return (2 * pos) + 1; }
        private boolean isLeaf(int pos) {
            if (pos > (size / 2) && pos <= size)
                return true;
            return false;
        }
        private void swap(int left, int right) {
            HuffmanNode tmp= Heap[left];
            Heap[left] = Heap[right];
            Heap[right] = tmp;

        }
        public HuffmanNode remove() {
            HuffmanNode popped = Heap[FRONT];
            Heap[FRONT] = Heap[size--];
            if (size!=0) // if tree contain one node
                minHeapify(FRONT);
            return popped;
        }
        private void minHeapify(int pos) { // keep the min heap tree property
            if (!isLeaf(pos)) {
                if (Heap[pos].frequency > Heap[leftChild(pos)].frequency
                        || Heap[pos].frequency > Heap[rightChild(pos)].frequency) {
                    System.out.println(Heap[pos].letter+""+Heap[leftChild(pos)].letter
                    +""+Heap[rightChild(pos)].letter);
                    if (Heap[leftChild(pos)].frequency < Heap[rightChild(pos)].frequency) {
                        swap(pos, leftChild(pos));
                        minHeapify(leftChild(pos));
                    }
                    else {
                        swap(pos, rightChild(pos));
                        minHeapify(rightChild(pos));
                    }
                }
            }
        }
        public void insert(HuffmanNode element) {
            if (size >= maxsize) {
                System.out.println("list is full");
                return;
            }
            Heap[++size] = element;
            int current = size;
            if (size!=1) {
                while (current!=1 && Heap[current].frequency < Heap[parent(current)].frequency) {
                    swap(current, parent(current));
                    current = parent(current);
                }
            }
        }
        public void print() {
            int left,right;
            for (int i = 1; i <= size / 2; i++) {
                System.out.print(" PARENT : " + Heap[i].letter+","+Heap[i].frequency);
                right = 2 * i + 1;
                left = 2 * i;
                if (left <= size)
                    System.out.print(" LEFT CHILD : " + Heap[2 * i].letter+","+Heap[2 * i].frequency);
                else
                    System.out.print("LEFT CHILD : NULL");
                if (right <= size)
                    System.out.print(" RIGHT CHILD :" + Heap[2 * i + 1].letter+","+Heap[2 * i + 1].frequency);
                else
                    System.out.print(" RIGHT CHILD : NULL");
                System.out.println();
            }
        }
        public int getMaxvalue(){
            int max= Math.max(Heap[size].frequency, Heap[size - 1].frequency);
            System.out.println("max in heap is:"+max);
            return max;
        }
        public int getSize() {
        return size;
    }
        public HuffmanNode getNode(int index){
            return Heap[index];
        }

}

