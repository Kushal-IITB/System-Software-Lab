import re,sys

if (len(sys.argv) != 2):	#checking for arguments
	sys.exit("Incorrect Arguments")
str = str(sys.argv[1])
fin = open(str, 'r')
fout = open('cbseProcessed.csv','w')
for line in fin:
	header = re.match(r'^ROLL NO', line, re.M)			#Checking for first line of header
	if header:
		header = re.sub(r'^ROLL NO +([A-Z]+? +?[A-Z]+?)\.+ +?([A-Z]+?) +?([A-Z]+?) +([A-Z]+?) +([A-Z]+?) +([A-Z]+?) +([A-Z]+?) +([A-Z]+?) +([A-Z]+?) +([A-Z]+?) +([A-Z]+?) +([A-Z]+?) +([A-Z]+?) +([A-Z]+?) +([A-Z]+?) +([A-Z]+?) +([A-Z]+?) +([A-Z]+?)', r'ROLL NO,\1,\2,\3,\4,\5,\6,\7,\8,\9,\10,\11,\12,\13,\14,\15,\16,\17,\18', line.rstrip())
		fout.write(header+',')			#First part of Header Extracted
		break
for line in fin:	#Extracting second part of header
	header = re.sub(r'^([A-Za-z]+) +([A-Za-z0-9]+)\.+ +([A-Za-z]+) +([A-Za-z0-9]+)\.+([A-Za-z0-9]+) +([A-Za-z]+) +([A-Za-z0-9]+)\.+ +([A-Za-z]+) +([A-Za-z0-9]+)\.+ +([A-Za-z]+) +([A-Za-z0-9]+)\.+ +(.*)', r'\1 2A,,,,,,\3 2B,,\3 2C,,\6 2D,,,,,,,,\8 3A,,,,\10 3B,,,,\12', (line.lstrip()).rstrip())
	fout.write(header+'\n')
	break
for line in fin:
	temp1 = line
	temp = re.match( r'^[0-9]+.*EIOP.*', temp1, re.M)		#Checking for presence of EIOP
	matchObj = re.match( r'^[0-9].*', temp1, re.M)		#Checking if line is amongst the ones which contains student data
	temp3 = re.match( r'^ +[0-9].*', temp1, re.M)		#Checking for second part of student data
	if temp :
		temp1 = re.sub(r'^([0-9]+) +([A-Z]+.*?) +([0-9]+) +([0-9]+.*?) +([A-Z]+.*?) +([0-9]+) +([0-9]+.*?) +([A-Z]+.*?) +([0-9]+) +([0-9]+.*?) +([A-Z]+.*?) +([0-9]+) +([0-9]+.*?) +([A-Z]+.*?) +([0-9]+) +([0-9]+.*?) +([A-Z]+.*?) +EIOP.*', r'\1,\2,\3,\4,\5,\6,\7,\8,\9,\10,\11,\12,\13,\14,\15,\16,\17,EIOP,NA', line.rstrip())
		fout.write(temp1+',')
	elif matchObj :
		temp2 = re.sub(r'^([0-9]+) +([A-Z]+.*?) +([0-9]+) +([0-9]+.*?) +([A-Z]+.*?) +([0-9]+) +([0-9]+.*?) +([A-Z]+.*?) +([0-9]+) +([0-9]+.*?) +([A-Z]+.*?) +([0-9]+) +([0-9]+.*?) +([A-Z]+.*?) +([0-9]+) +([0-9]+.*?) +([A-Z]+.*?) +([A-Z]+) +(.*?)', r'\1,\2,\3,\4,\5,\6,\7,\8,\9,\10,\11,\12,\13,\14,\15,\16,\17,\18,\19', line.rstrip())
		fout.write(temp2+',')
	elif temp3 :
		temp4 = re.sub(r' +([0-9]+) +([A-Z]+.*?) +([0-9]+) +([A-Z]+.*?) +([0-9]+) +([A-Z]+.*?) +([0-9]+) +([A-Z]+.*?) +([0-9]+) +([A-Z]+.*?) +([0-9]+) +([A-Z]+.*?) +([0-9]+) +([A-Z]+.*?) +([0-9]+) +([A-Z]+.*?) +([0-9]+) +([A-Z]+.*?) +([0-9]+) +([A-Z]+.*?) +([0-9]+) +([A-Z]+.*?) +([0-9]+) +([A-Z]+.*?) +([0-9]+) +([A-Z]+.*?)', r'\1,\2,\3,\4,\5,\6,\7,\8,\9,\10,\11,\12,\13,\14,\15,\16,\17,\18,\19,\20,\21,\22,\23,\24,\25,\26', line.rstrip())
		fout.write(temp4+'\n')
fout.close()