public class Estudiante {
    private int id;
    private String first_name;
    private String last_name;
    private String gender;
    private String career_aspiration;
    private int math_score;

    public Estudiante(int id, String first_name, String last_name, String gender, String career_aspiration, int math_score) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.career_aspiration = career_aspiration;
        this.math_score = math_score;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int var1) {
        this.id = id;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String var1) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String var1) {
        this.last_name = last_name;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String var1) {
        this.gender = gender;
    }

    public String getCareer_aspiration() {
        return this.career_aspiration;
    }

    public void setCareer_aspiration(String var1) {
        this.career_aspiration = career_aspiration;
    }

    public int getMath_score() {
        return this.math_score;
    }

    public void setMath_score(int var1) {
        this.math_score = math_score;
    }

    public String toString() {
        return "Estudiante {Id = " + this.id + ", primerNombre = " + this.first_name + ", apellidoPaterno = " + this.last_name + ", genero = " + this.gender + ", career aspiration = " + this.career_aspiration + ", math score = " + this.math_score + "}";
    }
}
