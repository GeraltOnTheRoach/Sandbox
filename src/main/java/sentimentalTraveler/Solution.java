package sentimentalTraveler;

import java.util.*;

class Solution {

    public int solution(int[] T) {
        for (int i = 0; i < T.length; i++) {
            System.out.println(i + " <-> " + T[i]);
        }
        Map<Integer, Set<Integer>> connections = new HashMap<>(T.length);
        for (int i = 0; i < T.length; i++) connections.put(i, new TreeSet<>());
        for (int i = 0; i < T.length; i++) {
            if(i < T[i]) connections.get(i).add(T[i]);
            if(i > T[i]) connections.get(T[i]).add(i);
        }
        System.out.println(connections);
        T[T.length-1] = 0;
        for (int i = T.length-2; i >= 0; i--){


            T[i] = connections.get(i).stream().mapToInt(node -> 1 + T[node]).sum();
        }

        return  T.length + Arrays.stream(T).sum();
    }
}