package com.pppetkov.adventofcode2023.day8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pppetkov.adventofcode2023.utility.Utility;

public class HauntedWasteland {
    private static Map<String, List<String>> generateMap(List<String> input){
        Map<String, List<String>> map = new HashMap<>();

        for(int i = 2; i < input.size(); ++i){
            String[] values = input.get(i).split("\\s+");
            String node = values[0];
            String left = values[2].substring(1,4);
            String right = values[3].substring(0, 3);

            map.putIfAbsent(node, new ArrayList<>());
            List<String> next = map.get(node);
            next.add(left);
            next.add(right);
        }

        return map;
    }

    public static int findShortestFollowedPathLength(String filename) throws IOException{
        List<String> input = Utility.readInput(filename);

        int pathLength = 0;
        int currentIndex = 0;
        String pathToFollow = input.get(0);
        Map<String, List<String>> nodeToNeighbours = generateMap(input);

        String currentNode = "AAA";
        String endNode = "ZZZ";

        while(!currentNode.equals(endNode)){
            ++pathLength;
            List<String> nextNodes = nodeToNeighbours.get(currentNode);
            char direction = pathToFollow.charAt(currentIndex++);
            currentIndex %= pathToFollow.length();
            
            if(direction == 'L'){
                currentNode = nextNodes.get(0);
            } else{
                currentNode = nextNodes.get(1);
            }   
        }
        return pathLength;
    }

    private static List<String> findStartingNodes(Map<String, List<String>> map){
        List<String> nodes = new ArrayList<>();

        for (String node : map.keySet()) {
            if(node.charAt(2) == 'A'){
                nodes.add(node);
            }
        }

        return nodes;
    }

    private static Set<String> findEndNodes(Map<String, List<String>> map){
        Set<String> nodes = new HashSet<>();

        for (String node : map.keySet()) {
            if(node.charAt(2) == 'Z'){
                nodes.add(node);
            }
        }

        return nodes;
    }

    public static long findShortestFollowedPathLengthPart2(String filename) throws IOException{
        List<String> input = Utility.readInput(filename);

        int pathLength = 0;
        int currentIndex = 0;
        String pathToFollow = input.get(0);
        Map<String, List<String>> nodeToNeighbours = generateMap(input);

        List<String> currentNodes = findStartingNodes(nodeToNeighbours);
        Set<String> endNodes = findEndNodes(nodeToNeighbours);
        Map<String, Integer> nodeToloopLength = new HashMap<>();

        while(nodeToloopLength.keySet().size() != endNodes.size()){
            ++pathLength;
            char direction = pathToFollow.charAt(currentIndex++);
            currentIndex %= pathToFollow.length();

            for(int i = 0; i < currentNodes.size(); ++i){
                String node = currentNodes.get(i);

                if(nodeToloopLength.get(node) == null){
                    List<String> nextNodes = nodeToNeighbours.get(node);

                    if(direction == 'L'){
                        node = nextNodes.get(0);
                    } else{
                        node = nextNodes.get(1);
                    }
    
                    
                    currentNodes.set(i, node);
                    if(endNodes.contains(node)){
                        nodeToloopLength.put(node, pathLength);
                    }
                }
            }
        }

        List<Integer> loopLengths = new ArrayList<>();

        for (Integer loopLength : nodeToloopLength.values()) {
            loopLengths.add(loopLength);
        }

        return Utility.findLCM(loopLengths);
    }
}
