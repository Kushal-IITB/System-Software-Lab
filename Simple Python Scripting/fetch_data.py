from sys import argv
import csv
import re

#the function total calculates the total marks of a student, the arguement being a list
def total(student):
	result = 0
	for i in range(1,6):
		if student[i]!='NA':
			result+=float(student[i])
	return result

#check if only one arguement is given to the script
try:
	script, filename = argv
except ValueError:
	print ("Give only one arguement to the program")
	quit()

f = open(filename, newline='')
count = 0
#listoflists stores the main data, i.e. without the headers
listoflists=[]
reader = csv.reader(f)
for row in reader:
	if (row[0]=='Sno'):
		header = dict(zip(range(1,8), row[0:7]))	#create a dictionary of the header
	if (re.match("^\d", row[0])):	#check if the row starts with a number
		listoflists.append(row)	
		count += 1
print("numStudents in part a = ",count)
print("numStudents in part b = ",len(listoflists))

#check if the student[6] has 1 or not and filter accordingly
selected = list(filter(lambda student: student[6]=='1', listoflists))

print("numSelected in part c = ",len(selected))

print ("Dictionary in part d")
for key in header:
	print (key,'\t',header[key])

#append the total marks for each student
for student in listoflists:
	student.append(total(student))

header[8] = "Total"
print ("Dictionary in part e")
for key in header:
	print (key,'\t',header[key])
#sort the students by the total column i.e. collumn -1
listoflists.sort(key = lambda x: x[-1]) #x[-1] corresponds to total column

with open ('sorted_marks.csv', 'wt') as fout:
	csvwrite = csv.writer(fout)
	csvwrite.writerow(list(header.values()))
	for student in listoflists:
		csvwrite.writerow(student)
