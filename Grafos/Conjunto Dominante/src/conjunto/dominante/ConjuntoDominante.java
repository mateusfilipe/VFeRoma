/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjunto.dominante;

import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class ConjuntoDominante {

    public static void calculaGrau(int[][] grafo,int grau[]){
        for(int i=0;i<grau.length;i++){
            grau[i]=0;
            for(int j=0;j<grau.length;j++){
                if(grafo[i][j]==1)
                {
                    grau[i]+=1;
                }
            }
        }
    }
    public static void ordenarGrafo(int [] grau,ArrayList<String>vertices){
        for(int i=0;i<grau.length;i++)
        {
            for(int j=1;j<grau.length;j++)
            {
                if(grau[j]>grau[i])
                {
                    String aux = vertices.get(i);
                    int aux1 = grau[i];
                    grau[i] = grau[j];
                    vertices.set(i, vertices.get(j));
                    grau[j] = aux1;
                    vertices.set(j,aux);
                }
            }
        }
    }
     private static void encontreDominante(ArrayList<String> verticesOriginais, ArrayList<String> vertices, ArrayList<String> solucao, int[][] grafo) {
        //int i=0;
        do{
            String u = vertices.get(0);
            vertices.removeAll(encontraVizinhos(verticesOriginais.indexOf(u),grafo,verticesOriginais));
            vertices.remove(u);
            solucao.add(u);
            //i++;
        }while(!vertices.isEmpty());
    }

    private static ArrayList<String> encontraVizinhos(int index, int[][] grafo, ArrayList<String> vertices) {
        ArrayList<String> solucao =new ArrayList<>();
        for(int i=0;i<vertices.size();i++){
            if(grafo[index][i]==1){
                solucao.add(vertices.get(i));
//                System.out.print("VERTICE RETIRADO:"+ vertices.get(i));
            }
        }
        System.out.println("");
        return solucao;
    }

    private static void imprimeSolucao(ArrayList<String> solucao, int grau[],ArrayList<String> vertices) {
        System.out.println("CONJUNTO DOMINANTE:");
        for (String string : solucao) {
            System.out.println(string);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int grafoT [][] = {{0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                           {1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
                           {1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0},
                           {0,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0},
                           {0,0,1,1,0,0,0,0,0,1,0,0,0,0,0,0},
                           {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0},
                           {0,0,0,0,0,0,1,1,0,0,1,0,0,1,0,0},
                           {0,0,0,0,1,0,0,0,0,0,1,1,0,0,0,0},
                           {0,0,0,0,0,0,0,1,1,1,0,1,0,0,0,0},
                           {0,0,0,0,0,0,0,0,0,1,1,0,1,0,0,0},
                           {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
                           {0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0},
                           {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1},
                           {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0}                                     
        };
        
        ArrayList<String> verticesT = new ArrayList<>();
        verticesT.add("1");
        verticesT.add("2");
        verticesT.add("3");
        verticesT.add("4");
        verticesT.add("5");
        verticesT.add("6");
        verticesT.add("7");
        verticesT.add("8");
        verticesT.add("9");
        verticesT.add("10");
        verticesT.add("11");
        verticesT.add("12");
        verticesT.add("13");
        verticesT.add("14");
        verticesT.add("15");
        verticesT.add("16");
        int [] grau = new int [verticesT.size()];
        calculaGrau(grafoT,grau);
        ArrayList<String> verticesOriginais = (ArrayList<String>) verticesT.clone();
        
        ordenarGrafo(grau,verticesT);
        ArrayList<String> solucao = new ArrayList<>();
        
        encontreDominante(verticesOriginais,verticesT,solucao,grafoT);
        System.out.println("GRAFO TÉRREO:");
        imprimeSolucao(solucao,grau,verticesOriginais);
        //-------------------------------------------------------------------------------------------------------
        int grafo1 [][] = {{0,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
                           {1,0,1,1,0,0,0,0,0,0,0,0,0,0,0},
                           {1,1,0,1,1,0,0,0,0,0,0,0,0,0,0},
                           {0,1,1,0,1,0,0,0,0,0,0,0,0,0,0},
                           {0,0,1,1,0,1,0,0,0,0,0,0,0,0,0},
                           {0,0,0,0,1,0,1,0,0,0,0,0,0,1,0},
                           {0,0,0,0,0,1,0,1,1,0,0,0,0,0,1},
                           {0,0,0,0,0,0,1,0,1,1,1,0,0,0,0},
                           {0,0,0,0,0,0,1,1,0,0,1,0,0,0,0},
                           {0,0,0,0,0,0,0,1,0,0,1,1,1,0,0},
                           {0,0,0,0,0,0,0,1,1,1,0,1,0,0,0},
                           {0,0,0,0,0,0,0,0,0,1,1,0,1,0,0},
                           {0,0,0,0,0,0,0,0,0,1,0,1,0,0,0},
                           {0,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
                           {0,0,0,0,0,0,1,0,0,0,0,0,0,1,0}                                           
        };
        
        ArrayList<String> vertices1 = new ArrayList<>();
        vertices1.add("1");
        vertices1.add("2");
        vertices1.add("3");
        vertices1.add("4");
        vertices1.add("5");
        vertices1.add("6");
        vertices1.add("7");
        vertices1.add("8");
        vertices1.add("9");
        vertices1.add("10");
        vertices1.add("11");
        vertices1.add("12");
        vertices1.add("13");
        vertices1.add("14");
        vertices1.add("15");
        int [] grau1 = new int [vertices1.size()];
        calculaGrau(grafo1,grau1);
        ArrayList<String> verticesOriginais1 = (ArrayList<String>) vertices1.clone();
        
        ordenarGrafo(grau1,vertices1);
        ArrayList<String> solucao1 = new ArrayList<>();
        
        encontreDominante(verticesOriginais1,vertices1,solucao1,grafo1);
        System.out.println("GRAFO PRIMEIRO ANDAR:");
        imprimeSolucao(solucao1,grau1,verticesOriginais1);
        //-------------------------------------------------------------------------------------------------------
        int grafo2 [][] = {{0,1,1,0,0,0,0,0,0,0,0,0,0},
                           {1,0,1,1,0,0,0,0,0,0,0,0,0},
                           {1,1,0,1,0,0,0,0,0,0,0,0,0},
                           {0,1,1,0,1,0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,1,1,0,0,0,0,0,0},
                           {0,0,0,0,1,0,1,0,0,0,0,0,0},
                           {0,0,0,0,1,1,0,1,1,0,0,0,0},
                           {0,0,0,0,0,0,1,0,1,1,1,0,0},
                           {0,0,0,0,0,0,1,1,0,0,1,0,0},
                           {0,0,0,0,0,0,0,1,0,0,1,1,1},
                           {0,0,0,0,0,0,0,1,1,1,0,1,0},
                           {0,0,0,0,0,0,0,0,0,1,1,0,1},
                           {0,0,0,0,0,0,0,0,0,1,0,1,0}
                                                                      
        };
        
        ArrayList<String> vertices2 = new ArrayList<>();
        vertices2.add("1");
        vertices2.add("2");
        vertices2.add("3");
        vertices2.add("4");
        vertices2.add("5");
        vertices2.add("6");
        vertices2.add("7");
        vertices2.add("8");
        vertices2.add("9");
        vertices2.add("10");
        vertices2.add("11");
        vertices2.add("12");
        vertices2.add("13");
        
        int [] grau2 = new int [vertices2.size()];
        calculaGrau(grafo2,grau2);
        ArrayList<String> verticesOriginais2 = (ArrayList<String>) vertices2.clone();
        
        ordenarGrafo(grau2,vertices2);
        ArrayList<String> solucao2 = new ArrayList<>();
        
        encontreDominante(verticesOriginais2,vertices2,solucao2,grafo2);
        System.out.println("GRAFO SEGUNDO ANDAR:");
        imprimeSolucao(solucao2,grau2,verticesOriginais2);
    }

   
    
}
