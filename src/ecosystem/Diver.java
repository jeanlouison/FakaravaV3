package ecosystem;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gui.LagoonPainter;

public class Diver implements Clock{
    /**
     * @attribute
     */
    private ArrayList<String> log = new ArrayList<String>();

    /**
     * @attribute
     */
    private Integer id;

    /**
     * @attribute
     */
    private String name;

    /**
     * @attribute
     */
    private String laboratory;

    /**
     * @attribute
     */
    private ArrayList<String> TODOList = new ArrayList<String>();

    /**
     * @attribute
     */
    ArrayList<Transmitter> transmittersList = new ArrayList<Transmitter>();

    /**
     * @attribute
     */
    ArrayList<Camera> camerasList = new ArrayList<Camera>();

    /**
     * @attribute
     */
    int nextTransmitterNum = 0;

    /**
     * @attribute
     */
    int nextCameraNum = 0;

    /**
     * @param name
     * @param id
     * @param laboratory
     */
    public Diver(String name, Integer id, String laboratory) {
        this.name = name;
        this.id = id;
        this.laboratory = laboratory;
    }

    /**
     * Donne la liste des transmetteurs
     * @return transmittersList
     */
    public ArrayList<Transmitter> getTransmittersList() {
        return new ArrayList<Transmitter>(transmittersList);
    }

    /**
     * Donne la liste des cameras
     * @return camerasList
     */
    public ArrayList<Camera> getCamerasList() {
        return new ArrayList<Camera>(camerasList);
    }

    /**
     * Pose un transmetteur sur les predateurs de la passe donnee en parametre
     * @param f
     */
    public void putTransmitters(Fishway f) {
        for (Predator p : f.getPredatorList()) {
            Transmitter tm = new Transmitter(nextTransmitterNum, p, this);
            this.transmittersList.add(tm);
            this.nextTransmitterNum++;
            this.log.add("Pose transmitter sur : " + p.getNum());
            Lagoon.addToListeActions(this.name + " pose un transmetteur sur les predateurs de la passe " + f.getNum());
        }
    }

    /**
     * Supprime le transmetteur d'id donne en parametre
     * @param num
     */
    public void removeTransmitter(Integer num) {
        if (this.transmittersList.contains(this.getTransmitter(num))){
            this.transmittersList.remove(this.getTransmitter(num));
            this.log.add("Enleve le transmetteur no " + num);
            Lagoon.addToListeActions(this.name + " enleve le transmetteur no " + num);
        }
        else {
            throw new NullPointerException(num + " n'est pas un transmitter");
        }
    }

    /**
     * place une camera dans la passe donnee en parametre
     * @param f passe a poisson
     */
    public void putCamera(Fishway f) {
        Camera cam = new Camera(nextCameraNum, f, this);
        this.camerasList.add(cam);
        f.setCamera(cam);
        this.nextCameraNum++;
        this.log.add("Pose une camera sur : " + f.getNum());
        Lagoon.addToListeActions(this.name + " pose une camera sur " + f.getNum());
    }

    /**
     * supprime la camera dont l'id est donne en parametre
     * @param num
     */
    public void removeCamera(Integer num) {
        if (this.camerasList.contains(this.getCamera(num))){
            this.getCamera(num).getFishway().setCamera(null);
            this.camerasList.remove(this.getCamera(num));

            this.log.add("Enleve la camera no " + num);
            Lagoon.addToListeActions(this.name + " enleve la camera no " + num);
        }
        else {
            throw new NullPointerException(num + " n'est pas une camera");
        }
    }

    /**
     * Donne un resume rapide du plongeur
     */
    @Override
    public String toString() {
        return "Plongeur " + id + " : " + name + " (" + laboratory + ")";
    }

    /**
     * Donne l'id du plongeur
     * @return id du plongeur
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Donne la camera dont l'id est passe en parametre
     * @param num id de la camera
     * @return la camera
     */
    public Camera getCamera(int num) {
        Camera res = null;
        int i = 0;
        boolean found = false;
        while (i < this.camerasList.size() && found == false) {
            if (this.camerasList.get(i).getNum() == num) {
                res = this.camerasList.get(i);
                found = true;
            }
            i++;
        }
        return res;
    }

    /**
     * Donne le transmetteur dont l'id est passe en parametre
     * @param num id du transmetteur
     * @return le transmetteur
     */
    public Transmitter getTransmitter(int num) {
        Transmitter res = null;
        int i = 0;
        boolean found = false;
        while (i < this.transmittersList.size() && found == false) {
            if (this.transmittersList.get(i).getNum() == num) {
                res = this.transmittersList.get(i);
                found = true;
            }
            i++;
        }
        return res;
    }

    /**
     * Donne la liste des logs du plongeur
     * @return une liste de logs
     */
    public ArrayList<String> getLog() {
        return new ArrayList<String>(log);
    }

    /**
     * Donne la TODOlist du plongeur
     * @return la TODOlist
     */
    public ArrayList<String> getTODOList() {
        return new ArrayList<String>(TODOList);
    }

    /**
     * Ajoute une ligne a la liste de logs
     * @param info ligne a ajouter
     */
    public void addToLog(String info) {
        this.log.add(info);
    }

    /**
     * Ajoute une ligne a la TODOlist
     * @param action action a faire, sous forme "{{Camera}/{Transmitter}} {id de la passe}" selon l'action
     */
    public void addTODO(String action) {
        this.TODOList.add(action);
    }

    /**
     * Met a jour les informations des transmetteurs et fait la premiere action de la TODOlist
     * @param isDay
     */
    @Override
    public void tickTock(Boolean isDay) {
        
        if (! this.transmittersList.isEmpty()) {
            for(Transmitter t : this.transmittersList)
            {
                if(!Lagoon.getPredatorList().contains(t.getPredator()))
                {
                    this.log.add(t.getPredator().getNum() + " est parti de la lagune ou est mort");
                    this.transmittersList.remove(t);
                }
                else
                {
                    this.log.add(t.getPredator().toString());
                    t.getPredator().setIcon(new ImageIcon("predwtransmitter.png"));
                }
            }
        }
        if (isDay) {
            if (! TODOList.isEmpty()) {
                String[] action = TODOList.get(0).split(" ");
                TODOList.remove(0);
                
                //si on a pose une camera
                if (action[1].equals("Camera")) {
                    LagoonPainter.getCellByPosition(Lagoon.getFishway(Integer.parseInt(action[0])).getPosition()[0], Lagoon.getFishway(Integer.parseInt(action[0])).getPosition()[1]).add(new JLabel(new ImageIcon("diverputtingcamera.png")));
                    this.putCamera(Lagoon.getFishway(Integer.parseInt(action[0])));
                }
                //si on a pose un transmetteur
                else {
                    LagoonPainter.getCellByPosition(Lagoon.getFishway(Integer.parseInt(action[0])).getPosition()[0], Lagoon.getFishway(Integer.parseInt(action[0])).getPosition()[1]).add(new JLabel(new ImageIcon("diverputtingtransmitter.png")));
                    this.putTransmitters(Lagoon.getFishway(Integer.parseInt(action[0])));
                } 
            }
        }
    }
}