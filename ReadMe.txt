# INSTALLATION

Simply use the jar file or compile using
`javac -d build -cp src/ src/bleaf/Bleaf.java` and then run using
`cd build` and `java bleaf.Bleaf`

# USAGE

The program has to be run in a java environment. 

1. When you run the program it will ask you if you want to do resolution or remainders. 
Type rs for resolution or rm for remainders. 

2. Next you will be asked to choose your knowledge base. 
You can use an example that’s already loaded into the program or your own knowledge base. If you want to use your own KB it will first ask for the first clause, each literal in your first clause has to be separated with a comma to indicate it’s a new literal. If you want your literals to be negated you have to type “-“ before the literal. When you finish you clause and hit enter it will ask if you want to add another clause. This will repeat until you are done entering clauses. 

3. Depending on the choice of operation it will ask you for phi or not phi.
If you have chosen resolution it will ask for not phi. If you have choosen remainders it will ask for phi.
This is because to calculate the remainders such that they do not entail not phi we need to find contradictions to phi.

4. If you choose to enter your own blelief base it will ask you to enter your own phi. But if you choose an example as your belief base you can choose to use a premade example or a custom phi.

5. It will then do the choosen operation with the belief base and phi and give you the result.
