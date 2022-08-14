// 实现runnable接口
public class MyRunnable
{
    /***
     *
     * @param i
     * @return
     * @throws Exception
     * 链接：https://www.nowcoder.com/questionTerminal/1c4845e0a6254fb29914ec099b8d93fd
     * 来源：牛客网
     *
     * (1)如果try快中抛出了一个在catch子句中说明的异常类，那么程序将跳过try语句的其余代码,执行catch子句中的处理器代码。
     * (2)如果方法中的任何代码抛出了一个在catch子句中都有声明的异常类型，那么方***立即退出。
     * (3)无论是否抛出异常finally语句块最终都会执行。
     * (4)当try块中包含return语句时，在执行return语句前会先执行finally块，如果finally块中也有return语句，这个return语句的返回值会将try块中return语句的返回值覆盖掉。
     * (5)如果try块中抛出异常，finally中也抛出相同类型异常，那么原始异常将会丢失，转而抛出finally中的异常
     */
    public static int aMethod(int i) throws Exception {
        try{//抛出异常
            return 10/i;
        }
        catch (Exception ex)//捕捉异常
        {
            throw new Exception("exception in a aMethod");
        }finally{//一定会执行
            System.out.printf("finally");
        }
    }
    public static void main(String[] args){
        try
        {
            aMethod(0);
        }
        catch (Exception ex)
        {
            System.out.printf("exception in main");
        }
        System.out.printf("finished");
    }
}
