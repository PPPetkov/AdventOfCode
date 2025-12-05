def part_one(filename):
    ranges = []
    result = 0

    with open(filename, encoding='utf-8') as file:
        line = file.readline()
        while line != '\n':
            bounds = line.strip().split('-')
            ranges.append((int(bounds[0]), int(bounds[1])))
            line = file.readline()
        
        line = file.readline()
        while line:
            ingredient = int(line)
            for r in ranges:
                if r[0] <= ingredient <= r[1]:
                    result += 1
                    break
            line = file.readline()
    
    return result

def part_two(filename):
    ranges = []

    with open(filename, encoding='utf-8') as file:
        for line in file:
            line = line.strip()
            if not line:
                break

            l, h = map(int, line.split('-'))
            ranges.append((l, h))
    
    ranges.sort()
    fused = []
    l, h = ranges[0]

    for low, high in ranges[1:]:
        if low <= h:
            h = max(high, h)
        else:
            fused.append((l, h))
            l, h = low, high

    fused.append((l, h))

    return sum(h - l + 1 for l, h in fused)

if __name__ == '__main__':
    print(part_one('../input.txt'))
    print(part_two('../input.txt'))