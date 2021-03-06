import java.math.BigInteger;
import java.rmi.*;

public class Client {

    public static void main(String[] args){
        try {
            IEcho e = (IEcho)Naming.lookup("echo");
            EchoRequest echoRequest = new EchoRequest("This is message");
            EchoResponse echoResponse = e.echo(echoRequest);
            System.out.println(echoResponse);

            IAddition a = (IAddition) Naming.lookup("add");
            int parm1 = 2;
            int parm2 = 3;
            AdditionRequest additionRequest = new AdditionRequest(parm1, parm2);
            AdditionResponse additionResponse = a.add(additionRequest);
            System.out.println(additionResponse);
        }
        catch(Exception e){
            System.err.println(e);
        }
    }
}