import io.netty.util.internal.ObjectUtil;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;

public class demo {
    @Test
    public void test() throws NoSuchMethodException {
        Object test = ObjectUtil.checkNotNull(1, "clazz");
        Class<?> aClass = test.getClass();
        Constructor<?> constructor = aClass.getConstructor();
    }
}
