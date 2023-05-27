import java.util.Arrays;

public class MaxHeap {
    
    public int size;
    public int capacity;
    public int[] heap;

    //Constructor method for instalizing
    public MaxHeap(int capacity){
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[this.capacity];
    }

    //Getter method for Size
    public int getSize(){
        return size;
    }

    //Getter method for Capacity
    public int getCapacity(){
        return capacity;
    }

    //Getter method for the values of the heap array
    public int[] getValues(){
        int[] currentHeap = new int[getSize()];
        for(int i = 0; i < getSize(); i++ ){
            currentHeap[i] = heap[i];
        }
        return currentHeap;
    }
    
    //Method that adds to the heap, afterwards it sorts the array so that the parent node
    //is bigger than the child nodes
    public void add(int value){
        if(getSize() < getCapacity()){
            heap[getSize()] = value;
            size++;
            int index = getSize()-1;
            int parentIndex = (index - 1)/2;
            outerloop:
            while(heap[index] > heap[parentIndex]){
                int temp = heap[parentIndex];
                heap[parentIndex] = heap[index];
                heap[index] = temp;

                index = parentIndex;
                if( index == 0 ){
                    break outerloop;
                }
                parentIndex = (index - 1)/2;
            }
        }
        else{
            throw new IllegalStateException("The Heap is already full.");
        }
    }

    //Returns the root node(The max of the array) and deletes it from the array
    //Afterwards it calls maxHeapify to return back the maxHeap properties
    public int extractMax(){
        if(getSize() > 0){
            int max = heap[0];
            heap[0] = 0;
            size--;
            maxHeapify(0);
            return max;
        }
        else{
            throw new IllegalStateException("Heap is empty.");
        }
    }

    //Returns the root node only without deleting it
    public int peekMax(){
        if(getSize() > 0){
            return heap[0];
        }
        else{
            throw new IllegalStateException("Heap is empty.");
        }
    }

    //Sorts the nodes below i in a way so that the child nodes are smaller
    //Otherwise the largest will be swaped with the node i and then sorted for the node below it as well
    public void maxHeapify(int i){

        if( ((2*i) + 2) > (getCapacity() - 1) ){

            if( ((2*i) + 1) > (getCapacity() - 1) ){
                return;
            }
            else{
                if( heap[(2*i) + 1] > heap[i] ){
                    int largest = heap[(2*i) + 1];
                    heap[(2*i) + 1] = heap[i];
                    heap[i] = largest;
                    return;
                }
                return;
            }

        }

        int leftChild = heap[(2*i) + 1];
        int rightChild = heap[(2*i) + 2];

        if(heap[i] >= leftChild && heap[i] >= rightChild){
            return;
        }
        else{
            int largest = 0;
            if(leftChild >= rightChild){
                largest = leftChild;
                heap[(2*i) + 1] = heap[i];
                heap[i] = largest;
                maxHeapify((2*i) + 1);
            }
            else{
                largest = rightChild;
                heap[(2*i) + 2] = heap[i];
                heap[i] = largest;
                maxHeapify((2*i) + 2);
            }
        }

    }

}
