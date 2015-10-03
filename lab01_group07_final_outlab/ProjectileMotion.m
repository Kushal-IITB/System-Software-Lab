%Group 07
%Out Lab 01

%declaration of global variables
global x0 = input("Enter x0: ");
global y0 = input("Enter y0: ");
global x = input("Enter xf: ");
global y = input("Enter yf: ");
global v = input("Enter v: ");
global r = 0.03;
global k = 0.05;
global g = 9.80;

%making sure that orientaion is such that throw is towards the board
if x<x0
	temp = x;
	x = x0;
	x0 = temp;
end

%calculates the relative position of hit w.r.t. dart board
%0 = below the dart board
%1 = in between
%2 = above the dart board
function x = isInRange(val)
	global y; global r;
	if val >= y-r && val <= y+r
		x = 1;
 	elseif val< y-r
		x = 0;
	else x=2;
	end
endfunction

%calculates y1 for given theta and other constants
function y1 = getY1(theta)
	global x; global x0; global y0; global v; global g;
	t = (x-x0)/(v*cosd(theta));
	y1 = v*t*sind(theta)-0.5*g*(t^2) + y0;
endfunction

%calculates y2 for given theta and other constants
function y2 = getY2(theta)
	global x; global x0; global y0; global v; global g; global k;
	OneMinusExp = ((x-x0)*k)/(v*cosd(theta));
	t=(-1/k)*log(1-OneMinusExp);
	y2= v*sind(theta)*OneMinusExp/k+g*(OneMinusExp-k*t)/(k^2)+y0;
endfunction

%calculates y3 for given theta and other constants
function y3 = getY3(phi)
	global k; global g; global x0; global y0; global v; global x;
	y3 = (1/k)* log ( abs((cos(sqrt(g/k) * ((exp(k*(x-x0))-1)/(v*cosd(phi))) - atan(sqrt(k/g)*v*sind(phi) ) ) ) / cos (atan(sqrt(k/g)*v*sind(phi) ) ) ) )  + y0;
endfunction

%computes the answer for case 1 by solving quadratic equation
function range = case1()
	global y; 	global y0; 	global x; 	global x0;	global g; 	global r; 	global v;
	range = [];
	a = 0.5*g/(v^2)*((x-x0)^2);
	b = x0-x;
	c0 = y-y0+a;
	
	c = c0-r;
	if b^2 >= 4*a*c
		range = [range (0-b-sqrt(b^2-4*a*c))/(2*a)];
		range = [range (0-b+sqrt(b^2-4*a*c))/(2*a)];
	end
	
	c = c0+r;
	if b^2 >= 4*a*c
		range = [range (0-b-sqrt(b^2-4*a*c))/(2*a)];
		range = [range (0-b+sqrt(b^2-4*a*c))/(2*a)];
	end
	
	range = sort(range);
	range = atand(range);
endfunction

%this function computes the range of angles for which hit is success. Precision is determined by 'increment'
%it gauranteed that start ccorresponds to < y-r and last corresponds to > y+r
function range = getRange(start, last, increment, f)
	range = [];
	state = isInRange(f(start));
	for angle = start : increment : last
		y = f(angle);
		if state ~= isInRange(y)
			if state ==0 || state==2
				if isInRange(y)==1
					range=[range angle];
				else range= [range angle-increment angle];
					##disp(range);
				end	
			elseif state==1
				range = [range angle-increment];
			end
		state = isInRange(y);
		end
	end
endfunction

%vector of function handles
f = {(@getY1), (@getY2), (@getY3)};

%the main program logic
for i = [1:length(f)]
	theta = [];
	if i == 1
		%in case1, slve using quadratic equation only
		theta = case1();
	else
		%startLoop denotes the the angle from which we will start checking.
		if y>=y0
			startOfLoop=0;
		else
			startOfLoop=-82.999;
			%limit has been taken to be 82.999 to avoid corner (physically)weird occurences.
		end
		%"crude" determination of angles
		theta = getRange(startOfLoop, 89.999, 0.1, f{i});
	
	
		if length(theta)>=2
			%"precise" determination of angles using previously computed "crude" angles
			temp = getRange(theta(1)-0.1, theta(2)+0.1, 0.01, f{i});
			if length(temp)==2
				theta(1:2) = temp;
			elseif length(temp)==4
				theta(1:2) = temp(1:2);
				theta = [theta temp(3:4)];
			end
		end
	
		if length(theta)==4	
			disp(length(getRange(theta(3)-0.1, theta(4)+0.1, 0.01, f{i})));
			theta(3:4) = getRange(theta(3)-0.1, theta(4)+0.1, 0.01, f{i})(1:2);
			% no. of elements will always be <= 4 
		end
		
	end
	
	%printing
	%display the ranges
	printf("Case "); for j=[1:i] printf("I") end; printf(":\n");
	if length(theta)==0
		printf("No Solution\n");
	end
	if length(theta)>=2
		printf("Dart can hit for the angles between [%.2f, %.2f]\n", theta(1), theta(2));
	end
	if length(theta)>=4
		printf("Dart can hit for the angles between [%.2f, %.2f]\n", theta(3), theta(4));
	end
	printf("....\n");
	
end;
