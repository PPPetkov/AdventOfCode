MOVEMENTS = [(-1, -1), (-1, 0), (-1, 1),
             ( 0, -1),          ( 0, 1), 
             ( 1, -1), ( 1, 0), ( 1, 1)]

PAPER_ROLL = '@'

def init_diagram(filename):
    diagram = []
    with open(filename, encoding='utf-8') as file:
        for line in file:
            row = []
            for c in line.strip():
                row.append(c == PAPER_ROLL)
            diagram.append(row)
    return diagram 

def part_one(filename):
    diagram = init_diagram(filename)           
    rows = len(diagram)
    cols = len(diagram[0])
    result = 0

    for x in range(rows):
        for y in range(cols):
            if not diagram[x][y]:
                continue

            rolls = 0
            for dx, dy in MOVEMENTS:
                n_x = x + dx
                n_y = y + dy

                if 0 <= n_x < rows and 0 <= n_y < cols and diagram[n_x][n_y]:
                    rolls += 1

            if rolls < 4:
                result += 1

    return result        

def part_two(filename):
    diagram = init_diagram(filename)
    rows = len(diagram)
    cols = len(diagram[0])
    result = 0

    any_removed = True
    while any_removed:
        any_removed = False
        for x in range(rows):
            for y in range(cols):
                if not diagram[x][y]:
                    continue

                rolls = 0
                for dx, dy in MOVEMENTS:
                    n_x = x + dx
                    n_y = y + dy

                    if 0 <= n_x < rows and 0 <= n_y < cols and diagram[n_x][n_y]:
                        rolls += 1

                if rolls < 4:
                    result += 1
                    diagram[x][y] = False
                    any_removed = True
    
    return result

if __name__ == '__main__':
    print(part_one('../input.txt'))
    print(part_two('../input.txt'))