def part_one(filename):
    result = 0
    with open(filename, encoding='utf-8') as file:
        for r in file.readline().split(','):
            bounds = r.split('-')
            low = int(bounds[0])
            high = int(bounds[1]) + 1

            for i in range(low, high):
                id_str = str(i)
                middle = len(id_str) // 2
                if id_str[:middle] == id_str[middle:]:
                    result += i
    
    return result

def is_invalid(s, part_size):
    if len(s) % part_size != 0:
        return False
    
    parts = [s[i:i+part_size] for i in range(0, len(s), part_size)]
    for i in range(0, len(parts) - 1):
        if parts[i] != parts[i+1]:
            return False
    
    return True

def part_two(filename):
    result = 0
    with open(filename, encoding='utf-8') as file:
        for r in file.readline().split(','):
            bounds = r.split('-')
            low = int(bounds[0])
            high = int(bounds[1]) + 1

            for i in range(low, high):
                id_str = str(i)
                for j in range(1, len(id_str) // 2 + 1):
                    if is_invalid(id_str, j):
                        result += i
                        break

    return result

if __name__ == '__main__':
    print(part_one('../input.txt'))
    print(part_two('../input.txt'))
