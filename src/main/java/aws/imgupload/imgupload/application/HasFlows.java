package aws.imgupload.imgupload.application;

public interface HasFlows<T> {
    T mainFlow();
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
