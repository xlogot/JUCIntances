package chapter03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FightQueryExample {
    private static List<String> fightCompany = Arrays.asList("CSA","CEA","HNA");

    public static void main(String[] args) {
        List<String> results=search("SH","BJ");
        System.out.println("================");
        results.forEach(System.out::println);
    }
    private static List<String> search(String original,String destination){

        final List<String> result=new ArrayList<>();
        List<FightQueryTask> tasks=fightCompany.stream().map(f->createSearchTask(f,original,destination)).collect(toList());

        tasks.forEach(Thread::start);

        tasks.forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        tasks.stream().map(FightQueryTask::get).forEach(result::addAll);
        return result;
    }

    private static FightQueryTask createSearchTask(String fight, String original, String destination) {
        return new FightQueryTask(fight,original,destination);
    }
}
