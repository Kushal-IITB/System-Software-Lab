%Group 07 - Inlab1

%Global variables declaration
global n = 12;
global A = 2;

%desired precision of answer
epsilon = 10^(-11);

x0 = input("Give x0: ");

%checking whether input will cause overflow
while abs(x0) < 0.001
	x0 = input("Enter mod(x0) >= 0.001 to avoid overflow : ");
end

%calculates next value in the series
function next = refine(prev)
	global n;
	global A;	
	next = (1/n)*((n-1)*prev + A/(prev^(n-1)));
endfunction;

xk = x0;
x = [x0]; %x will store the series

while abs(xk^n - A) > epsilon		%checking precision
	xk = refine(xk);
	x = [x xk];			%appending to vector
end;

printf("%.10f\n",xk);			%printing answer till 10th decimal position

%plotting graph
plot([0:length(x)-1],x,'LineWidth',2);
title("xk vs k","FontSize",35);
xlabel("k","FontSize",20);
ylabel("xk","FontSize",20);
grid on;

