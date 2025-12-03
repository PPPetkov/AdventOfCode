def find_joltage(bank, active_batteries):
    joltage = ''
    for i in range(active_batteries - 1):
        battery = max(bank[:-(active_batteries - 1 - i)])
        bank = bank[bank.index(battery)+1:]
        joltage += battery
    
    return int(joltage + max(bank))

def part_one(filename):
    result = 0

    with open(filename, encoding='utf-8') as file:
        for line in file:
            line = line.strip()
            result += find_joltage(line, 2)

    return result

def part_two(filename):
    result = 0

    with open(filename, encoding='utf-8') as file:
        for line in file:
            line = line.strip()
            result += find_joltage(line, 12)

    return result    

if __name__ == '__main__':
    print(part_one('../input.txt'))
    print(part_two('../input.txt'))