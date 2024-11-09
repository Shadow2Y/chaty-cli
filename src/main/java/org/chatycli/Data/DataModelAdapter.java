package org.chatycli.Data;

import com.google.gson.TypeAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.chatycli.Data.DataModel;
import org.chatycli.Data.Message;
import org.chatycli.Data.Session;
import org.chatycli.Data.User;

import java.io.IOException;

public class DataModelAdapter extends TypeAdapter<DataModel> {
    private final Gson gson = new Gson();

    @Override
    public void write(JsonWriter out, DataModel dataModel) throws IOException {
        out.beginObject();

        // Serialize each field individually
        out.name("user");
        gson.toJson(dataModel.getUser(), User.class, out);

        out.name("session");
        gson.toJson(dataModel.getSession(), Session.class, out);

        out.name("message");
        gson.toJson(dataModel.getMessage(), Message.class, out);

        out.endObject();
    }

    @Override
    public DataModel read(JsonReader in) throws IOException {
        in.beginObject();

        User user = null;
        Session session = null;
        Message message = null;

        // Read each field individually
        while (in.hasNext()) {
            String name = in.nextName();

            switch (name) {
                case "user":
                    user = gson.fromJson(in, User.class);
                    break;
                case "session":
                    session = gson.fromJson(in, Session.class);
                    break;
                case "message":
                    message = gson.fromJson(in, Message.class);
                    break;
                default:
                    in.skipValue(); // Skip any unexpected fields
                    break;
            }
        }
        in.endObject();

        // Return a new DataModel instance populated with the deserialized fields
        if (user == null || session == null || message == null) {
            throw new JsonSyntaxException("Missing fields in DataModel JSON");
        }

        return new DataModel(user, session, message);
    }
}
