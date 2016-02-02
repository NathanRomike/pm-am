import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Message {
  private int mId;
  private String mDescription;
  private int mTaskId;
  private int mUserId;
  private String mDateCreated;

  public int getId(){
    return mId;
  }

  public String getDescription() {
    return mDescription;
  }

  public int getUserId() {
    return mUserId;
  }

  public int getTaskId() {
    return mTaskId;
  }

  public String getDateCreated() {
    return mDateCreated;
  }

  public Message(String description, int userId, int taskId) {
     this.mDescription = description;
     this.mUserId = userId;
     this.mTaskId = taskId;
  }

  @Override
  public boolean equals(Object otherMessage) {
    if (!(otherMessage instanceof Message)) {
      return false;
    } else {
      Message newMessage = (Message) otherMessage;
      return this.getDescription().equals(newMessage.getDescription()) &&
             this.getUserId() == (newMessage.getUserId()) &&
             this.getDateCreated() == (newMessage.getDateCreated());
    }
  }

  public static List<Message> all() {
    String sql = "SELECT id AS mId,  description AS mDescription, user_id AS mUserId, task_id AS mTaskId, date_created AS mDateCreated FROM messages";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Message.class);
    }
  }

  public void save() {
    String sql = "INSERT INTO messages(description, user_id, task_id) VALUES (:description, :userId, :taskId)";
    try(Connection con = DB.sql2o.open()) {
      this.mId = (int) con.createQuery(sql, true)
        .addParameter("description", this.mDescription)
        .addParameter("userId", this.mUserId)
        .addParameter("taskId", this.mTaskId)
        .executeUpdate()
        .getKey();
    }
  }

  public static Message find(int id) {
    String sql = "SELECT id AS mId, description AS mDescription, user_id AS mUserId, task_id AS mTaskId, date_created AS mDateCreated FROM messages WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      Message message = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Message.class);
    return message;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String deleteMessage = "DELETE FROM messages WHERE id = :id;";
    con.createQuery(deleteMessage)
      .addParameter("id", mId)
      .executeUpdate();
    }
  }

  public void update(String newDescription, int newUserId) {
    mDescription = newDescription;
    mUserId = newUserId;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE messages SET description = :description, user_id = :userId";
      con.createQuery(sql)
      .addParameter("description", newDescription)
      .addParameter("userId", newUserId)
      .executeUpdate();
    }
  }

  public void assignTask(Task task) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO tasks_messages (message_id, task_id) VALUES (:messageId, :taskId)";
      con.createQuery(sql)
        .addParameter("messageId", this.mId)
        .addParameter("taskId", task.getId())
        .executeUpdate();
    }
  }
}
