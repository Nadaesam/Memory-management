import java.util.ArrayList;

public class WorstFitPolicy {
    protected ArrayList<Process> processes =new ArrayList<>();
    protected ArrayList<Partition> partitions=new ArrayList<>();
    protected ArrayList<Process> notAllocatedProcess=new ArrayList<Process>();
    protected int partitionsIdx=-1;

    public  WorstFitPolicy(ArrayList<Process> processes,ArrayList<Partition> partitions){
        this.processes=processes;
        for (Partition partition : partitions) {
            addPartition(partition);
        }
    }

    public void addPartition(Partition p){
        partitionsIdx++;
        if(partitions.isEmpty()){partitions.add(p);}
        else
        {
            int i;
            for( i=0 ; i<partitions.size() ; i++){
                if(partitions.get(i).getPartitionSize()>p.getPartitionSize()){
                    partitions.add(i,p);
                    break;
                }
            }
            if (i==partitions.size()){partitions.add(p);}
        }
    }


    public void manageMemory(){
        for (Process process : processes) {
            Partition maxPartition = getMaxPartition();
            if (maxPartition != null && process.getProcessSize() <= maxPartition.getPartitionSize()) {
                if (process.getProcessSize() < maxPartition.getPartitionSize()) {
                    int newPartitionSize = maxPartition.getPartitionSize() - process.getProcessSize();
                    String newPartitionName = "partition" + Integer.toString(partitionsIdx + 1);
                    addPartition(new Partition(newPartitionName, newPartitionSize));
                }
                maxPartition.setPartitionSize(process.getProcessSize());
                maxPartition.setProcessName(process.getProcessName());
                partitions.remove(maxPartition);
                partitionsIdx--;
                addPartition(maxPartition);

            } else if (process != null) {
                notAllocatedProcess.add(process);
            }   ///no empty or no enough capacity
        }
    }

    public void displayPartitions(){
        for (Partition partition : partitions) {
            System.out.println(partition.getPartitionName() + " (" +partition.getPartitionSize()+" KB) "+"=> "+partition.getProcessName());
        }
    }

    public void displayNotAllocatedPartition(){
        for ( Process process : notAllocatedProcess) {
            System.out.println(process.getProcessName()+ " can not be allocated");
        }
    }

    public Partition getMaxPartition(){
        for(int i=partitions.size()-1 ; i>=0 ; i--){
            if (partitions.get(i).isEmpty()){
                return partitions.get(i);
            }
        }
        return null;
    }

}


