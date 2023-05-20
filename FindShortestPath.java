// Shourya Nundy (251282929) Assignment 3
import java.io.FileNotFoundException;

public class FindShortestPath
{
    public static void main(String[] args)
    {
        try {
            // Checking input
            if (args.length < 1) throw new Exception("No input file specified");
            String dungeonFileName = args[0];
            // Creating an object of the class Dungeon
            Dungeon dungeon = new Dungeon(dungeonFileName);
            // Creating an empty priority queue
            DLPriorityQueue<Hexagon> queue = new DLPriorityQueue<>();
            // Get the starting chamber from the Dungeon object
            Hexagon start = dungeon.getStart();
            // Adding the starting chamber to the queue with priority 0
            start.setDistanceToStart(0);
            queue.add(start, 0);
            start.markEnqueued();
            boolean exit = false;
            // While queue is not empty and the exit has not been found i.e. false
            while (!queue.isEmpty() && !exit) {
                // Removing the chamber with the smallest priority
                Hexagon current = queue.removeMin();
                current.markDequeued();
                // Exiting the loop if current chamber is the exit
                if (current.isExit()) {
                    exit = true;
                    break;
                }
                // Checking if current chamber has a dragon
                if (current.isDragon())
                    continue;
                // Checking if neighbouring chambers have a dragon
                boolean check=false;
                for(int i=0; i<6; i++)
                {
                    if (current.getNeighbour(i) != null && current.getNeighbour(i).isDragon()) {
                        check = true;
                        break;
                    }
                }
                if(check==true)
                    continue;
                // Loop to check multiple conditions
                for (int i=0; i<6;i++) {
                    // Checking if the neighbouring chamber is null or a wall
                    Hexagon neighbour = current.getNeighbour(i);
                    if (neighbour == null || neighbour.isWall())
                        continue;
                    // Checking if neighbour chamber is marked dequeued
                    if (neighbour.isMarkedDequeued())
                        continue;
                    // Calculating the distance from the initial chamber to the neighbour through the current chamber
                    int nbrDistance = current.getDistanceToStart() + 1;
                    // Checking if the previous distance calculated for the neighbour was incorrect
                    if (nbrDistance < neighbour.getDistanceToStart()) {
                        neighbour.setDistanceToStart(nbrDistance);
                        neighbour.setPredecessor(current);
                        // Updating priority if already in queue
                        if (neighbour.isMarkedEnqueued()) {
                            queue.updatePriority(neighbour, nbrDistance + neighbour.getDistanceToExit(dungeon));
                        }
                        // Otherwise, adding it to the queue with its priority
                        else {
                            queue.add(neighbour, nbrDistance + neighbour.getDistanceToExit(dungeon));
                            neighbour.markEnqueued();
                        }
                    }
                }
            }
            int dist = dungeon.getExit().getDistanceToStart() + 1;
            // Printing the result
            if (exit == true) {
                System.out.println("Path of length " + dist + " found");
            } else {
                System.out.println("No path found");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

