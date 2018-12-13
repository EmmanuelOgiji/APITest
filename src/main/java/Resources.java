public class Resources {

    public static  String getServer(){
        return "/app/rest/server";
    }

    public static  String getProject(){
        return "/app/rest/projects";
    }

    public static  String postNewUser(){ return "/app/rest/users"; }

    public static  String postNewUserBody(){
        return "{\n" +
                "  \"username\": \"AliH\",\n" +
                "  \"name\": \"Ali\",\n" +
                "  \"id\": 11,\n" +
                "  \"email\": \"string\",\n" +
                "  \"lastLogin\": \"string\",\n" +
                "  \"password\": \"string\",\n" +
                "  \"hasPassword\": false,\n" +
                "  \"realm\": \"string\",\n" +
                "  \"href\": \"string\",\n" +
                "  \"properties\": {\n" +
                "    \"count\": 1,\n" +
                "    \"href\": \"/app/rest/users/id:2\",\n" +
                "    \"property\": [\n" +
                "      {\n" +
                "        \"name\": \"string\",\n" +
                "        \"value\": \"string\",\n" +
                "        \"inherited\": false,\n" +
                "        \"type\": {\n" +
                "          \"rawValue\": \"string\"\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"groups\": {\n" +
                "    \"count\": 1,\n" +
                "    \"group\": [\n" +
                "      {\n" +
                "        \"key\": \"ALL_USERS_GROUP\",\n" +
                "        \"name\": \"All Users\",\n" +
                "        \"href\": \"string\",\n" +
                "        \"description\": \"string\",\n" +
                "        \"users\": {\n" +
                "          \"count\": 0,\n" +
                "          \"user\": [\n" +
                "            null\n" +
                "          ]\n" +
                "        },\n" +
                "        \"properties\": {\n" +
                "          \"count\": 0,\n" +
                "          \"href\": \"string\",\n" +
                "          \"property\": [\n" +
                "            {\n" +
                "              \"name\": \"string\",\n" +
                "              \"value\": \"string\",\n" +
                "              \"inherited\": false,\n" +
                "              \"type\": {\n" +
                "                \"rawValue\": \"string\"\n" +
                "              }\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"locator\": \"string\"\n" +
                "}";
    }
}
