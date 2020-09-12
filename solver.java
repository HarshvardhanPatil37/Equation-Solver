import java.util.*;
import java.lang.*;
import java.math.*;
class simplify 
{
     public static double calculate(String F)
     {
         Scanner sc = new Scanner(System.in);
         int k = 0, m = 0, s = 0, t = 0, flag = 0, flag1 = 0, flag2 = 0, div = 10;
         double ans = 0;
         double arr[] = new double[100];
         char ope[] = new char[100];
         String f, x;

         //System.out.println("enter a expression");
         f = F;//taking function input
         //System.out.println("enter x value ");
        
         //f=f.replace("x",String.valueOf(X));
         //f=f.replace("y",String.valueOf(Y));
         f = f.replace("sin", "s");
         f = f.replace("cos", "c");
         f = f.replace("tan", "t");
         f = f.replace("exp", "e");
         f = f.replace("log", "l");
         //System.out.println("f="+f);
         //f=check(f,x);
         for (int y = 0; y < f.length(); y++) {
             flag1 = 0;
             if ((int) f.charAt(y) >= 37 && (int) f.charAt(y) <= 47 && f.charAt(y) != '.' || f.charAt(y) == 's' || f.charAt(y) == 'c' || f.charAt(y) == 't' || f.charAt(y) == 'e' || f.charAt(y) == 'l' || f.charAt(y) == '^') {
                 ope[k] = f.charAt(y);
                 k++;
                 flag = 0;
                 flag2 = 0;
             } else if ((int) f.charAt(y) >= 48 && (int) f.charAt(y) <= 57 || f.charAt(y) == 'x' || f.charAt(y) == '.') {
                 if (f.charAt(y) == '.') {
                     flag2 = 1;
                     flag1 = 1;
                     flag = 0;
                     div = 10;
                 }
                 if (flag != 0)//executes when previous element was also operand
                 {
                     if (flag2 == 1) {
                         arr[k - 1] = (double) (arr[k - 1] + ((double) ((int) f.charAt(y) - 48) / div));
                         div = div * 10;
                         flag1 = 1;
                     } else {
                         arr[k - 1] = (arr[k - 1] * 10) + ((int) f.charAt(y) - 48);
                         flag1 = 1;
                     }
                 }
                 if (flag1 == 0)//exectue when previous element is operator
                 {
                     if (f.charAt(y) - 48 == 0) {
                         arr[k] = 0.0000000000000001;
                     } else {
                         arr[k] = (int) f.charAt(y) - 48;
                     }
                     k++;
                 }
                 flag++;
             }
         }
         ans = bracket(ope, arr, f.length());
         //System.out.println("\n ans="+ans);
         return ans;
     }

