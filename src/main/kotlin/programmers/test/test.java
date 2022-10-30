package programmers.test;

public class test {
    public void solution(){
        Long var1 = 1000000L;
        Double var2 = 100.0;

        Long var3 = var2.longValue();

        System.out.println(var1.doubleValue()*0.04);
        System.out.println(Math.round(var1.doubleValue()*0.04*10)/10.0);
    }
}