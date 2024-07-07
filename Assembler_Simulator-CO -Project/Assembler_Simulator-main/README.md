The code starts by importing the sys module and defining some variables for registers, flags, and the program counter (pc).

It then reads the instructions from standard input (sys.stdin) and stores them in a list called ins. The instructions are stripped of leading/trailing whitespace.

The code initializes a memory array called MEM with 128 elements, each initialized to a 16-bit binary string "0000000000000000".

Next, there are several conversion functions defined:

b_to_d_7(binary): Converts a 7-bit binary string to a decimal number.
b_to_d_3(binary): Converts a 3-bit binary string to a decimal number.
d_to_b_7(decimal): Converts a decimal number to a 7-bit binary string.
d_to_b_16(decimal): Converts a decimal number to a 16-bit binary string.
The code enters an infinite loop (while True) to execute instructions.

Inside the loop, the code fetches the current instruction from the ins list based on the value of the program counter (pc).

It then checks the opcode of the instruction to determine the operation to perform.

If the opcode is "11010" (halt instruction), it sets the flags to 0 and prints the current state of the program (program counter, registers, and flags) using the conversion functions. Finally, it breaks out of the loop.
If the opcode is "00100" (load instruction), it extracts the register number and memory index from the instruction and performs the load operation, updating the corresponding register with the value from memory. It also sets the flags to 0 and prints the current state.
If the opcode is "00101" (store instruction), it extracts the register number and memory index from the instruction and performs the store operation, updating the corresponding memory location with the value from the register. It also sets the flags to 0 and prints the current state.
If the opcode is "00000" (add instruction), it extracts the three register numbers from the instruction and performs the addition operation, storing the result in the first register. If the result is greater than 127, it sets the overflow flag. It also sets the other flags to 0 and prints the current state.
If the opcode is "00001" (subtract instruction), it extracts the three register numbers from the instruction and performs the subtraction operation, storing the result in the first register. If the result is greater than 127 or less than 0, it sets the overflow flag. It also sets the other flags to 0 and prints the current state.
If the opcode is "00110" (multiply instruction), it extracts the three register numbers from the instruction and performs the multiplication operation, storing the result in the first register. If the result is greater than 127, it sets the overflow flag. It also sets the other flags to 0 and prints the current state.
If the opcode is "00111" (divide instruction), it extracts the two register numbers from the instruction and performs the division operation, storing the quotient in register 0 and the remainder in register 1. If the divisor is 0, it sets the division-by-zero flag. It also sets the other flags to 0 and prints the current state.
After the main loop, there are additional instructions for logical operations


#BONUS PART OF THE QUESTION

We have designed 5 function s for the bonus part which is as mentioned below:
FN1:
Type A instruction(As per the project instructions pdf provided)
opcode = 10011
Instruction of hypotenuse calculation:
hyp reg1 reg2 reg3
where reg1 = root((reg2)**2 + (reg3)**2)
returning type int

FN2:
Type A instruction(As per the project instructions pdf provided)
Instruction of area calculation of rectangle:
opcode = 10101 
arr reg1 reg2 reg3

FN3:
Type A instruction(As per the project instructions pdf provided)
Instruction of area calculation of triangle:
opcode = 10110 
art reg1 reg2 reg3

FN4:
Type A instruction(As per the project instructions pdf provided)
Instruction to calculate exponent:
opcode = 10111
exp reg1 reg2 reg3

FN5:
Type A instruction(As per the project instructions pdf provided)
Instruction to get area of parabola of the number:
opcode = 10100
para reg1 reg2 reg3
where reg1 = area of parabola
reg2 = lower limit 
reg3 = upper limit
returning type int