     public static double bracket(char ope[], double arr[], int length) {
         int s = 0, set = 0, count = 0, flag = 0,even=0;
         double ans = 0, rep;
         for(int y=0;y<length;y++)
         {
             if(ope[y]=='('||ope[y]==')')
             {even++;}
         }
         if(even%2!=0)
         {
             //brac_flag=1;
             return 0;
         }
         for (int y = 0; y <=length; y++)//for solving brackets
         {
             flag = 0;
             if (ope[y] == '(') {
                count=1;
                 for (int t = y + 1; t < length; t++)//for finding corresponding closing bracket
                 {

                     if (ope[t] == '(') {
                         count++;
                         flag++;
                     }

                     if (ope[t] == ')') {
                         count--;
                     }
                     if(count==0)
                     {
                         break;
                     }
                 }

                 if (count == 0) {
                     for (int x = y; x < length; x++)//for finding closing brackets
                     {
                         if (ope[x] == ')') {
                             flag--;
                         }
                         if (ope[x] == ')' && flag == -1) {

                             char op[] = new char[100];//stroing operators to be send for recurtion
                             double ar[] = new double[100];//storing operands to be send for recurtion
                             int k = 0;

                             ope[y] = 0;
                             ope[x] = 0;


                             for (int i = y + 1; i < x; i++)//storing values to be sent for recurtion
                             {
                                 if (arr[i] != 0.0) {
                                     ar[k] = arr[i];
                                     k++;
                                 } else if (ope[i] != 0) {
                                     op[k] = ope[i];
                                     k++;
                                 }

                             }

                             rep = bracket(op, ar, (x - y));//recurtion
                             if (rep == 0) {
                                 rep = 0.0000000000000001;
                             }
                             for (int i = y; i < x; i++) {
                                 arr[i] = 0;
                                 ope[i] = 0;
                             }
                             set = y;
                             s = x;

                             arr[set + 1] = rep;
                             if (set != 0) {
                                 if (ope[set - 1] != '/' && ope[set - 1] != '*' && ope[set - 1] != '+' && ope[set - 1] != '-' && ope[set - 1] != '^') {
                                     ope[set] = '*';
                                 }
                             }
                             x = length;
                         }
                     }
                 }
                 else {
                     //brac_flag = 1;
                     return count;
                 }
             }
         }
         char op[] = new char[100];
         double ar[] = new double[100];
         int k = 1;

         for (int y = 0; y < length; y++) {
             if (ope[y] == 's') {
                 s = y;
                 ans = Math.sin(arr[s + 2]);
                 if (ans == 0) {
                     ans = 0.0000000000000001;
                 }
                 arr[s] = ans;
                 arr[s + 2] = 0;
                 ope[s] = 0;
                 ope[s + 1] = 0;
                 y = y - 1;
             }
         }
         for (int y = 0; y < length; y++) {
             if (ope[y] == 'c') {
                 s = y;
                 ans = Math.cos(arr[s + 2]);
                 if (ans == 0) {
                     ans = 0.0000000000000001;
                 }
                 arr[s] = ans;
                 arr[s + 2] = 0;
                 ope[s] = 0;
                 ope[s + 1] = 0;
                 y = y - 1;
             }
         }
         for (int y = 0; y < length; y++) {
             if (ope[y] == 't') {
                 s = y;
                 ans = Math.tan(arr[s + 2]);
                 if (ans == 0) {
                     ans = 0.0000000000000001;
                 }
                 arr[s] = ans;
                 arr[s + 2] = 0;
                 ope[s] = 0;
                 ope[s + 1] = 0;

                 y = y - 1;
             }
         }
         for (int y = 0; y < length; y++) {
             if (ope[y] == 'e') {
                 s = y;
                 ans = Math.exp(arr[s + 2]);
                 if (ans == 0) {
                     ans = 0.0000000000000001;
                 }
                 arr[s] = ans;
                 arr[s + 2] = 0;
                 ope[s] = 0;
                 ope[s + 1] = 0;
                 y = y - 1;
             }
         }
         for (int y = 0; y < length; y++) {
             if (ope[y] == 'l') {
                 s = y;
                 ans = Math.log(arr[s + 2]);
                 if (ans == 0) {
                     ans = 0.0000000000000001;
                 }
                 arr[s] = ans;
                 arr[s + 2] = 0;
                 ope[s] = 0;
                 ope[s + 1] = 0;
                 y = y - 1;
             }
         }


         k=1;
         int temp;
         for(int i=0;i<length;i++)//arrangment /dealing with signs
         {
             if(ope[i]!=0)
             {
                 temp=i;
                 //System.out.println("temp="+temp);

                 if(ope[i]=='-')
                 {
                     while(arr[temp]==0)
                     {
                         temp++;
                     }
                     //System.out.println("inside for and temp="+temp);
                     if(ope[i+2]=='^')
                     {
                         arr[temp]=Math.pow(arr[temp],arr[temp+2]);
                         ope[temp+1]=0;
                         arr[temp+2]=0;
                     }
                     if(i==0)
                     {
                         arr[temp]=-1*arr[temp];
                         op[k]=0;
                         continue;
                     }
                     else
                     {
                         arr[temp]=-1*arr[temp];
                         op[k]='+';
                     }
                     k=k+2;
                 }
                 else
                 {
                     op[k]=ope[i];
                     k=k+2;
                 }
             }
         }
         ope = op;

         k = 0;
         for (int i = 0; i < length; i++) {
             if (arr[i] != 0) {
                 if (arr[i] == 0.0000000000000001) {
                     ar[k] = 0;
                 } else {
                     ar[k] = arr[i];
                 }
                 k = k + 2;
             }
         }
         arr = ar;

         for (int y = 0; y < length; y++) {
             if (ope[y] == '^') {
                 s = y;
                 ans = Math.pow(arr[s - 1], arr[s + 1]);
                 arr[s - 1] = ans;
                 ope[s] = ope[s + 2];
                 while (s < length) {
                     ope[s] = ope[s + 2];
                     arr[s + 1] = arr[s + 3];
                     s = s + 2;
                 }
                 y = y - 1;
             }
         }
         for (int y = 0; y < length; y++) {
             if (ope[y] == '/') {
                 s = y;
                 ans = arr[s - 1] / arr[s + 1];
                 arr[s - 1] = ans;
                 ope[s] = ope[s + 2];
                 while (s < length) {
                     ope[s] = ope[s + 2];
                     arr[s + 1] = arr[s + 3];
                     s = s + 2;
                 }
                 y = y - 1;
             }
         }

         for (int y = 0; y < length; y++) {
             if (ope[y] == '*') {
                 s = y;
                 ans = arr[s - 1] * arr[s + 1];
                 arr[s - 1] = ans;
                 ope[s] = ope[s + 2];
                 while (s < length) {
                     ope[s] = ope[s + 2];
                     arr[s + 1] = arr[s + 3];
                     s = s + 2;
                 }
                 y = y - 1;
             }
         }

         for (int y = 0; y < length; y++) {
             if (ope[y] == '-') {
                 s = y;
                 ans = arr[s - 1] - arr[s + 1];
                 arr[s - 1] = ans;
                 ope[s] = ope[s + 2];
                 while (s < length) {
                     ope[s] = ope[s + 2];
                     arr[s + 1] = arr[s + 3];
                     s = s + 2;
                 }
                 y = y - 1;
             }
         }

         for (int y = 0; y < length; y++) {
             if (ope[y] == '+') {
                 s = y;
                 ans = arr[s - 1] + arr[s + 1];
                 arr[s - 1] = ans;
                 ope[s] = ope[s + 2];
                 while (s < length) {
                     ope[s] = ope[s + 2];
                     arr[s + 1] = arr[s + 3];
                     s = s + 2;
                 }
                 y = y - 1;
             }
         }

         return arr[0];
     }
    }   
    
    
