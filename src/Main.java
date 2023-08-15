import java.util.*;

public class Main {
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                int count = 0;
                String rlrfr = generateRoute("RLRFR", 100);
                char[] c =rlrfr.toCharArray();
                for(Character q : c) {
                    if (q == 'R') {
                        count++;
                    }
                }
//                System.out.println(rlrfr + " -> " + count);
                synchronized (sizeToFreq) {
                    if ((sizeToFreq.get(count)) != null) {
                        sizeToFreq.put(count, (sizeToFreq.get(count) + 1));
                    } else {
                        sizeToFreq.put(count, 1);
                    }
                }
            }

//            sizeToFreq.entrySet().forEach(System.out::println);
            int max = 0;
            for (Map.Entry<Integer, Integer> entry : sizeToFreq.entrySet()) {
                if (entry.getKey() > max) {
                    max = entry.getKey();
                }
            }
            System.out.println("Самое частое количество повторений " + max + " (встретилось " + sizeToFreq.get(max) + " раз)\nДругие размеры:");

            for (Map.Entry<Integer, Integer> entry : sizeToFreq.entrySet()) {
                System.out.println("- " + entry.getKey() + " ( " + entry.getValue() + " раз)");
            }
        }).start();
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }

}
