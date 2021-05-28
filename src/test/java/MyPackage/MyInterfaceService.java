package MyPackage;

import jolie.runtime.JavaService;
import jolie.runtime.Value;
import jolie.runtime.embedding.RequestResponse;

public class MyInterfaceService extends JavaService {

    public void ow_1(Value value) {
        String request = value.strValue();
    }

    public void ow_2(Value value) {
        MyType request = MyType.parse(value);
    }

    @RequestResponse()
    public Value rr_1(Value value) {
        String request = value.strValue();
    }

    @RequestResponse()
    public Value rr_2(Value value) {
        MyOtherType request = MyOtherType.parse(value);
    }
}
