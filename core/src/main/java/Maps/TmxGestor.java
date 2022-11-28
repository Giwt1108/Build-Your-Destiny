/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import estructuras.DoubleLinkedList;
import Maps.PathMatrix;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sebastian
 */
public class TmxGestor {
    private int row,col;
    private int level;
    
    
    private File map;
    
    private List<String>[][] matrix;
    
    private String head; 
    private String tail;
    
    //private String path;
    
    private File file;
    private Integer[] initPoint ;
    
    private List<String> initData = new ArrayList<String>();
    private List<String> finishData = new ArrayList<String>();
    
    private List<String> upLeftData = new ArrayList<String>();
    private List<String> upRightData = new ArrayList<String>();
    private List<String> downLeftData = new ArrayList<String>();
    private List<String> downRightData = new ArrayList<String>();
    
    private List<String> leftRightData = new ArrayList<String>();
    private List<String> upDownData = new ArrayList<String>();
    
    public TmxGestor(int level, int row, int col){
        PathMatrix<Integer[]> matrix = new PathMatrix<Integer[]>(row,col);
        DoubleLinkedList<Integer[]> path = matrix.findPath();
        this.row = row;
        this.col = col;
        this.level = level;    
        this.matrix = new ArrayList[row][col];
        init();
        createMap();
        getMapsData();
        buildPath(path);
    }
    
    public void init(){
        head = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
                "<map version=\"1.9\" tiledversion=\"1.9.2\" orientation=\"orthogonal\" renderorder=\"right-down\" width=\""+String.valueOf(col*32)+"\" height=\""+String.valueOf(row*32)+"\" tilewidth=\"32\" tileheight=\"32\" infinite=\"0\" nextlayerid=\"2\" nextobjectid=\"1\">\r\n"+
                " <tileset firstgid=\"1\" name=\"Room\" tilewidth=\"32\" tileheight=\"32\" tilecount=\"60\" columns=\"10\">\r\n" +
                "  <image source=\"Room.jpg\" trans=\"000000\" width=\"320\" height=\"192\"/>\r\n" +
                "  <tile id=\"0\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"1\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"2\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"3\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"6\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"7\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"8\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"animation\" value=\"FireUp\"/>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"9\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"animation\" value=\"TorchDown\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"10\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"17\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"18\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"animation\" value=\"FireDown\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"19\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"20\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"27\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"28\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"animation\" value=\"FireUp\"/>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"29\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"animation\" value=\"TorchDown\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"30\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"37\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"38\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"animation\" value=\"FireDown\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"39\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"40\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"41\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"42\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"43\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"46\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"47\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"48\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"animation\" value=\"FireUp\"/>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"49\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"animation\" value=\"TorchDown\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"58\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"animation\" value=\"FireDown\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                "  <tile id=\"59\">\r\n" +
                "   <properties>\r\n" +
                "    <property name=\"blocked\" value=\"\"/>\r\n" +
                "   </properties>\r\n" +
                "  </tile>\r\n" +
                " </tileset>\r\n" +
                " <layer id=\"1\" name=\"Capa de patrones 1\" width=\""+String.valueOf(col*32)+"\" height=\""+String.valueOf(row*32)+"\">\r\n" +
                "  <data encoding=\"csv\">\r\n";
                
        
        tail = "</data>\r\n" +
                " </layer>\r\n" +
                "</map>";
    }
    
    public void buildPath(DoubleLinkedList<Integer[]> path){
        List<Integer[]> coords = new ArrayList<Integer[]>();
        String stringPath = "";
        while(!path.isEmpty()){
            Integer[] coord = path.popBack();
            coords.add(coord);
        }
        for(int i = 0;i<row*col-1;i++){
            int deltaY = coords.get(i)[0]-coords.get(i+1)[0];
            int deltaX = coords.get(i)[1]-coords.get(i+1)[1];
            if(deltaY == -1){
                stringPath += "D";
            }else if(deltaY == 1){
                stringPath+= "U";
            }else if(deltaX == 1){
                stringPath+= "L";
            }else if(deltaX == -1){
                stringPath+= "R";
            }
        }
        
        initPoint = coords.get(0);
        pathLoader(coords,stringPath);
        
    }
    
