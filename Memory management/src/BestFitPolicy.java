import java.util.ArrayList;

public class BestFitPolicy {
    ArrayList<Process> processes ;
    ArrayList<Partition> partitions ;
    protected int partitionsIdx;
    protected ArrayList<Process> notAllocatedProcess=new ArrayList<Process>();

    public  BestFitPolicy(ArrayList<Process> processes,ArrayList<Partition> partitions){
        this.processes=processes;
        this.partitions=partitions;
        partitionsIdx=partitions.size()-1;
    }

    public void manageMemory(){
        for (Process process : processes) {
            int bestPartitionIdx=getBestPartitionIdx(process.getProcessSize());
            if (bestPartitionIdx==-1){
                notAllocatedProcess.add(process);
            }
            else{
                if (process.getProcessSize() < partitions.get(bestPartitionIdx).getPartitionSize()) {
                    int newPartitionSize = partitions.get(bestPartitionIdx).getPartitionSize() - process.getProcessSize();
                    String newPartitionName = "partition" + Integer.toString(++partitionsIdx);
                    partitions.add(bestPartitionIdx+1,new Partition(newPartitionName, newPartitionSize));
                }
                partitions.get(bestPartitionIdx).setPartitionSize(process.getProcessSize());
                partitions.get(bestPartitionIdx).setProcessName(process.getProcessName());
            }
        }

    }

    public int getBestPartitionIdx(int processSize){
        int bestSize=Integer.MAX_VALUE;
        int bestIdx=-1;
        for (int i=0 ; i< partitions.size()  ;i++){
            if (partitions.get(i).isEmpty()&&partitions.get(i).getPartitionSize()>=processSize){
                if((partitions.get(i).getPartitionSize()-processSize)<bestSize){
                    bestSize=(partitions.get(i).getPartitionSize()-processSize);
                    bestIdx=i;
                }
            }
        }
        return bestIdx;
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


}
