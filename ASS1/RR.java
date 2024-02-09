import java.util.*;

public class RR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        System.out.print("Enter the time quantum: ");
        int tq = sc.nextInt();
        int pid[] = new int[n];
        int bt[] = new int[n];
        int temp_bt[] = new int[n];
        int at[] = new int[n];
        int complete[] = new int[n];
        Queue<Integer> q = new LinkedList<>();  //q- > Queue
        Queue<Integer> gc = new LinkedList<>(); //gc -> Gantt Chart
        Iterator iterator = q.iterator();

        for (int i = 0; i < n; i++) {
            pid[i] = i;
        }

        for (int i = 0; i < n; i++) {   //setting all as not completed
            complete[i] = 0;
        }

        System.out.println("Enter the Burst Time for each process.");
        for (int i = 0; i < n; i++) {
            System.out.print("For Process " + (i + 1) + ":");
            bt[i] = sc.nextInt();
            temp_bt[i] = bt[i];
        }

        System.out.println("Enter the arrival time for each process.");
        for (int j = 0; j < n; j++) {
            System.out.print("For Process " + (j + 1) + ":");
            at[j] = sc.nextInt();
        }

        int wt[] = new int[n];
        int tt[] = new int[n];  
        int ct[] = new int[n];  
        int p = 0;  //p points the lowest arrival time
        int pval = 99999;  
        int added_q[] = new int[n];
        for(int i=0;i<n;i++){   //Initializing empty added q
            added_q[i] = 0;
        }
        for(int i=0;i<n;i++){
            if(at[i]<pval){
                pval=at[i];
                p=i;
            }
        }
        int timer = 0;
        while(timer < at[p]) //Incrementing Timer until the first process arrives
			timer++; 

        q.add(pid[p]);
        added_q[p] = 1;
        
        while(iterator.hasNext()){
            int k=0;
            int temp_name = q.poll();
            int tn = temp_name;
            tn++;
            gc.add(tn);
            while(pid[k]!=temp_name){
                k++;
            }
            if(bt[k]>tq){
                bt[k]-=tq;
                timer+=tq;
            }
            else
            {
                timer+=bt[k];
                bt[k] = 0;
                complete[k] = 1;
                ct[k] = timer;
                tt[k] = ct[k]-at[k];
                wt[k] = tt[k]-temp_bt[k];
            }
 
            while(true){        //it checks ay other process has arived at the time
            pval=timer;
            int flag = 0;
                for(int i=0;i<n;i++){
                    if(at[i] <= pval && complete[i] == 0 && added_q[i] == 0){
                        pval=at[i];
                        p=i;
                        flag=1;
                     }
                }
                if(flag==1){
                    q.add(p);
                    added_q[p] = 1;
                }
                if(flag!=1){
                    break;
                }
            }
            if(complete[k]==0){
                q.add(pid[k]);
            }
        }

        // Printing 
        System.out.println("\nProcesses ||" + " Burst Time ||" + " Arrival Time ||" + " Completion Time ||"  + " Turn-Around Time  ||" + " Waiting Time ");
        float awt = 0;
        float att = 0;
        for (int i = 0; i < n; i++) {
            awt = awt + wt[i];
            att = att + tt[i];
            System.out.println("   " + (pid[i]+1) + "\t  ||\t" + temp_bt[i] + "\t||\t" + at[i] + "\t||\t  " + ct[i] + "\t   ||\t   " + tt[i] + "\t\t||\t " + wt[i]);
        }

        awt = awt / n;
        att = att / n;

        System.out.println("\nAverage waiting time = " + awt);
        System.out.println("\nAverage turn around time = " + att);

        System.out.print("GC: "+gc);
        
    }
}