##take c as input
format rat;
global c = input("Enter constant c : ");
C = zeros(1000,1000);   ##create a 1000x1000 matrix with all zeros
fin = fopen("small.txt");
if fin == -1
    disp("Input file Not Found");
else
    i = 1;
    while ~feof (fin)
        links = fgetl(fin);
        linkarray = strread(links,"%d");
        [m,n] = size(linkarray);
        for k = [1:m]
            C(linkarray(k),i) = 1/m;
        end
        i = i+1;
    end
    fclose(fin);
    A = ones(1000,1000);    ##create a 1000x1000 matrix with all ones
    C = C + (0.15/1000)*A;
    [egVector, egValue] = eig (C);
    Ranks = egVector(:,1)/sum(egVector(:,1));   ##return first column of eigenvector matrix and then normalises it
    [wantedRanks,I] = sort(Ranks,"descend");    ##sorts ranks in ascending order and stores corresponding ids in I
    disp("Top 20 page ranks and their ids are : ");
    disp("\tId   PageRank");
    disp([I(1:20,1),wantedRanks(1:20,1)]);
end