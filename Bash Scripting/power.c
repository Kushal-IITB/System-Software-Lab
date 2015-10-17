#include    <stdio.h>  // in output it should print stdio.h
 //#include<stdlib>    // should not have this line in output

#    include<math.h>  // in output script should print math.h
#include<stdlib.h>  // in output script should print stdlib.h


 int include_as_part_of_another_var=0;  // should ignore because it is a variable not a header file

void main(){
int n;int m;
printf("enter a number");
scanf("%d",&n);
printf("enter the value of exponent");
scanf("%d",&m);
int p=pow(n,m);
printf("%d power %d is = %d \n",n,m,p);

}
