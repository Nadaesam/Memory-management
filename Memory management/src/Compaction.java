import java.util.ArrayList;

public class Compaction {

    public void compactWorstFitPolicy(WorstFitPolicy worstFitPolicy)
    {
        int totalSize=0;
        ArrayList<Partition>pars=new ArrayList<>();
        for (int i=0; i<worstFitPolicy.partitions.size() ; i++){
            Partition partition=worstFitPolicy.partitions.get(i);
            if (partition.isEmpty()){
                totalSize+=partition.getPartitionSize();
            }
            else {pars.add(partition);}
        }

        if(totalSize!=0)
        {
            worstFitPolicy.partitions=pars;
            worstFitPolicy.processes=worstFitPolicy.notAllocatedProcess;
            worstFitPolicy.notAllocatedProcess=new ArrayList<>();

            String name="partition"+Integer.toString(worstFitPolicy.partitionsIdx+1);
            worstFitPolicy.addPartition( new Partition(name,totalSize));

            worstFitPolicy.manageMemory();
        }
    }


    public void compactFirstFitPolicy(FirstFitPolicy firstFitPolicy)
    {
        int totalSize=0;
        ArrayList<Partition>pars=new ArrayList<>();
        for (int i=0; i<firstFitPolicy.partitions.size() ; i++){
            Partition partition=firstFitPolicy.partitions.get(i);
            if (partition.isEmpty()){
                totalSize+=partition.getPartitionSize();
            }
            else {pars.add(partition);}
        }

        if(totalSize!=0)
        {

            firstFitPolicy.partitions=pars;
            firstFitPolicy.processes=firstFitPolicy.notAllocatedProcess;
            firstFitPolicy.notAllocatedProcess=new ArrayList<>();

            String name="partition"+Integer.toString(++firstFitPolicy.partitionsIdx);
            firstFitPolicy.partitions.add(firstFitPolicy.partitions.size(), new Partition(name,totalSize));

            firstFitPolicy.manageMemory();
        }
    }

    public void compactBestFitPolicy(BestFitPolicy bestFitPolicy)
    {
        int totalSize=0;
        ArrayList<Partition>pars=new ArrayList<>();
        for (int i=0; i<bestFitPolicy.partitions.size() ; i++){
            Partition partition=bestFitPolicy.partitions.get(i);
            if (partition.isEmpty()){
                totalSize+=partition.getPartitionSize();
            }
            else {pars.add(partition);}
        }

        if(totalSize!=0)
        {

            bestFitPolicy.partitions=pars;
            bestFitPolicy.processes=bestFitPolicy.notAllocatedProcess;

            bestFitPolicy.notAllocatedProcess=new ArrayList<>();

            String name="partition"+Integer.toString(++bestFitPolicy.partitionsIdx);
            bestFitPolicy.partitions.add(bestFitPolicy.partitions.size(), new Partition(name,totalSize));

            bestFitPolicy.manageMemory();
        }
    }

}





