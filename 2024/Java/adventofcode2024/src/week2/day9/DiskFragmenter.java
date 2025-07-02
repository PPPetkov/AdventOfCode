package week2.day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class DiskFragmenter {
    public static long partOne(String filename) throws IOException {
        List<Integer> diskMap = parseLine(filename);
        int lastFileID = diskMap.size() - 1;
        while (diskMap.get(lastFileID) == -1) {
            lastFileID--;
        }

        int firstFreeSpaceIndex = diskMap.indexOf(-1);
        while (lastFileID > firstFreeSpaceIndex) {
            diskMap.set(firstFreeSpaceIndex, diskMap.get(lastFileID));
            diskMap.set(lastFileID, -1);

            while(diskMap.get(firstFreeSpaceIndex) != -1) {
                firstFreeSpaceIndex++;
            }
            while(diskMap.get(lastFileID) == -1) {
                lastFileID--;
            }
        }

        return calculateCheckSum(diskMap);
    }

    private static List<Integer> parseLine(String filename) throws IOException{
        String line = (new BufferedReader(new FileReader(filename))).readLine();
        int id = 0;
        List<Integer> diskMap = new ArrayList<>();

        for (int i = 0; i < line.length(); i++) {
            int repeatCount = Character.getNumericValue(line.charAt(i));
            boolean isFile = i % 2 == 0;
            int val = isFile ? id++ : -1;

            for (int j = 0; j < repeatCount; j++) {
                diskMap.add(val);
            }
        }

        return diskMap;
    }

    private static long calculateCheckSum(List<Integer> diskMap) {
        long result = 0;
        for (int i = 0; i < diskMap.size(); i++) {
            int val = diskMap.get(i);
            if (val != -1) {
                result += (long) i * val;
            }
        }
        return result;
    }

    public static long partTwo(String filename) throws IOException {
        List<Integer> diskMap = parseLine(filename);

        int lastFileIndex = diskMap.size() - 1;
        while (diskMap.get(lastFileIndex) == -1) {
            lastFileIndex--;
        }
        int fileSize = getBlockSizeDec(diskMap, lastFileIndex, diskMap.get(lastFileIndex));

        SortedMap<Integer, Integer> freeSpaces = getSizes(diskMap);

        while (lastFileIndex > freeSpaces.firstKey()) {
            //System.out.println(lastFileIndex);
            int fileID = diskMap.get(lastFileIndex);

            Set<Integer> keys = freeSpaces.keySet();
            for (int index : keys) {
                if (index < lastFileIndex && fileSize <= freeSpaces.get(index)) {
                    while (fileSize > 0) {
                        diskMap.set(index++, fileID);
                        diskMap.set(lastFileIndex--, -1);
                        fileSize--;
                    }

                    freeSpaces = getSizes(diskMap);
                    break;
                }
            }

            while (diskMap.get(lastFileIndex) == -1 || diskMap.get(lastFileIndex) == fileID) {
                lastFileIndex--;
            }
            fileSize = getBlockSizeDec(diskMap, lastFileIndex, diskMap.get(lastFileIndex));
        }

        return calculateCheckSum(diskMap);
    }

    private static SortedMap<Integer, Integer> getSizes(List<Integer> diskMap) {
        SortedMap<Integer, Integer> freeSpaceSizes = new TreeMap<>();
        int freeSpaceIndex = diskMap.indexOf(-1);
        while (freeSpaceIndex < diskMap.size()) {
            int size = getFreeSpaceSize(diskMap, freeSpaceIndex);
            freeSpaceSizes.put(freeSpaceIndex, size);
            freeSpaceIndex += size;
            while (freeSpaceIndex < diskMap.size() && diskMap.get(freeSpaceIndex) != -1) {
                freeSpaceIndex++;
            }
        }

        return freeSpaceSizes;
    }

    private static int getBlockSizeDec(List<Integer> diskMap, int start, int blockID) {
        int size = 0;
        while (start >= 0 && diskMap.get(start) == blockID) {
            size++;
            start--;
        }
        return size;
    }

    private static int getFreeSpaceSize(List<Integer> diskMap, int start) {
        int size = 0;
        while (start < diskMap.size() && diskMap.get(start) == -1) {
            size++;
            start++;
        }
        return size;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(partTwo("resources/input.txt"));
    }
}
