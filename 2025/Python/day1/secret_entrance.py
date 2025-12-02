def part_one(filename):
    current = 50
    zero_count = 0
    with open(filename) as file:
        for line in file:
            turn_amount = int(line[1:])
            current += turn_amount if line[0] == 'R' else -turn_amount
            current %= 100
            if current == 0:
                zero_count += 1

    return zero_count

def part_two(filename):
    current = 50
    zero_count = 0
    with open(filename) as file:
        for line in file:
            turn_amount = int(line[1:])
            turn_dir = -1 if line[0] == 'L' else 1
            for _ in range(turn_amount):
                current += turn_dir
                current %= 100
                if current == 0:
                    zero_count += 1
        
    return zero_count

if __name__ == '__main__':
    print(part_one('../input.txt'))
    print(part_two('../input.txt'))