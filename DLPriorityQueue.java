// Shourya Nundy (251282929) Assignment 3
public class DLPriorityQueue<T> implements PriorityQueueADT<T>
{
    // Instance variables
    private DLinkedNode<T> front;
    private DLinkedNode<T> rear;
    private int count;

    // Default Constructor
    public DLPriorityQueue()
    {
        front = null;
        rear = null;
        count = 0;
    }

    // Method to add new element to queue along with its priority
    public void add(T dataItem, double priority)
    {
        // Creating new node
        DLinkedNode<T> newNode = new DLinkedNode<>(dataItem, priority);
        // Checking if queue is empty
        if (isEmpty())
        {
            front = newNode;
            rear = newNode;
        }
        // Checking if new node has higher priority than front
        else if(front.getPriority() > priority)
        {
            newNode.setNext(front);
            front.setPrev(newNode);
            front = newNode;
        }
        else {
            // setting current node as front
            DLinkedNode<T> current = front;
            // Loop to find where the new node has to be inserted based on its priority
            while (current != null && current.getPriority() <= priority)
                current = current.getNext();
            // If current is null, the node is inserted at the end
            if (current == null)
            {
                newNode.setPrev(rear);
                rear.setNext(newNode);
                rear = newNode;
            }
            // Node is added to the front if it has high priority
            else if (current == front)
            {
                newNode.setNext(front);
                front.setPrev(newNode);
                front = newNode;
            }
            // Inserting the node in the queue in accordance to its priority
            else
            {
                newNode.setPrev((current.getPrev()));
                newNode.setNext(current);
                current.getPrev().setNext(newNode);
                current.setPrev(newNode);
            }
        }
        count++; // Counting nodes
    }

    // Function to update the priority of elements already in the queue
    @Override
    public void updatePriority(T dataItem, double newPriority) throws InvalidElementException {
        DLinkedNode<T> current = front;
        boolean found = false;
        // Looking for the data item in the queue
        while (current != null) {
            if (current.getDataItem().equals(dataItem)) {
                found = true;
                break;
            }
            current = current.getNext();
        }
        // Throwing exception if not found
        if (!found) {
            throw new InvalidElementException("Data item not found in the priority queue.");
        }
        current.setPriority(newPriority); // Setting new priority
        if (current != front && newPriority < current.getPrev().getPriority()) {
            // While loop to change the order based on different conditions
            // Looping till the correct position is achieved
            while (current.getPrev() != null && newPriority < current.getPrev().getPriority()) {
                DLinkedNode<T> prev = current.getPrev();
                DLinkedNode<T> next = current.getNext();
                if (prev.getPrev() != null)
                    prev.getPrev().setNext(current);
                else
                    front = current;
                current.setPrev(prev.getPrev());
                current.setNext(prev);
                prev.setPrev(current);
                prev.setNext(next);
                if (next != null)
                    next.setPrev(prev);
                else
                    rear = prev;
            }
        }
        // Checking if current node is not rear and has lower priority
        else if (current != rear && newPriority > current.getNext().getPriority()) {
            while (current.getNext() != null && newPriority > current.getNext().getPriority()) {
                // Swapping nodes
                DLinkedNode<T> prev = current.getPrev();
                DLinkedNode<T> next = current.getNext();
                if (next.getNext() != null)
                    next.getNext().setPrev(current);
                else
                    rear = current;
                current.setNext(next.getNext());
                current.setPrev(next);
                next.setNext(current);
                next.setPrev(prev);
                if (prev != null)
                    prev.setNext(next);
                else
                    front = next;
            }
        }
    }

    // Method to remove and return data item with smallest priority
    public T removeMin() throws EmptyPriorityQueueException {
        // Checking if queue is empty and throwing exception if needed
        if (isEmpty())
            throw new EmptyPriorityQueueException("Empty Queue");
        T dataItem = front.getDataItem();
        front = front.getNext();
        if (front == null)
            rear = null;
        else
            front.setPrev(null);
        count--;
        return dataItem;
    }

    // Method to check if queue is empty
    public boolean isEmpty()
    {
        return count == 0;
    }

    // Method to find size of queue
    public int size()
    {
        return count;
    }

    // Method to print data
    public String toString()
    {
        DLinkedNode<T> current = front;
        String str = "";
        while (current != null) {
            str += current.getDataItem();
            current = current.getNext();
        }
        return str;
    }

    // Method to return rear element of the queue
    public DLinkedNode<T> getRear()
    {
        return rear;
    }
}
