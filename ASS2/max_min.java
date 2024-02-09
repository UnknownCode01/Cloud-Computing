//contributed by Brijit Adak
import java.util.Scanner;
import java.util.Arrays;
public class max_min{
public static void main(String[] args){
Scanner sc = new Scanner(System.in);

int nT,nM;      //number of tasks , number of machines
System.out.print("Enter number of machines and tasks : ");
nM = sc.nextInt();
nT = sc.nextInt();

int maxMin[][] = new int[nM][nT];

System.out.print("Fill Data\n");
for(int i=0;i<nM;i++)
for(int j=0;j<nT;j++){
maxMin[i][j]= sc.nextInt();
}
// Show the data 
System.out.println("Original Data");
System.out.println("VM\tT1\tT2\tT3");

for(int i=0;i<nM;i++){
System.out.print("M"+(i+1)+"\t");
for(int j=0;j<nT;j++){
System.out.print(maxMin[i][j]+"\t");
}
System.out.println();
}

int done[] = new int[nT];       //this array indicates which task has been completed -1 -> not completed 0->completed 
for(int i=0;i<nT;i++){
    done[i]=-1;
}

int ptr[] = new int[nT];        //This array holds the index of the smaller execution time for every task
int min[] = new int[nT];         //This array holds the value of the smaller execution time for every task
int resultTask[] = new int[nT];
int resultMachine[]= new int[nT];
int resultTime[]= new int[nT];

for(int k=0;k<nT;k++){
for(int i=0;i<nT;i++){
    if(done[i]==0){
        continue;
    }
    min[i] = maxMin[0][i];
}

for(int j=0;j<nT;j++){
    if(done[j]==0){
        continue;
        }
    for(int i=0;i<nM;i++){
        if(maxMin[i][j]<=min[j]){
            min[j]=maxMin[i][j];
            ptr[j]=i; 
        }
    }
}

int p=0;        //p points to the max task time in the task array
int p1=0;       //p1 stores the index
for(int i=0;i<nT;i++){
    if(done[i]==0){
        continue;
        }
    if(min[i]>=p){
        p=min[i];
        p1=i;
    }
}
resultTask[k]=p1;
resultMachine[k]=ptr[p1];
resultTime[k]=maxMin[ptr[p1]][p1];
done[p1]=0;
for(int j=0;j<nT;j++){
    if(done[j]==-1){
        maxMin[ptr[p1]][j]+=p;
    }
}
}

//printing answer
System.out.print("\nScheduled Task are :\n");
for(int i=0;i<nT;i++){
System.out.print("Task "+(resultTask[i]+1)+" Runs on Machine "+(resultMachine[i]+1)+" with Time "+(resultTime[i])+" units\n");
}
System.out.print("Makespan = "+Arrays.stream(resultTime).max().getAsInt());

}
}