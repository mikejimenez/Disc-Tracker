package android.post.testing;

public class Courses {

    //private variables
    int _id;
    String _name;
    String _holes;


    public Courses() {

    }

    public Courses(int id, String name, String _holes) {
        this._id = id;
        this._name = name;
        this._holes = _holes;
    }


    public Courses(String name, String _holes) {
        this._name = name;
        this._holes = _holes;
    }

    // getting ID
    public int getID() {
        return this._id;
    }

    // setting id
    public void setID(int id) {
        this._id = id;
    }

    // getting name
    public String getName() {
        return this._name;
    }

    // setting name
    public void setName(String name) {
        this._name = name;
    }

    // getting holes
    public String getHoles() {
        return this._holes;
    }

    // setting holes
    public void setHoles(String holes) {
        this._holes = holes;
    }
}
