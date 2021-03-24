

/*
    逃逸分析
    快速判断是否发生了逃逸分析，看new对象实体是否有可能在方法外被调用。
 */
public class EscapeAnalysis {
    public EscapeAnalysis obj;

    //方法返回EscapeAnalysis对象，发生逃逸
    public EscapeAnalysis getInstance(){
        return obj==null?new EscapeAnalysis():obj;
    }
    //为成员属性赋值，发生逃逸
    public void setObj(){
        obj=new EscapeAnalysis();
        //当obj声明为static时，仍然会发生逃逸
    }
    //对象的作用域仅在当前方法中有效，没有发生逃逸
    public void ues(){
        EscapeAnalysis e=new EscapeAnalysis();
    }
    //应用成员变量的值，发生逃逸
    public void use1(){
        EscapeAnalysis e=getInstance();
    }
}
