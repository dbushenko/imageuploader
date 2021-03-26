package aws.imgupload.imgupload.application;

import java.io.IOException;

public interface HasFlows<T> {
    T mainFlow() throws IOException;
    T alternateFlows(Exception exception);

    default T run() {
        try {
            return mainFlow();
        } catch (Exception exception) {
            return alternateFlows(exception);
        }
    }

    class Unit {
        private Unit() {}
        public final static Unit instance = new Unit();
    }
}
