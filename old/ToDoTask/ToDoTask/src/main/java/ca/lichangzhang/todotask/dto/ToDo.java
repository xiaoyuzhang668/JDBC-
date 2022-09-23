package ca.lichangzhang.todotask.dto;

/**　Email: xiaoyuzhang668@gmail.com
 *   Date: 2022
 *
 *  @author catzh
 */
public class ToDo {

    private int id;
    private String todo;
    private String note;
    private boolean finished;
    
    public ToDo() {
    }

    public ToDo(int id) {
        this.id = id;
    }
    
      public ToDo(String todo, String note, boolean finished) {
        this.todo = todo;
        this.note = note;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }
    
     public void setId(int id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

}

