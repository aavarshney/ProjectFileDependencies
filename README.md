# ProjectFileDependencies
Problem Title: Project File Dependencies

Project managers, such as the UNIX utility make, are used to maintain large software projects made up

from many components. Users write a project file specifying which components (called tasks) depend on

others and the project manager can automatically update the components in the correct order.

Write a program that reads a project file from stdin and writes to stdout the order in which the tasks

should be performed.

For simplicity we represent each task by an integer number from where is the total number

of tasks. The first line of input specifies the number of tasks and the number of rules, such that

and .

The rest of the input consists of rules, one in each line, specifying dependencies using the following

This rule means that task number depends on tasks (we say that task is the target

and are dependents).

Note that tasks numbers are separated by single spaces and that rules end with a newline. Rules can

appear in any order, but each task can appear as target only once.

Your program can assume that there are no circular dependencies in the rules, i.e. no task depends

directly or indirectly on itself.

The output should be a single line with the permutation of the tasks to be performed, ordered by

dependencies (i.e. no task should appear before others that it depends on).

To avoid ambiguity in the output, tasks that do not depend on each other should be ordered by their

number (lower numbers first).