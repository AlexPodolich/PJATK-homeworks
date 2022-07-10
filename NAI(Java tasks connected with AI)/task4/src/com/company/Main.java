package com.company;

import java.io.IOException;
import java.util.*;

public class Main {

    public static String testFile1 = "resources/IrisTest.txt";
    public static String testFile2 = "resources/IrisTrain.txt";
    public static int numOfClusters;
    public static int numOfAttributes;
    public static ArrayList<String> classesNames;
    public static double sumOfPrevDistances = 1;
    public static double sumOfPrevPrevDistances = 1;

    public static void main(String[] args) throws IOException {
        menu();
    }

    public static void menu() throws IOException {
        System.out.println("1.Run testFile1");
        System.out.println("2.Run testFile2");
        System.out.println("3.Exit");
        System.out.println("User choice: ");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.println("Enter number of clusters: ");
                numOfClusters = sc.nextInt();
                ArrayList<CustomVector> testVectors1 = new ArrayList<>();
                Parser.Parse(testFile1, testVectors1);
                System.out.println("---------------------------Started Info---------------------------");
                getNumClassesAndAttributes(testVectors1);
                System.out.println("Number of cluster: " + numOfClusters);

                System.out.println("SIZE " + testVectors1.size());
                ArrayList<Cluster> clusters = createClusters(testVectors1, testVectors1.size());
                Parser.Parse(testFile1, testVectors1);
                algorithm(clusters, testVectors1);
                menu();
            }
            case 2 -> {
                System.out.println("Enter number of clusters: ");
                numOfClusters = sc.nextInt();
                ArrayList<CustomVector> testVectors2 = new ArrayList<>();
                Parser.Parse(testFile2, testVectors2);
                System.out.println("---------------------------Started Info---------------------------");
                getNumClassesAndAttributes(testVectors2);

                System.out.println("Number of cluster: " + numOfClusters);
                System.out.println("SIZE " + testVectors2.size());
                ArrayList<Cluster> clusters = createClusters(testVectors2, testVectors2.size());
                Parser.Parse(testFile2, testVectors2);
                algorithm(clusters, testVectors2);
                menu();
            }
            case 3 -> {
                System.out.println("Thank you! Bye!");
                System.exit(0);
            }
            default -> {
                System.out.println("Error! Enter number 1-3!");
                menu();
            }

        }
    }

    public static void getNumClassesAndAttributes(ArrayList<CustomVector> testArray){
        List<String> tmpArr = new ArrayList<>();
        for (CustomVector customVector : testArray) {
            tmpArr.add(customVector.getClassName());
        }
        Set<String> classNames = new LinkedHashSet<>(tmpArr);
        classesNames = new ArrayList<>(classNames);
        System.out.println(classesNames);

        numOfAttributes = testArray.get(0).toString().split(",").length - 1;

        System.out.println("Number of classes: " + classNames.size());
        System.out.println("Number of attributes: " + numOfAttributes);
    }

    public static ArrayList<Cluster> createClusters(ArrayList<CustomVector> testVectors, int size){
        ArrayList<CustomVector> copyArrVectors = testVectors;
        ArrayList<Cluster> clusterCentroids = new ArrayList<>(numOfClusters);
        Random random = new Random();
        for (int c = 0; c < numOfClusters; c++) {
            clusterCentroids.add(new Cluster("Cluster" + (c+1)));
        }

        for (int j = 0; j < size; j++) {
            int idCluster = random.nextInt(numOfClusters);
            int idVector = random.nextInt(copyArrVectors.size());
            clusterCentroids.get(idCluster).getArrOfVectors().add(copyArrVectors.get(idVector));
            copyArrVectors.remove(idVector);
        }

        for (int i = 0; i < numOfClusters; i++) {
            System.out.println(clusterCentroids.get(i).getArrOfVectors().size());
            Vector<Double> tempVect = new Vector<>();
            for (int attribute = 0; attribute < numOfAttributes; attribute++) {
                double sumAtt = 0;
                for (int h = 0; h < clusterCentroids.get(i).getArrOfVectors().size(); h++) {
                    sumAtt += clusterCentroids.get(i).getArrOfVectors().get(i).getVector().get(attribute);
                }
                tempVect.add(sumAtt / clusterCentroids.get(i).getArrOfVectors().size());
            }
            clusterCentroids.get(i).setCentroidVector(tempVect);
            System.out.println(clusterCentroids.get(i).toString());
        }

        return clusterCentroids;
    }

    public static void algorithm(ArrayList<Cluster> clusters, ArrayList<CustomVector> testVectors){
        iterationZero(clusters, testVectors);
        int iteration = 0;
        double sumOfDistances;
        while (true){
            iteration++;
            System.out.println("\n---------------------------------------Iteration number: " + iteration + "---------------------------------------");

            for (int i = 0; i < numOfClusters; i++) {
                clusters.get(i).getArrOfVectors().clear();
            }

            for (int i = 0; i < testVectors.size(); i++) {
                double maxDist = 0;
                String nameOfMaxCluster = "";

                //find max dist for each cluster
                for (int j = 0; j < clusters.size(); j++) {
                    double dist = euclideanDist(clusters.get(j), testVectors.get(i));
                    if (dist > maxDist) {
                        maxDist = dist;
                        nameOfMaxCluster = clusters.get(j).getClusterClassName();
                    }
                }

                //adding vector to cluster's array of vectors
                for (int j = 0; j < clusters.size(); j++) {
                    if (clusters.get(j).getClusterClassName().equals(nameOfMaxCluster)) {
                        clusters.get(j).getArrOfVectors().add(testVectors.get(i));
                    }
                }
            }
            double sumDist = 0;
            for (int i = 0; i < clusters.size(); i++) {
                System.out.println("***Cluster " + clusters.get(i).getClusterClassName() + ": ***");
                sumDist += sumDistancesInCluster(clusters.get(i));
                printPercentInCluster(clusters.get(i));
                updateCentroid(clusters.get(i));
                System.out.println();
            }
            System.out.println("Sum of distances between observations and their centroids: " + sumDist);

            sumOfDistances = 0;
            for (int i = 0; i < clusters.size(); i++) {
                sumOfDistances += sumDistancesInCluster(clusters.get(i));
            }
            if(sumOfPrevDistances == sumOfDistances || sumOfPrevPrevDistances == sumOfDistances){
                System.out.println("Clusters don't change anymore! The program stops!");
                break;
            }
            if(iteration%2 == 1){
                sumOfPrevDistances = sumOfDistances;
            }else {
                sumOfPrevPrevDistances = sumOfDistances;
            }

        }
    }

    public static void iterationZero(ArrayList<Cluster> clusters, ArrayList<CustomVector> testVectors){
            int iteration = 0;
            System.out.println("\n---------------------------------------Iteration number: " + iteration + "---------------------------------------");
            double sumDist = 0;
            for (int i = 0; i < clusters.size(); i++) {
                System.out.println("***Cluster " + clusters.get(i).getClusterClassName() + ": ***");
                sumDist += sumDistancesInCluster(clusters.get(i));
                printPercentInCluster(clusters.get(i));
                System.out.println("Centroid vector: " + clusters.get(i).getCentroidVector());
                System.out.println();
            }
            System.out.println("Sum of distances between observations and their centroids: " + sumDist*2);
    }
    public static double euclideanDist(Cluster cluster, CustomVector customVector) {
        double sum = 0;
        for (int i = 0; i < numOfAttributes; i++) {
            sum += Math.sqrt(Math.pow(customVector.getVector().get(i) - cluster.getCentroidVector().get(i), 2));
        }
        return sum;
    }

    public static double sumDistancesInCluster(Cluster cluster) {
        double sum = 0;
        for (int i = 0; i < cluster.getArrOfVectors().size(); i++) {
            sum += euclideanDist(cluster, cluster.getArrOfVectors().get(i));
        }
        return sum;
    }

    public static void printPercentInCluster(Cluster cluster) {
        if (cluster.getArrOfVectors().size() > 0) {
            HashMap<String, Integer> map = new HashMap<>();
            for (int s = 0; s < classesNames.size(); s++) {
                map.put(classesNames.get(s), 0);
            }

            for (int i = 0; i < cluster.getArrOfVectors().size(); i++) { //count irises in cluster by name
                if (map.containsKey(cluster.getArrOfVectors().get(i).getClassName())) {
                    map.put(cluster.getArrOfVectors().get(i).getClassName(), map.get(cluster.getArrOfVectors().get(i).getClassName()) + 1);
                }
            }

            for (int s = 0; s < classesNames.size(); s++) {
                if (map.get(classesNames.get(s)) != 0) {
                    System.out.println("Percent of class " + classesNames.get(s) + " is " + Math.round(map.get(classesNames.get(s)) * 100 / cluster.getArrOfVectors().size()) + "% (" + map.get(classesNames.get(s)) + ")");
                }
            }

        }else {
            for (int s = 0; s < classesNames.size(); s++) {
                System.out.println("Percent of class " + classesNames.get(s) + " is " + 0 + "% (" + 0 + ")");
            }
        }
    }

    public static void updateCentroid(Cluster cluster) {
        if (cluster.getArrOfVectors().size() > 0) {
            Vector<Double> oldVector = cluster.getCentroidVector();
            Vector<Double> tempVect = new Vector<>();
            for (int attribute = 0; attribute < numOfAttributes; attribute++) {
                double sumAtt = 0;
                for (int i = 0; i < cluster.getArrOfVectors().size(); i++) {
                    sumAtt += cluster.getArrOfVectors().get(i).getVector().get(attribute);
                }
                tempVect.add(sumAtt / cluster.getArrOfVectors().size());
            }
            cluster.setCentroidVector(tempVect);
            Vector<Double> newVector = cluster.getCentroidVector();

            if (oldVector.equals(newVector)) {
                System.out.println("No updates of centroid vector");
            } else {
                System.out.println("Updated centroid vector: " + newVector);
            }
        } else {
            System.out.println("No updates of centroid vector");
        }
    }

}
