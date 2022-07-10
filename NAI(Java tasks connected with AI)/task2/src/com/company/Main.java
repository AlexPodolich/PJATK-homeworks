package com.company;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        menu(0.1, 25);
    }

    public static void menu(double learningRate, int numOfIterations) throws IOException {
        String pathToTrainFile = "resources\\example1\\train.txt";
        String pathToTestFile = "resources\\example1\\test.txt";
        String pathToIrisTrainFile = "resources\\iris_perceptron\\training.txt";
        String pathToIrisTestFile = "resources\\iris_perceptron\\test.txt";
        if (learningRate == 0.1) {
            System.out.println("0.Enter learning rate(0.1 as default):");
        } else {
            System.out.println("0.Enter learning rate("+ learningRate + " current value):");
        }
        System.out.println("1.Run example1");
        System.out.println("2.Run iris_perceptron");
        System.out.println("3.Input test sample for classification.");
        if (numOfIterations == 25) {
            System.out.println("4.Enter num of iterations(25 as default):");
        } else {
            System.out.println("4.Enter num of iterations("+ numOfIterations + " current value):");
        }
        System.out.println("5.Exit");
        System.out.println("User choice: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 0 -> {
                System.out.println("Enter new value for learning rate: ");
                String userRate = sc.next();
                learningRate = Double.parseDouble(userRate);
                System.out.println("Now learning rate = " + learningRate);
                menu(learningRate, numOfIterations);
            }
            case 1 -> {
                ArrayList<CustomVector> trainVectors = new ArrayList<>();
                Parser.Parse(pathToTrainFile, trainVectors);
                System.out.println(trainVectors);
                double theta = 0;
                int dimension = trainVectors.get(0).getVector().size();
                double[] weights = new double[dimension];
                double diff;
                int iteration, output;
                for (int i = 0; i < dimension; i++) {
                    weights[i] = 0;
                }
                iteration = 0;
                do {
                    iteration++;
                    for (int j = 0; j < trainVectors.size(); j++) {
                        output = calculateOutput(trainVectors.get(j), weights, trainVectors.get(j).getVector().size(), theta);
                        diff = Integer.parseInt(trainVectors.get(j).getClassName()) - output;
                        for (int i = 0; i < dimension; i++) {
                            weights[i] += learningRate * diff * trainVectors.get(j).getVector().get(i);
                        }
                        theta -= diff * learningRate;
                    }
                }while (iteration < numOfIterations);
                ArrayList<CustomVector> testVectors = new ArrayList<>();
                Parser.Parse(pathToTestFile, testVectors);
                ArrayList<String> outputs = new ArrayList<>();
                for (int j = 0; j < testVectors.size(); j++) {
                    output = calculateOutput(testVectors.get(j), weights, testVectors.get(j).getVector().size(), theta);
                    outputs.add(String.valueOf(output));
                    System.out.println("Predicted class for " + (j+1) + " case: " + output);
                    System.out.println("Actual class is "+ testVectors.get(j).getClassName());
                }
                testAccuracy(testVectors, outputs);
                menu(learningRate, numOfIterations);
            }
            case 2 -> {
                ArrayList<CustomVector> trainVectors = new ArrayList<>();
                Parser.Parse(pathToIrisTrainFile, trainVectors);
                List<String> tmpList = new ArrayList<>();
                for (int i = 0; i < trainVectors.size(); i++) {
                    tmpList.add(trainVectors.get(i).getClassName());
                }
                Set<String> classNames = new LinkedHashSet<>(tmpList);
                ArrayList<String> classNamesArray = new ArrayList<>(classNames);
                System.out.println(trainVectors);
                double theta = 0;
                int dimension = trainVectors.get(0).getVector().size();
                double[] weights = new double[dimension];
                double diff;
                int iteration, output;
                for (int i = 0; i < dimension; i++) {
                    weights[i] = 0;
                }
                iteration = 0;
                do {
                    iteration++;
                    for (int j = 0; j < trainVectors.size(); j++) {
                        output = calculateOutput(trainVectors.get(j), weights, trainVectors.get(j).getVector().size(), theta);
                        int originalOutput = trainVectors.get(j).getClassName().equals(classNamesArray.get(0)) ? 0 : 1;
                        diff = originalOutput - output;
                        for (int i = 0; i < dimension; i++) {
                            weights[i] += learningRate * diff * trainVectors.get(j).getVector().get(i);
                        }
                        theta -= diff * learningRate;
                    }
                }while (iteration < numOfIterations);
                ArrayList<CustomVector> testVectors = new ArrayList<>();
                Parser.Parse(pathToIrisTestFile, testVectors);
                ArrayList<String> outputs = new ArrayList<>();
                for (int j = 0; j < testVectors.size(); j++) {
                    output = calculateOutput(testVectors.get(j), weights, testVectors.get(j).getVector().size(), theta);
                    outputs.add(String.valueOf(output == 0 ? classNamesArray.get(0) : classNamesArray.get(1)));
                    System.out.println("Predicted class for " + (j+1) + " case: " + (output == 0 ? classNamesArray.get(0) : classNamesArray.get(1)));
                    System.out.println("Actual class is "+ testVectors.get(j).getClassName());
                }
                testAccuracy(testVectors, outputs);
                menu(learningRate, numOfIterations);
            }
            case 3 -> {
                ArrayList<CustomVector> trainVectors = new ArrayList<>();
                Parser.Parse(pathToTestFile, trainVectors);
                List<String> tmpList = new ArrayList<>();
                for (int i = 0; i < trainVectors.size(); i++) {
                    tmpList.add(trainVectors.get(i).getClassName());
                }
                Set<String> classNames = new LinkedHashSet<>(tmpList);
                ArrayList<String> classNamesArray = new ArrayList<>(classNames);
                System.out.println("Enter test sample for classification: ");
                String userSample = sc.next();
                String[] subString = userSample.split(",");
                String className = subString[subString.length - 1];
                Vector<Double> vec = new Vector<>(subString.length - 1);
                for (int i = 0; i < vec.capacity(); i++) {
                    vec.add(Double.parseDouble(subString[i]));
                }
                CustomVector userCustomVector = new CustomVector(vec, className);

                double theta = 0;
                int dimension = trainVectors.get(0).getVector().size();
                double[] weights = new double[dimension];
                double diff;
                int iteration, output;
                for (int i = 0; i < dimension; i++) {
                    weights[i] = 0;
                }
                iteration = 0;
                do {
                    iteration++;
                    for (int j = 0; j < trainVectors.size(); j++) {
                        output = calculateOutput(trainVectors.get(j), weights, trainVectors.get(j).getVector().size(), theta);
                        int originalOutput = trainVectors.get(j).getClassName().equals(classNamesArray.get(0)) ? 0 : 1;
                        diff = originalOutput - output;
                        for (int i = 0; i < dimension; i++) {
                            weights[i] += learningRate * diff * trainVectors.get(j).getVector().get(i);
                        }
                        theta -= diff * learningRate;
                    }
                }while (iteration < numOfIterations);

                double res = 0;
                for (int l = 0; l < userCustomVector.getVector().size(); l++) {
                    double point1 = userCustomVector.getVector().get(l);
                    double point2 = weights[l];
                    res += point1 * point2;
                }
                //System.out.println("User class is " + (res == 0 ? classNamesArray.get(0) : classNamesArray.get(1)));
                System.out.println("User class is " + ((res >= theta) ? 1 : 0));


                menu(learningRate, numOfIterations);
            }
            case 4 -> {
                System.out.println("Enter new value for num of iterations: ");
                numOfIterations = sc.nextInt();
                System.out.println("Now learning rate = " + numOfIterations);
                menu(learningRate, numOfIterations);
            }
            case 5 -> {
                System.out.println("Thank you! Bye!");
                System.exit(0);
            }
            default -> {
                System.out.println("Error! Enter number 1-5!");
                menu(learningRate, numOfIterations);
            }
        }
    }

    public static int calculateOutput(CustomVector input, double[] weight, int dimension, double theta){
        double res = 0;
        for (int l = 0; l < dimension; l++) {
            double point1 = input.getVector().get(l);
            double point2 = weight[l];
            res += point1 * point2;
        }
        return (res >= theta) ? 1 : 0;
    }

    public static void testAccuracy(ArrayList<CustomVector> test, ArrayList<String> myResults) {
        int correctTests = 0;
        int wrongTests = 0;
        for (int i = 0; i < test.size(); i++) {
            if (test.get(i).getClassName().equals(myResults.get(i))) {
                correctTests++;
            } else {
                wrongTests++;
            }
        }
        double accuracy = (correctTests / Double.parseDouble(String.valueOf(test.size()))) * 100;
        System.out.println("Correct " + correctTests + "/" + test.size());
        System.out.println("Wrong " + wrongTests + "/" + test.size());
        System.out.println("Accuracy: " + Math.round(accuracy) + "%");
    }
}