    private void pathLoader(List<Integer[]> coords, String stringPath){
        String substring;
        String charAti="";
        
        substring = stringPath.substring(0,1);
        
        try{
            file = new File("Maps/Init/"+substring+".tmx");
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            String row;
            while((row = buffer.readLine())!=null){
                initData.add(row+"\r\n");
            }
        }catch(Exception e){System.out.println("Algo salio mal en el init");}
        
        matrix[coords.get(0)[0]][coords.get(0)[1]] = initData;
        for(int i = 0; i<stringPath.length()-1;i++){
            if(stringPath.charAt(i)=='U'){
                charAti = "D";
            }else if(stringPath.charAt(i)=='D'){
                charAti = "U";
            }else if(stringPath.charAt(i)=='L'){
                charAti = "R";
            }else if(stringPath.charAt(i)=='R'){
                charAti = "L";
            }
            substring = charAti+stringPath.charAt(i+1);
            
            if(substring.equals("RL") || substring.equals("LR")){
                matrix[coords.get(i+1)[0]][coords.get(i+1)[1]] = leftRightData;
            }else if(substring.equals("UD") || substring.equals("DU")){
                matrix[coords.get(i+1)[0]][coords.get(i+1)[1]] = upDownData;
            }else if(substring.equals("UL") || substring.equals("LU")){
                matrix[coords.get(i+1)[0]][coords.get(i+1)[1]] = upLeftData;
            }else if(substring.equals("UR") || substring.equals("RU")){
                matrix[coords.get(i+1)[0]][coords.get(i+1)[1]] = upRightData;
            }else if(substring.equals("DL") || substring.equals("LD")){
                matrix[coords.get(i+1)[0]][coords.get(i+1)[1]] = downLeftData;
            }else if(substring.equals("DR") || substring.equals("RD")){
                matrix[coords.get(i+1)[0]][coords.get(i+1)[1]] = downRightData;
            }else{System.out.println("substring rara :" + substring);}
            
        }
        
        if(stringPath.charAt(stringPath.length()-1)=='U'){
                charAti = "D";
            }else if(stringPath.charAt(stringPath.length()-1)=='D'){
                charAti = "U";
            }else if(stringPath.charAt(stringPath.length()-1)=='L'){
                charAti = "R";
            }else if(stringPath.charAt(stringPath.length()-1)=='R'){
                charAti = "L";
            }
        substring = charAti;
        try{
            file = new File("Maps/Init/"+substring+".tmx");
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            String row;
            while((row = buffer.readLine())!=null){
                finishData.add(row+"\r\n");
            }
        }catch(Exception e){System.out.println("Algo salio mal en el finish");}
        
        matrix[coords.get(coords.size()-1)[0]][coords.get(coords.size()-1)[1]] = finishData;
    }
    
    private void createMap(){
        String name = "Maps/level"+String.valueOf(level);
        try{
            map = new File(name+".tmx");
            map.createNewFile();
        }catch (Exception e){}
    }
    
    public void getMapsData(){
        getRectData();
        getEdgesData();
    }
    
    public void writeTmx(){
        try{            
            FileWriter writer = new FileWriter(map);
            writer.write(head);
            String row;
            for(int i=0;i<matrix.length;i++){
                for(int k=0;k<32;k++){
                    row = "";
                    for(int j = 0; j<matrix[i].length;j++){
                        if(k!=31){
                            row += matrix[i][j].get(k).replace("\r\n", "");
                        }else{
                            if(i!=matrix.length-1 || j!=matrix[i].length-1 || k!=31){
                                row += matrix[i][j].get(k).replace("\r\n", "")+",";
                            }else{
                                row += matrix[i][j].get(k).replace("\r\n", "");
                            }
                        }
                    }
                    
                    writer.write(row+"\r\n");
                }
            }
            writer.write(tail);
            writer.close();
        }catch(Exception e){System.out.println(e);}
    }

    private void getRectData() {
        try{
            file = new File("Maps/Rect/LR.tmx");
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            String row;
            while((row = buffer.readLine())!=null){
                leftRightData.add(row+"\r\n");
            }
            
            file = new File("Maps/Rect/UD.tmx");
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            while((row = buffer.readLine())!=null){
                upDownData.add(row+"\r\n");
            }
        }catch(Exception e){System.out.println("Algo salio mal en el rect");}
    }

    private void getEdgesData() {
        try{
            file = new File("Maps/Edges/UR.tmx");
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            String row;
            while((row = buffer.readLine())!=null){
                upRightData.add(row+"\r\n");
            }
            
            file = new File("Maps/Edges/UL.tmx");
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            while((row = buffer.readLine())!=null){
                upLeftData.add(row+"\r\n");
            }
            
            file = new File("Maps/Edges/DR.tmx");
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            while((row = buffer.readLine())!=null){
                downRightData.add(row+"\r\n");
            }
            
            file = new File("Maps/Edges/DL.tmx");
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            while((row = buffer.readLine())!=null){
                downLeftData.add(row+"\r\n");
            }
        }catch(Exception e){System.out.println("Algo salio mal en edges");}
    }
    
    public Integer[] getInitPoint(){
        return initPoint;
    }
}
