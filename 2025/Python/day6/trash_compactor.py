OPERATORS = set(['+', '*'])

def solve_problem(numbers, operator):
    if operator == '+':
        return sum(numbers)

    solution = 1
    for number in numbers:
        solution *= number
    return solution

def part_one(filename):
    problems = []
    with open(filename, encoding='utf-8') as file:
        lines = file.readlines()
        problems = [[] for _ in range(len(lines[0].strip().split()))]

        for line in lines:
            numbers = line.strip().split()
            for i, num in enumerate(numbers):
                if num in OPERATORS:
                    problems[i].append(num)
                else:
                    problems[i].append(int(num))
                    
    result = 0
    for problem in problems:        
        result += solve_problem(problem[:-1], problem[-1])
    
    return result

def part_two(filename):
    problems = []
    with open(filename, encoding='utf-8') as file:
        for line in file:
            problems.append(list(reversed(line.strip('\n'))))

    problem_end = False
    rows = len(problems)
    cols = len(problems[0])
    numbers = []
    operator = ''
    i = 0
    result = 0

    while i < cols:
        number = []
        for j in range(rows):
            c = problems[j][i]
            if c.isnumeric():
                number.append(c)
            elif c in OPERATORS:
                operator = c
                problem_end = True
        numbers.append(int(''.join(number)))

        if problem_end:
            problem_end = False
            i += 1
            result += solve_problem(numbers, operator)
            numbers = []

        i += 1

    return result

if __name__ == '__main__':
    print(part_one('../input.txt'))
    print(part_two('../input.txt'))