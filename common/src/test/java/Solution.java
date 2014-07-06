import java.util.Scanner;
public class Solution {
    public static void main(String args[] ) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] input_array = new int[n];
        for(int i = 0; i < n; i++){
            input_array[i] = sc.nextInt(); 
        }
        int k = sc.nextInt();
        findConsecutive(input_array, k);
    }
    
    public static void findConsecutive(int[] a, int k){
        boolean found = false;
        int start = 0;
        int end = a.length;
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        while(!found){
            for(int i = start;i < end; i++){
               sum += a[i];
               sb.append(a[i]);
               sb.append("\n");
               if(sum == k){
                   found = true;
                   break;
               }else if(sum > k){
                   sb.setLength(0);
                   sb.append(a[i]);
                   sb.append("\n");
                   sum = a[i];
               }    
            }
            if(!found && start == end){
                break;
            }else if(!found){
                start++;
                sb.setLength(0);
                sum = 0;
            }
        }
        if(!found){
            System.out.println(0);
        }else{
            System.out.println(sb.toString());
        }
    }
}
