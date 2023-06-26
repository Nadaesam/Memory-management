import java.util.ArrayList;
import java.util.Scanner;

public class GUI {
    public void display(){
        ArrayList<Process> processes = new ArrayList<Process>();
        ArrayList<Partition> partitions = new ArrayList<Partition>();
        Scanner sc1=new Scanner(System.in);
        Scanner sc2=new Scanner(System.in);
        Scanner sc3=new Scanner(System.in);


        System.out.println("Enter number of partitions: ");
        int n1=sc1.nextInt();

        for(int i=0 ; i<n1 ; i++){
            System.out.println("Partition name and its size:");
            String name =sc2.nextLine();  //sc.nextInt()
            int size=sc3.nextInt();
            Partition partition=new Partition(name,size);
            partitions.add(partition);
        }

        System.out.println();
        System.out.println("Enter number of processes:");
        int n2= sc1.nextInt();

        for(int i=0 ; i<n2 ; i++){
            System.out.println("Process name and its size:");
            String name= sc2.nextLine();
            int size =sc3.nextInt();
            Process process=new Process(name,size);
            processes.add(process);
        }

        System.out.println();
        System.out.println("Select the policy you want to apply:");
        System.out.println("1. First fit");
        System.out.println("2. Worst fit");
        System.out.println("3. Best fit");
        System.out.println("Select policy:");
        int option=sc1.nextInt();

        if(option==1){
            FirstFitPolicy firstFitPolicy=new FirstFitPolicy(processes,partitions);
            firstFitPolicy.manageMemory();
            firstFitPolicy.displayPartitions();
            firstFitPolicy.displayNotAllocatedPartition();
            System.out.println();

            System.out.println("Do you want to compact? 1.yes 2.no");
            option=sc2.nextInt();
            if(option==1){
                Compaction compaction=new Compaction(); compaction.compactFirstFitPolicy(firstFitPolicy);
                firstFitPolicy.displayPartitions();
                firstFitPolicy.displayNotAllocatedPartition();
            }
        }

        else if(option==2){
            WorstFitPolicy worstFitPolicy=new WorstFitPolicy(processes,partitions);
            worstFitPolicy.manageMemory();
            worstFitPolicy.displayPartitions();
            worstFitPolicy.displayNotAllocatedPartition();
            System.out.println();

            System.out.println("Do you want to compact? 1.yes 2.no");
            option=sc2.nextInt();
            if(option==1){
                Compaction compaction=new Compaction(); compaction.compactWorstFitPolicy(worstFitPolicy);
                worstFitPolicy.displayPartitions();
                worstFitPolicy.displayNotAllocatedPartition();
            }
        }

        else if(option==3){
            BestFitPolicy bestFitPolicy=new BestFitPolicy(processes,partitions);
            bestFitPolicy.manageMemory();
            bestFitPolicy.displayPartitions();
            bestFitPolicy.displayNotAllocatedPartition();
            System.out.println();

            System.out.println("Do you want to compact? 1.yes 2.no");
            option=sc2.nextInt();
            if(option==1){
                Compaction compaction=new Compaction(); compaction.compactBestFitPolicy(bestFitPolicy);
                bestFitPolicy.displayPartitions();
                bestFitPolicy.displayNotAllocatedPartition();
            }
        }
    }
}
