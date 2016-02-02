
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Epic {
  private int mId;
  private String mTitle;
  private User mCreator;
  private String mDateCreated;
  private String mStatus;
  private String mDescription;
  private int mTypeTaskId;
  private User mImplementer;


  public int getId(){
    return mId;
  }

  public String getTitle() {
    return mTitle;
  }

  public User getCreator() {
    return mCreator;
  }

  public String getDateCreated() {
    return mDateCreated;
  }

  public String getStatus() {
    return mStatus;
  }

  public String getDescription() {
    return mDescription;
  }

  public int getTypeTaskId() {
    return mTypeTaskId;
  }

  public User getImplementer() {
    return mImplementer;
  }

  public Epic(String title, int creatorId, String status, String description, int typeTaskId, int implementerId) {
     this.mTitle = title;
     this.mCreator = User.find(creatorId);
     this.mStatus = status;
     this.mDescription = description;
     this.mTypeTaskId = typeTaskId;
     this.mImplementer = User.find(implementerId);
  }

  @Override
  public boolean equals(Object otherEpic) {
    if (!(otherEpic instanceof Epic)) {
      return false;
    } else {
      Epic newEpic = (Epic) otherEpic;
      return this.getTitle().equals(newEpic.getTitle()) &&
            this.getCreator() == (newEpic.getCreator()) &&
            this.getStatus().equals(newEpic.getStatus()) &&
            this.getDescription().equals(newEpic.getDescription()) &&
            this.getTypeTaskId() == (newEpic.getTypeTaskId()) &&
            this.getImplementer() == (newEpic.getImplementer());
    }
  }

  public static List<Epic> all() {
    String sql = "SELECT id AS mId, title AS mTitle, creator_user_id AS mCreator, date_created AS mDateCreated, status AS mStatus, description AS mDescription, type_task_id AS mTypeTaskId, developer_id AS mImplementer FROM tasks";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Epic.class);
    }
  }

  public void save() {
    String sql = "INSERT INTO tasks(title, creator_user_id, status, description, type_task_id, developer_id) VALUES (:title, :creatorId, :status, :description, :typeTaskId, :implementerId)";
    try(Connection con = DB.sql2o.open()) {
      this.mId = (int) con.createQuery(sql, true)
        .addParameter("title", this.mTitle)
        .addParameter("creatorId", mCreator.getId())
        .addParameter("status", this.mStatus)
        .addParameter("description", this.mDescription)
        .addParameter("typeTaskId", this.mTypeTaskId)
        .addParameter("implementerId", mImplementer.getId())
        .executeUpdate()
        .getKey();
    }
  }

  public static Epic find(int id) {
    String sql = "SELECT id AS mId, title AS mTitle, creator_user_id AS mCreator, date_created AS mDateCreated, status AS mStatus, description AS mDescription, type_task_id AS mTypeTaskId, developer_id AS mImplementer FROM tasks WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      Epic epic = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Epic.class);
    return epic;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String deleteEpic = "DELETE FROM tasks WHERE id = :id;";
    con.createQuery(deleteEpic)
      .addParameter("id", mId)
      .executeUpdate();
    }
  }

  public void updateTitleAndDescription(String newTitle, String newDescription) {
    mTitle = newTitle;
    mDescription = newDescription;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tasks SET title = :title, description = :description WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", mId)
      .addParameter("title", newTitle)
      .addParameter("description", newDescription)
      .executeUpdate();

    }
  }

  public void updateStatus(String newStatus) {
    mStatus = newStatus;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tasks SET status = :status WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", mId)
      .addParameter("status", newStatus)
      .executeUpdate();
    }
  }

  public void updateImplementer(int newImplementerId) {
    mImplementer = User.find(newImplementerId);
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tasks SET developer_id = :implementer WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", mId)
      .addParameter("implementer", newImplementerId)
      .executeUpdate();
    }
  }
}
