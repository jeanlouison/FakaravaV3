package ecosystem;

public class Camera {
    
    /**
     * @attribute
     */
    private Integer num;

    /**
     * @attribute
     */
    Diver diver;
    
    /**
     * @attribute
     */
    Fishway fishway;

    /**
     * Constructeur de la classe Camera
     * @param num id de la camera
     * @param f passe dans laquelle est posee la camera
     * @param diver plongeur qui a pose la camera
     */
    public Camera(int num, Fishway f, Diver diver) {
        this.num = num;
        this.fishway = f;
        this.diver = diver;
    }

    /**
     * @return le plongeur qui a pose la camera
     */
    public Diver getDiver() {
        return diver;
    }

    /**
     * @return id de la camera
     */
    public Integer getNum() {
        return num;
    }

    /**
     * @return la passe dans laquelle est posee la camera
     */
    public Fishway getFishway() {
        return fishway;
    }
}
