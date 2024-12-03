package src_Server;

public class DBRequest {

    public String method;

    public Object params;

    public String id;

    public static class AddRecordParams {
        public AddRecordParams(String record, int index) {
            this.record = record;
            this.index = index;
        }
        public String record;
        public int index;
    }

    public static class GetRecordParams {
        public GetRecordParams(int index) {
            this.index = index;
        }
        public int index;
    }
}

